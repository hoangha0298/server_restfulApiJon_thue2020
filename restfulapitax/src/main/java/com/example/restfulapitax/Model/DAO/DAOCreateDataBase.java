package com.example.restfulapitax.Model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DAOCreateDataBase {

    private static Statement statement;

    private static boolean execute(String sql) {
        try {
            System.out.println(sql);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // tạo mới database rỗng không có thông tin, nếu có rồi thì xóa tạo lại
    public static void run (Connection connection, String dbName)  {
        System.out.println("====================createAndClearDatabase====================");
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("!!!!!!!!! Connection chưa được mở !!!!!!!!!");
            e.printStackTrace();
        }

        // lấy thông tin tạo database từ file dll
         String workingDir = System.getProperty("user.dir");
         String path = workingDir + "\\src\\main\\DatabaseDDL\\database2.2.ddl";
//        String path = "C:\\Users\\hoang ha\\Desktop\\bài tập ở trường\\bài tập lớn db chất lượng pm\\csdl\\database2.2.ddl";
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

        if (!execute("DROP DATABASE " + dbName))
            System.out.println("!!!!!!!!! data base '" + dbName + "' don't exist !!!!!!!!!");

        execute("CREATE DATABASE " + dbName);
        execute("USE " + dbName);

        // bỏ kí tự cuối dòng file dll là 10 trong bảng utf 8
        String[] listSql = sql.split(";" + (char)10);
        for (String temp : listSql)
        {
            System.out.println(execute(temp));
        }
    }

    private DAOCreateDataBase() {
    }
}
