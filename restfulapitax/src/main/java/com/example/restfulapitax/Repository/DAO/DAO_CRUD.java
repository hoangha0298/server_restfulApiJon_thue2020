package com.example.restfulapitax.Repository.DAO;

import com.example.restfulapitax.Repository.InfomationDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

@Repository
public abstract class DAO_CRUD {

    protected Statement statement;
    protected String hostName = InfomationDatabase.getHostName();
    protected String userName = InfomationDatabase.getUserName();
    protected String password = InfomationDatabase.getPassword();
    protected String dbName = InfomationDatabase.getDbName();

    public DAO_CRUD() {
        try {
            // tạo connection tới db manager system
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionURL =
                    "jdbc:mysql://" +
                            hostName +
                            ":3306/" +
                            "?useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(connectionURL, userName, password);
            statement = conn.createStatement();

            // tạo csdl
            if (!execute("USE " + dbName)) {
                createDatabase();
                execute("USE " + dbName);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("!!!!!!!! don't connection to database !!!!!!!!");
        }
    }

    // tạo mới cơ sở dữ liệu nếu có rồi thì xóa đi
    public void createDatabase ()  {
        System.out.println("====================createAndClearDatabase====================");

        // lấy thông tin tạo database từ file dll
        String workingDir = System.getProperty("user.dir");
        String path = workingDir + "\\src\\main\\java\\com\\example\\restfulapitax\\Repository\\database3.0.ddl";
        Scanner scanner = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path));
            scanner = new Scanner(fileInputStream);
        } catch (FileNotFoundException e) {
            System.out.println("!!!!!!!!! Không tìm thấy file config database !!!!!!!!!");
            e.printStackTrace();
        }
        String sql = "";
        while (scanner.hasNext()) {
            sql += scanner.nextLine();
            sql += "\n";
        }

        // xóa csdl
        if (executeUpdate("DROP DATABASE " + dbName) == 0)
            System.out.println("!!!!!!!!! data base '" + dbName + "' don't exist !!!!!!!!!");

        // tạo csdl
        execute("CREATE DATABASE " + dbName);
        execute("USE " + dbName);

        // bỏ kí tự cuối dòng file dll là 10 trong bảng utf 8
        String[] listSql = sql.split(";" + (char)10);
        for (String temp : listSql)
        {
            System.out.println(execute(temp));
        }
    }

    // câu lệnh thêm, sử dụng bảng ( create, use)
    protected boolean execute(String sql) {
        try {
            System.out.println(sql);
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("!!!! sql error !!!!");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // câu lệnh đọc
    protected ResultSet executeQuery(String sql) {
        try {
            System.out.println(sql);
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("!!!! sql error !!!!");
            e.printStackTrace();
        }
        return null;
    }

    // câu lệnh sửa, xóa
    protected int executeUpdate(String sql) {
        try {
            System.out.println(sql);
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("!!!! sql error !!!!");
            e.printStackTrace();
        }
        return 0;
    }


}
