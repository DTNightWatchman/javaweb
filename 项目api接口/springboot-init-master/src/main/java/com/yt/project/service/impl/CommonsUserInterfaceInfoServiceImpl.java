package com.yt.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yt.apicommons.model.domain.InterfaceInfo;
import com.yt.apicommons.model.entity.User;
import com.yt.apicommons.services.CommonUserInterfaceInfoService;
import com.yt.project.model.domain.UserInterfaceInfo;
import com.yt.project.service.InterfaceInfoService;
import com.yt.project.service.UserInterfaceInfoService;
import com.yt.project.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;

/**
 * 公共的实现过程
 */
@DubboService
public class CommonsUserInterfaceInfoServiceImpl implements CommonUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private UserService userService;

    /**
     * 公共模块接口中的实现调用一次接口记录的函数
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }


    /**
     * 公共接口中看是否有对应的接口
     * @param accessKey
     * @param secretKey
     * @return
     */
    @Override
    public User getInvokeUser(String accessKey, String secretKey) {
        com.yt.project.model.entity.User user = userService.getInvokeUser(accessKey, secretKey);
        // 类型转换
        User resUser = new User();
        BeanUtils.copyProperties(user, resUser);
        return resUser;
    }


    /**
     * 判断是否有对应的接口和方法
     * @param url
     * @param method
     * @return 为空表示没有
     */
    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        com.yt.project.model.domain.InterfaceInfo interfaceInfo = interfaceInfoService.getInterfaceInfo(url, method);
        if (interfaceInfo == null) {
            return null;
        }
        InterfaceInfo resInterfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfo, resInterfaceInfo);
        return resInterfaceInfo;
    }

    /**
     * 判断是否允许调用接口（是否还有次数）
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    @Override
    public boolean getCouldInvoke(long interfaceInfoId, long userId) {
        QueryWrapper<UserInterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("leftNum");
        queryWrapper.eq("interfaceInfoId", interfaceInfoId);
        queryWrapper.eq("userId", userId);
        UserInterfaceInfo one = userInterfaceInfoService.getOne(queryWrapper);
        if (one == null) {
            return false;
        }
        int leftNum = one.getLeftNum();
        return leftNum > 0;
    }

}
