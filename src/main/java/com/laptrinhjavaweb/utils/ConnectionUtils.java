package com.laptrinhjavaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

    private static String DB_URL = "jdbc:mysql://localhost:3306/springbootweb2";
    private static String USER = "root";
    private static String PASS = "Shapeyou@1";

    public static Connection getConnection() throws ClassNotFoundException {

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
