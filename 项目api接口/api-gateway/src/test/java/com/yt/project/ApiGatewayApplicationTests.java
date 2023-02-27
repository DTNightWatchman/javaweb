package com.yt.project;

import com.yt.apicommons.model.entity.User;
import com.yt.project.provider.CommonsService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ApiGatewayApplicationTests {

    @Resource
    private CommonsService commonsService;

    @Test
    void testInvokeCount() {
        User user = commonsService.getInvokeUser("admin123","");

        System.out.println(user);

    }
//
}
