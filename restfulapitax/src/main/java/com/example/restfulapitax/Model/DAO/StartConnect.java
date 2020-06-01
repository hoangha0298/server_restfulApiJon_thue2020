package com.example.restfulapitax.Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StartConnect {       // Design Pattern – Singleton

    private static Connection conn;

    public static Connection getConnection(String hostName , String userName , String password) {
        try {
            if (conn == null || conn.isClosed())
            //  nếu connection chưa có hoặc đã close thì tạo mới
            {
                System.out.println("========= Connection = null or isClosed, create Connection =========");
                Class.forName("com.mysql.cj.jdbc.Driver");
                String connectionURL =
                                "jdbc:mysql://" +
                                hostName +
                                ":3306/" +
                                "?useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
                conn = DriverManager.getConnection(connectionURL, userName, password);
            }
            else
                System.out.println("========= Connection is exist, no create new connect =========");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("!!!!!!!! don't connection to database !!!!!!!!");
        }
        return conn;
    }

    public static boolean selectedDatabase(String dbName) {
        try {
            conn.createStatement().execute("USE " + dbName);
        } catch (SQLException e) {
            System.out.println("!!!!!!!! không chọn được database !!!!!!!!");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private StartConnect() {
    }

}

