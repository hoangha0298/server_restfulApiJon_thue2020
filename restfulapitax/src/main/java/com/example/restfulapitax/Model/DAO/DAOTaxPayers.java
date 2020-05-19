package com.example.restfulapitax.Model.DAO;

import com.example.restfulapitax.Model.people;
import com.example.restfulapitax.Model.taxPayer;

import java.sql.*;

public class DAOTaxPayers {

    private static Statement statement;

    private static boolean execute(String sql) {
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

    private static ResultSet executeQuery(String sql) {
        try {
            System.out.println(sql);
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("!!!! sql error !!!!");
            e.printStackTrace();
        }
        return null;
    }

    // kiểm tra một mã số thuế đã tồn tại chưa
    public static boolean isExistTaxCode (Connection connection, taxPayer p) {
        try {
            statement = connection.createStatement();
            String sql = "SELECT taxCode FROM taxpayers WHERE taxCode = '" + p.getTaxCode() + "'";
            ResultSet rs = executeQuery(sql);
            rs.next();
            rs.getString("taxCode");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // đăng ký ,nếu không thêm đc thì trả về false , thêm đc trả về true
    public static boolean addTaxPayer(Connection connection, taxPayer p) {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        String sql = "INSERT INTO taxpayers VALUES (" + p.toDataAllSql() + ")";
        return execute(sql);
    }

    // đăng nhập ( cần taxCode và passwork) trả về đối tượng account đầy đủ thông tin trừ passwork,
    // không lấy đc trả về đối tượng account có các thông tin là null
    public static taxPayer getTaxPayer (Connection connection ,taxPayer tp) {
        taxPayer result = new taxPayer();
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM taxpayers WHERE taxCode='" + tp.getTaxCode() + "' AND passwork='" + tp.getPasswork() + "'";
            ResultSet rs = executeQuery(sql);
            rs.next();

            long taxCode = rs.getLong(1);
            String passwork = "*********";
            String email = rs.getString(3);
            Date startDay = Date.valueOf(rs.getString(4));
            Date endDay = Date.valueOf(rs.getString(5));
            String taxAuthorities = rs.getString(6);
            String bank = rs.getString(7);
            long idAccountBank = rs.getLong(8);
            String description = rs.getString(9);

            long idCard = rs.getLong(10);
            String name = rs.getString(11);
            Date dateOfBirth = Date.valueOf(rs.getString(12));
            byte sex = rs.getByte(13);
            String address = rs.getString(14);
            long numberPhone = rs.getLong(15);

            people p = new people(idCard, name, dateOfBirth, sex, address, numberPhone);
            result = new taxPayer(p, taxCode, passwork, email, startDay, endDay, taxAuthorities, bank, idAccountBank,description);

        } catch (Exception e) {
            System.out.println("không đăng nhập được !!!");
            e.printStackTrace();
        }
        return result;
    }

    private DAOTaxPayers() {
    }
}
