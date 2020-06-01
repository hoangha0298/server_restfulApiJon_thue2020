package com.example.restfulapitax.Model.DAO;

import com.example.restfulapitax.Model.declareTax;
import com.example.restfulapitax.Model.taxPayer;

import java.sql.*;
import java.util.ArrayList;

public class DAODeclareTax {

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

    private static int executeUpdate(String sql) {
        try {
            System.out.println(sql);
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("!!!! sql error !!!!");
            e.printStackTrace();
        }
        return 0;
    }

    //thêm bản khai thuế nếu không thêm đc thì trả về false , thêm đc trả về true
    public static boolean addDeclareTax(Connection connection, declareTax dt, taxPayer tp) {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        String sql = "INSERT INTO declareTax(" +
                " taxPeriod," +
                "  times," +
                "  fax," +
                "  totalIncome," +
                "  minusYourSefl," +
                "  minusDependentPerson," +
                "  minusCharity," +
                "  minusInsurrance," +
                "  dateCreate," +
                "  taxCode" +
                ") VALUES (" + dt.toDataAddSql() + ",'" + tp.getTaxCode() + "')";

        return execute(sql);
    }

    // lấy declare tax ( bằng taxCode) trả về arrayList declare tax
    // không lấy đc trả về đối tượng arrayList là rỗng
    public static ArrayList<declareTax> getListDeclareTax (Connection connection ,taxPayer tp) {
        ArrayList<declareTax> result = new ArrayList<declareTax>();
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM declareTax WHERE taxCode='" + tp.getTaxCode() + "'";
            ResultSet rs = executeQuery(sql);

            while (rs.next()) {
                long id = rs.getLong(1);
                Date taxPeriod = rs.getDate(2);
                byte times = rs.getByte(3);
                String fax = rs.getString(4);
                long totalIncome = rs.getLong(5);
                long minusYourSefl = rs.getLong(6);
                long minusDependentPerson = rs.getLong(7);
                long minusCharity = rs.getLong(8);
                long minusInsurrance = rs.getLong(9);
                Date dateCreate= rs.getDate(10);

                declareTax dt = new declareTax(id, taxPeriod, times, fax, totalIncome, minusYourSefl,
                        minusDependentPerson, minusCharity, minusInsurrance, dateCreate);
                result.add(dt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean deleteDeclareTax(Connection connection, declareTax dt) {
        try {
            statement = connection.createStatement();
            String sql = "DELETE FROM declareTax WHERE id='" + dt.getId() + "'";
            if (executeUpdate(sql) != 0) return true;
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private DAODeclareTax() {
    }
}
