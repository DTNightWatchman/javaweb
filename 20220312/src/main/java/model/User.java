package model;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-03-10
 * Time:15:43
 */
public class User {
    private int userId;
    private String username;
    private String password;
//判断是否是你的博客
    private int isYourBlog;

    public int getIsYourBlog() {
        return isYourBlog;
    }

    public void setIsYourBlog(int isYourBlog) {
        this.isYourBlog = isYourBlog;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
