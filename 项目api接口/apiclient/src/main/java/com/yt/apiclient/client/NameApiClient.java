package com.yt.apiclient.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.yt.apiclient.model.User;
import com.yt.apiclient.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 调用第三方接口的客户端
 */
public class NameApiClient {

    private String accessKey;
    private String secretKey;

    public NameApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String res = HttpRequest.get("http://localhost:10010/api/name/").addHeaders(getHeaderMap(name)).form(paramMap)
                .execute().body();
        return res;
    }

    public String getNameByPost(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String body = HttpRequest.post("http://localhost:10010/api/name/").addHeaders(getHeaderMap(name)).form(paramMap)
                .execute().body();
        return body;
    }

    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> header = new HashMap<>();
        header.put("accessKey", accessKey);
        String nonce = RandomUtil.randomNumbers(8);
        header.put("nonce", nonce);
        header.put("body", body);
        String timestamp = String.valueOf(System.currentTimeMillis());
        header.put("timestamp", timestamp);
        if (body == null) {
            body = "";
        }
        header.put("sign", SignUtils.getSign(body + nonce + timestamp, secretKey));
        return header;
    }

    public String getNameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse execute = HttpRequest.post("http://localhost:10010/api/name/user").contentType("application/json;charset=gbk")
                .body(json)
                .addHeaders(getHeaderMap(json))
                .execute();
        System.out.println(execute.getStatus());
        return execute.body();
    }

}
