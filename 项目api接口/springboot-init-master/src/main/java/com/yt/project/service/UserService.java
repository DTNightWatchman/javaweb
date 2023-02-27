package com.yt.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yt.project.model.entity.User;
import com.yt.project.model.vo.AkSkVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
 *
 * @author yt
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);


    /**
     * 根据accessKey 和 secretKey来判断是否存在用户
     * @param accessKey
     * @param secretKey
     * @return user
     */
    User getInvokeUser(String accessKey, String secretKey);

    /**
     * 获取当前用户在数据库中accessKey，secretKey
     * @param request
     * @return
     */
    AkSkVO getUserAccessKeySecretKey(HttpServletRequest request);

    /**
     * 自动修改aksk
     * @param request
     * @return
     */
    Boolean updateAkSk(HttpServletRequest request);


    /**
     * 添加头像
     * @param file
     * @param request
     * @return
     */
    String uploadUserAvatar(MultipartFile file, HttpServletRequest request);
}
