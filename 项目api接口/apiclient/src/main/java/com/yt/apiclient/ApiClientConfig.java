package com.yt.apiclient;
import com.yt.apiclient.client.NameApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// 用于读取配置，加载到类中
@ConfigurationProperties("api.client")
@Data
// 用于扫包
@ComponentScan
public class ApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public NameApiClient nameApiClient() {
        return new NameApiClient(accessKey, secretKey);
    }

    //    public static void main(String[] args) {
//
//        NameApiClient nameApiClient = new NameApiClient();
////        String res1 = nameApiClient.getNameByGet("YT摆渡人");
////        String res2 = nameApiClient.getNameByPost("YT摆渡人1");
//        User user = new User();
//        user.setUsername("YTbaiduren2");
//        String res3 = nameApiClient.getNameByPost(user);
////        System.out.println("result1:" + res1);
////        System.out.println("result2:" + res2);
//        System.out.println("result3:" + res3);
//    }





}
