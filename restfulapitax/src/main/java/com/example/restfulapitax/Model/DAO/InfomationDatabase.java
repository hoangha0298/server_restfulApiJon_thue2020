package com.example.restfulapitax.Model.DAO;

public class InfomationDatabase {
    private static String hostName = "localhost";
    private static String userName = "root";
    private static String password = "";
    private static String dbName = "thue2020";

    private InfomationDatabase() {
    }

    public static String getHostName() {
        return hostName;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    public static String getDbName() {
        return dbName;
    }
}
