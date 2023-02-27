package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-03-11
 * Time:20:55
 */
public class CheckUtil {
    public static User check(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session == null) {
            return null;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return null;
        }
        return user;
    }
}
