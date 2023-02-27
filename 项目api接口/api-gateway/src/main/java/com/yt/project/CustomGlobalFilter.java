package com.yt.project;


import com.yt.apiclient.utils.SignUtils;
import com.yt.apicommons.model.domain.InterfaceInfo;
import com.yt.apicommons.model.entity.User;
import com.yt.project.provider.CommonsService;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1", "0.0.0.0");

    @Resource
    private CommonsService commonsService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // print log
        ServerHttpRequest request = exchange.getRequest();
        log.info("requestId:" + request.getId());
        log.info("requestPath:" + request.getPath().value());
        log.info("requestMethod:" + request.getMethod());
        log.info("requestQueryParams:" + request.getQueryParams());
        log.info("requestRemoteAddress:" + request.getRemoteAddress());
        // black write list
        String sourceAddress = Objects.requireNonNull(request.getRemoteAddress()).getHostString();
        ServerHttpResponse response = exchange.getResponse();
        if (!IP_WHITE_LIST.contains(sourceAddress)) {
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }

        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String body = headers.getFirst("body");
        String sign = headers.getFirst("sign");
        // 从数据库中判断是否存在accessKey，存在，再执行后面的程序
        User invokeUser = null;
        try {
            invokeUser = commonsService.getInvokeUser(accessKey, null);
        } catch (Exception e){
            log.error("get invoke user error", e);
        }
        if (invokeUser == null) {
            return handleNoAuth(response);
        }



        if (nonce == null) {
            return handleNoAuth(response);
        }
        // 用redis进行判断
        String key = "apiGateway:nonce";
        // 写入到redis中
        Double score = stringRedisTemplate.opsForZSet().score(key, nonce);
        System.out.println(score);
        if (score != null) {
            // 已有随机值
            return handleNoAuth(response);
        }
        stringRedisTemplate.opsForZSet().add(key, nonce, System.currentTimeMillis());

        if (timestamp == null) {
            return handleNoAuth(response);
        }

        // 时间和当前时间不能超过5分钟
        if (Timestamp.valueOf(LocalDateTime.now().minusMinutes(1)).after(new Timestamp(Long.parseLong(timestamp)))) {
            return handleNoAuth(response);
        }


        // 从数据库中获取secretKey，根据accessKey
        String secretKey = invokeUser.getSecretKey();
        String toSign = body + nonce + timestamp;
        System.out.println(toSign);
        String serverSign = SignUtils.getSign(toSign, secretKey);
        // 判断签名是否一致
        if (!serverSign.equals(sign)) {
            return handleNoAuth(response);
        }
        // 判断请求的 url 和 method 方法在数据库中是否存在
        String path = request.getPath().value();
        String httpMethod = Objects.requireNonNull(request.getMethod()).toString();
        //log.debug(path, httpMethod);
        InterfaceInfo interfaceInfo = null;
        //
        try {
            interfaceInfo = commonsService.getInterfaceInfo(path, httpMethod);
        } catch (Exception e) {
            log.error("get interfaceInfo error : ", e);
        }
        if (interfaceInfo == null) {
            return handleInvokeError(response);
        }
        // 判断数据库中是否还有次数
        if (!commonsService.ifCouldInvoke(interfaceInfo.getId(), invokeUser.getId())) {
            // 没有次数了
            return handleNoAuth(response);
        }
        return handleResponse(exchange, chain, interfaceInfo.getId(), invokeUser.getId());
    }


    /**
     * 处理响应
     * @param exchange
     * @param chain
     * @return
     */
    private Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain, long interfaceInfoId, long userId) {
        try {
            ServerHttpResponse originalResponse = exchange.getResponse();
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();

            HttpStatus statusCode = originalResponse.getStatusCode();

            if(statusCode == HttpStatus.OK){
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {

                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        //log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            //
                            return super.writeWith(fluxBody.map(dataBuffer -> {
                                // 到达此处，执行成功
                                // 判断结果，成功调用次数+1
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                try {
                                    commonsService.invokeCount(interfaceInfoId, userId);

                                } catch (Exception e) {
                                    log.error("invoke count error : " + e);
                                }
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);//释放掉内存
                                // 构建日志
                                StringBuilder sb2 = new StringBuilder(200);
                                sb2.append("<--- {} {} \n");
                                List<Object> rspArgs = new ArrayList<>();
                                rspArgs.add(originalResponse.getStatusCode());
                                String data = new String(content, StandardCharsets.UTF_8);//data
                                sb2.append(data);
                                // 处理结果，打印日志
                                log.info("响应结果：" + data);
                                return bufferFactory.wrap(content);
                            }));
                        } else {
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange);//降级处理返回数据
        }catch (Exception e){
            log.error("gateway log exception.\n" + e);
            return chain.filter(exchange);
        }
    }

    private Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    private Mono<Void> handleInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}

