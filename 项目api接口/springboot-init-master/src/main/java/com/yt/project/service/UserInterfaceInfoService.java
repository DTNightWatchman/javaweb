package com.yt.project.service;

import com.yt.project.model.domain.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author lenovo
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2023-02-22 13:04:44
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 调用接口逻辑
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
