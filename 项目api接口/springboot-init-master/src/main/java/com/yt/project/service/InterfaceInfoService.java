package com.yt.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yt.project.common.IdRequest;
import com.yt.project.model.domain.InterfaceInfo;

/**
* @author lenovo
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2022-11-03 17:43:17
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean b);


    /**
     * 修改状态
     * @param idRequest
     * @param status
     */
    boolean updateStatus(IdRequest idRequest, int status);

    /**
     * 根据对应的路径和方法判断是否有对应数据
     * @param url
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String url, String method);
}
