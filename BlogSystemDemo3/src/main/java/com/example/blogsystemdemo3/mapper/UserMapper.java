package com.example.blogsystemdemo3.mapper;

import com.example.blogsystemdemo3.model.User;
import com.example.blogsystemdemo3.model.UserSession;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: YT
 * @Description: 用户操作
 * @DateTime: 2022/7/27$ - 21:17
 */
@Mapper
public interface UserMapper {
    public int insertUser(User user);

    public User ifExistUser(String username);

    public User ifCouldLogin(String username, String email, String password);

    public UserSession getUserMessage(int userId);

    public int incNumber(int userId);

    public int decNumber(int userId);

}
