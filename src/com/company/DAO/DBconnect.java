package com.company.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by Administrator on 2017/6/29.
 */
public class DBconnect {

    public static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "root";
        Connection conn = null;

        try {
            Class.forName(driver); //classLoader
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
