package servlet;

import model.User;

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
 * Time:19:30
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        HttpSession session = req.getSession(false);
        User user = CheckUtil.check(req);
        if (session == null ){
            resp.getWriter().write("<h3>当前未登录</h3>");
            return;
        }
        session.removeAttribute("user");
        resp.sendRedirect("blog_login.html");
    }
}
