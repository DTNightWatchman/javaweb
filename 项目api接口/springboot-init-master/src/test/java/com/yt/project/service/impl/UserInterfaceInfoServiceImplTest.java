package com.yt.project.service.impl;



import com.yt.project.service.UserInterfaceInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class UserInterfaceInfoServiceImplTest {

    @Resource
    UserInterfaceInfoService userInterfaceInfoService;

    @Test
    void invokeCount() {
        userInterfaceInfoService.invokeCount(1L,1L);

    }
}