package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Blog;
import model.BlogDao;
import model.User;
import model.UserDao;

import javax.jws.soap.SOAPBinding;
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
 * Time:16:17
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf8");
        User user = CheckUtil.check(req);
        if (user == null) {
            resp.setStatus(403);
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String blogId = req.getParameter("blogId");
        if (blogId == null || "".equals(blogId)) {
            //System.out.println(user);
            String userString = objectMapper.writeValueAsString(user);
            resp.getWriter().write(userString);
            return;
        } else {
            Blog blog = BlogDao.findBlog(Integer.parseInt(blogId));
            User writer = UserDao.selectByUserId(blog.getUserId());
            writer.setIsYourBlog(user.getUserId() == blog.getUserId()?1:0);
            System.out.println(writer.getIsYourBlog());
            String writerString = objectMapper.writeValueAsString(writer);
            resp.getWriter().write(writerString);
            return;
        }
    }
}
