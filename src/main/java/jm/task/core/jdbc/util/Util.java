package jm.task.core.jdbc.util;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util  {

    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/users?useUnicode=true&serverTimezone=UTC&" +
            "useSSL=true&verifyServerCertificate=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "111";


    public Connection getConnection(){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение установлено.");
        } catch (SQLException e) {
            System.err.println("Соединение НЕ установлено.");
        }
        return connection;
    }




}
