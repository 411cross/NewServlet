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

//        String dbName = "nurse_app";
        String dbName = "test";


        String username = "root";
        String password = "root";
        Connection conn = null;
//        String url = "jdbc:mysql://localhost:3306/" + dbName + "?user=" + username + "&password=" + password + "&useUnicode=true&characterEncoding=utf-8";
        String url = "jdbc:mysql://localhost:3306/" + dbName + "?user=" + username + "&password=" + password + "&useUnicode=true&characterEncoding=utf-8";


        try {
            Class.forName(driver); //classLoader
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = (Connection) DriverManager.getConnection(url);
            System.out.println(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
