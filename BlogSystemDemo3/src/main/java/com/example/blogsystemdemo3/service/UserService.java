package com.example.blogsystemdemo3.service;

import com.example.blogsystemdemo3.mapper.UserMapper;
import com.example.blogsystemdemo3.model.User;
import com.example.blogsystemdemo3.model.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: YT
 * @Description: 用户操作
 * @DateTime: 2022/7/27$ - 21:24
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int ifExistUser(String username) {
        User user = userMapper.ifExistUser(username);

        if (user == null) {
            return -1;
        } else {
            return user.getUserId();
        }
    }

    public int insertUser(User user) {
        int ret = userMapper.insertUser(user);
        return ret;
    }

    public UserSession ifCouldLogin(String username, String email, String password) {
        User user = userMapper.ifCouldLogin(username, email,password);
        if (user == null) {
            return null;
        }
        UserSession userSession = new UserSession();
        userSession.setUserId(user.getUserId());
        userSession.setUsername(user.getUsername());
        userSession.setEmail(user.getEmail());
        userSession.setGithub(user.getGithub());
        userSession.setNumber(user.getNumber());
        return userSession;
    }

    public UserSession getUserMessage(int userId) {
        UserSession userSession = userMapper.getUserMessage(userId);
        return userSession;
    }

}
