package com.example.blogsystemdemo2.mapper;

import com.example.blogsystemdemo2.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: YT
 * @Description:
 * @DateTime: 2022/6/1 - 21:40
 */

@Mapper
public interface UserMapper {

    //增加新用户
    public int addNewUser(String username, String password);

    //查找指定用户名
    public User findUserByName(String username);

    //根据用户名和密码查找用户
    public User findUserByNameAndPassword(String username, String password);

    //根据用户名id来查找用户
    public User findUserById(int userId);

    //在插入一篇博客的时候，给用户的number自增1
    public int updateNumber(int userId);

    //在删除一篇博客的时候，给用户的number自减1
    public int decNumber(int userId);

    //找到用户的所有博客（使用左连接）
    public User getOwnerBlogList(int userId);
}
