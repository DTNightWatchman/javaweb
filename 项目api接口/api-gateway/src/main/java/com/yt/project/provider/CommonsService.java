package com.yt.project.provider;

import com.yt.apicommons.model.domain.InterfaceInfo;
import com.yt.apicommons.model.entity.User;
import com.yt.apicommons.services.CommonUserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class CommonsService {

    @DubboReference
    private CommonUserInterfaceInfoService commonUserInterfaceInfoService;

    public boolean invokeCount(long interfaceInfoId, long userId) {
        return commonUserInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }

    public User getInvokeUser(String accessKey,String secretKey) {
        return commonUserInterfaceInfoService.getInvokeUser(accessKey, secretKey);
    }

    public InterfaceInfo getInterfaceInfo(String path, String method) {
        return commonUserInterfaceInfoService.getInterfaceInfo(path, method);
    }

    public boolean ifCouldInvoke(long interfaceInfoId, long userId) {
        return commonUserInterfaceInfoService.getCouldInvoke(interfaceInfoId, userId);

    }

}
