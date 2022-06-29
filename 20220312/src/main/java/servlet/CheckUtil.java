package servlet;

import model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-03-13
 * Time:15:26
 */
public class CheckUtil {
    public static User check(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            return null;
        }
        User user = (User) session.getAttribute("user");
        return user;
    }
}
