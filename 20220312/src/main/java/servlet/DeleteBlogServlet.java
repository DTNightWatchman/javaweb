package servlet;

import model.Blog;
import model.BlogDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-03-13
 * Time:21:25
 */
@WebServlet("/deleteblog")
public class DeleteBlogServlet extends HttpServlet {
    //?blogId=15

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=utf8");
        User user = CheckUtil.check(req);
        if (user == null) {
            resp.getWriter().write("<h3>未登录</h3>");
            resp.sendRedirect("blog_login.html");
            return;
        }
        String blogId = req.getParameter("blogId");
        if (blogId == null || "".equals(blogId)) {
            resp.getWriter().write("未输入blogId");
            return;
        }
        Blog blog = BlogDao.findBlog(Integer.parseInt(blogId));
        if (blog == null) {
            resp.getWriter().write("<h3>没有这篇博客</h3>");
        }
        if (user.getUserId() != blog.getUserId()) {
            resp.getWriter().write("<h3>不能删除别人博客</h3>");
            return;
        }
        BlogDao.deleteBlog(blog.getBlogId());
        resp.sendRedirect("blog_list.html");
    }
}
