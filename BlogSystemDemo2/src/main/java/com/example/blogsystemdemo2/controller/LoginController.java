package com.example.blogsystemdemo2.controller;

import com.example.blogsystemdemo2.component.BlackLogin;
import com.example.blogsystemdemo2.model.User;
import com.example.blogsystemdemo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YT
 * @Description: 登录
 * @DateTime: 2022/6/1$ - 21:10
 */

@RequestMapping("/login")
@Controller
@ResponseBody
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlackLogin blackLogin;

    @RequestMapping("/register")
    public Map<String,Object> register(@RequestBody User user) {
        //先验证前端数据
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println(username);
        System.out.println(password);
        Map<String,Object> map = new HashMap<>();
        if (StringUtils.hasLength(username) && StringUtils.hasLength(password)) {
            int flag = userService.addNewUser(username, password);
            System.out.println(flag);
            map.put("success",200);
            map.put("data",flag);
            if (flag == 1) {
                map.put("message","注册成功");
            } else {
                map.put("message","该用户名已经存在");
            }

        } else {
            map.put("success",200);
            map.put("data",0);
            map.put("message","账号或密码不能为空");
        }
        return map;
    }

    @RequestMapping("/login")
    public Map<String,Object> login(@RequestBody User user, HttpServletRequest request) {
        String IPAndPort =  user.getUsername();
        System.out.println(IPAndPort);
        User ifUser = userService.findUser(user.getUsername(),user.getPassword());
        Map<String,Object> result = new HashMap<>();
        Boolean flag = blackLogin.getBlackMap().containsKey(IPAndPort);
        int value = 1;
        if (flag) {
            value = blackLogin.getBlackMap().get(IPAndPort);
            if (value >= 5) {
                result.put("success",200);
                result.put("data",0);
                result.put("message","输错次数过多，账号被锁定,请联系管理员解开账号");
                return result;
            }
        }

        if (ifUser == null) {
            result.put("success",200);
            result.put("data",0);
            if (flag) {
                value = value + 1;
                blackLogin.getBlackMap().put(IPAndPort,value);
            } else {
                blackLogin.getBlackMap().put(IPAndPort,1);
            }
            result.put("message","账号或密码错误，还可以输入" + (5 - value) + "次");
        } else {
            if (flag) {
                blackLogin.getBlackMap().remove(IPAndPort);
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("user", ifUser);
            result.put("success",200);
            result.put("data",1);
            result.put("message","登录成功");
        }
        return result;
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("../blog_login.html");
            return;
        }
        User user = (User)session.getAttribute("user");
        System.out.println(user);
        if (user == null) {
            session.invalidate();
            response.sendRedirect("../blog_login.html");
            return;
        } else {
            session.removeAttribute("user");
            session.invalidate();
            response.sendRedirect("../blog_login.html");
            return;
        }
    }
}
