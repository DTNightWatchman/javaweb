package servlet;

import model.User;
import model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-03-13
 * Time:15:08
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        req.setCharacterEncoding("utf8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || password == null || "".equals(username) || "".equals(password)) {
            resp.getWriter().write("<h3>账号或密码为空</h3>");
            return;
        }
        User user = UserDao.selectByName(username);
        if (!user.getPassword().equals(password) || user == null) {
            resp.getWriter().write("<h3>账号或者密码错误</h3>");
            return;
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("user",user);
        resp.sendRedirect("blog_list.html");
    }
}
