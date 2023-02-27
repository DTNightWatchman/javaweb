package com.yt.project.service.impl;

import cn.hutool.core.util.URLUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yt.apiclient.client.NameApiClient;
import com.yt.project.common.ErrorCode;
import com.yt.project.common.IdRequest;
import com.yt.project.common.InterfaceInfoStatusEnum;
import com.yt.project.exception.BusinessException;
import com.yt.project.mapper.InterfaceInfoMapper;
import com.yt.project.model.domain.InterfaceInfo;
import com.yt.project.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Locale;

/**
* @author lenovo
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2022-11-03 17:43:17
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{

    @Resource
    private NameApiClient nameApiClient;

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        // 创建的时候必要参数不能为空
        if (add) {
            if (StringUtils.isAnyBlank(name)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }

    @Override
    public boolean updateStatus(IdRequest idRequest, int status) {
        if (idRequest == null || idRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 判断是否存在
        InterfaceInfo interfaceInfo = this.getById(idRequest.getId());
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 判断该接口是否可以调用
        com.yt.apiclient.model.User requestUser = new com.yt.apiclient.model.User();
        requestUser.setUsername("testName");
        String testNameByPost = nameApiClient.getNameByPost(requestUser);
        if (StringUtils.isBlank(testNameByPost)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "测试接口失败");
        }

        InterfaceInfo onlineInterface = new InterfaceInfo();
        onlineInterface.setId(idRequest.getId());
        onlineInterface.setStatus(InterfaceInfoStatusEnum.ONLINE.getValue());
        UpdateWrapper<InterfaceInfo> updateWrapper = new UpdateWrapper<>();
        // 修改状态
        updateWrapper.setSql("status = " + status);
        updateWrapper.eq("id", idRequest.getId());
        boolean result = this.update(updateWrapper);
        return result;
    }

    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        // 直接判断，前缀问题让网关解决
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeLeft("url", url);
        // 方法统一在数据库中永大写存储
        queryWrapper.eq("method", method.toUpperCase());
        InterfaceInfo interfaceInfo = this.getOne(queryWrapper);
        return interfaceInfo;
    }
}




