package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "mypass";
    public static final String URL = "jdbc:mysql://127.0.0.1:3306/?user=root";
//    public static Connection connection;
    public static Statement statement;

    /*
        Driver driver;

        {
            try {
                driver = new com.mysql.cj.jdbc.Driver();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    */
/*    static {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
//            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.getStackTrace();
//            System.out.println("Connection Not OK");
        }
        return connection;
    }
}
