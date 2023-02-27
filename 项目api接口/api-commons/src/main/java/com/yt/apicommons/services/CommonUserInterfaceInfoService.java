package com.yt.apicommons.services;

import com.yt.apicommons.model.domain.InterfaceInfo;
import com.yt.apicommons.model.domain.UserInterfaceInfo;
import com.yt.apicommons.model.entity.User;

/**
* @author lenovo针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2023-02-22 13:04:44
*/
public interface CommonUserInterfaceInfoService {


    /**
     * 调用接口逻辑
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    /**
     * 查看是否有对应accessKey
     * @param accessKey
     * @param secretKey
     * @return
     */
    User getInvokeUser(String accessKey,String secretKey);

    /**
     * 查看是否有对应接口
     * @param url
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String url, String method);

    /**
     * 判断是否可以调用接口
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean getCouldInvoke(long interfaceInfoId, long userId);
}
