package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.imageio.plugins.gif.GIFImageReaderSpi;
//import jdk.internal.org.objectweb.asm.util.CheckSignatureAdapter;
import model.Blog;
import model.BlogDao;
import model.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-03-12
 * Time:21:09
 */
@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从数据库拿到数据列表，从数据库中拿到列表后以json格式的数据返回给前端
        resp.setContentType("application/json;charset=utf8");
        User user = CheckUtil.check(req);
        if (user == null) {
            resp.setStatus(403);
            return;
        }
        String blogId = req.getParameter("blogId");
        if (blogId == null || "".equals(blogId)) {
            List<Blog> blogs = BlogDao.findAllBlogs();
            String jsonString = objectMapper.writeValueAsString(blogs);
            resp.getWriter().write(jsonString);
        } else {
            Blog blog = BlogDao.findBlog(Integer.parseInt(blogId));
            if (blog == null) {
                resp.getWriter().write("<h3>没有这篇博客</h3>");
                return;
            }
            String blogString = objectMapper.writeValueAsString(blog);
            System.out.println(blogString);
            resp.getWriter().write(blogString);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        req.setCharacterEncoding("utf8");
        User user = CheckUtil.check(req);
        System.out.println("?????????");
        if (user == null) {
            resp.sendRedirect("blog_login.html");
            return;
        }
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if (title == null || content == null || "".equals(title) || "".equals(content)) {
            String html = "<h3>标题或者内容不能为空</h3>";
            resp.getWriter().write(html);
            return;
        }
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUserId(user.getUserId());
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        BlogDao.addBlog(blog);
        resp.sendRedirect("blog_list.html");
    }
}
