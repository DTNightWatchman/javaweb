package model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * created by YT
 * description:
 * User:lenovo
 * Data:2022-03-10
 * Time:15:14
 */
public class DBUtil {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/BlogSystem?characterEncoding=utf8&&useSSL=false";
    private static final String userName = "root";
    private static final String password = "";
    private static DataSource dataSource = new MysqlDataSource();
    static {
        ((MysqlDataSource)dataSource).setURL(url);
        ((MysqlDataSource)dataSource).setUser(userName);
        ((MysqlDataSource)dataSource).setPassword(password);
    }
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
