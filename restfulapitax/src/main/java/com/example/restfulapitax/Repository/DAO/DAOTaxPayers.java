package com.example.restfulapitax.Repository.DAO;

import com.example.restfulapitax.Model.User;
import com.example.restfulapitax.Model.people;
import com.example.restfulapitax.Model.taxPayer;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DAOTaxPayers extends DAO_CRUD {

    // thêm taxpayer ,nếu không thêm đc thì trả về false , thêm đc trả về true
    // thêm trường balance = 0
    public boolean add(taxPayer p) {
        String sql = "INSERT INTO taxpayers VALUES (" + p.toDataAllSql() + ")";
        return execute(sql);
    }

    // kiểm tra một mã số thuế đã tồn tại chưa
    public boolean isExistByTaxCode (long taxCode) {
        try {
            String sql = "SELECT taxCode FROM taxpayers WHERE taxCode = '" + taxCode + "'";
            ResultSet rs = executeQuery(sql);
            rs.next();
            rs.getString("taxCode");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // login, kiểm tra taxcode và password
    public boolean checkLogin (long taxCode, String password) {
        try {
            String sql = "SELECT * FROM taxpayers WHERE taxCode = '" + taxCode + "' AND password = '" + password + "'";
            ResultSet rs = executeQuery(sql);
            rs.next();
            rs.getString("password");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // lấy taxcode, passwork
    public User loadUserByUsername (long taxCode) {
        User user = null;
        try {
            String sql = "SELECT * FROM taxpayers WHERE taxCode='" + taxCode + "'";
            ResultSet rs = executeQuery(sql);
            rs.next();

            String password = rs.getString("password");
            user = new User(taxCode, password);
            user.setRoles(new String[] { "ROLE_USER" });
        } catch (Exception e) {
            System.out.println("!!!!!!!! không lấy được thông tin !!!!!!!!");
            e.printStackTrace();
        }
        return user;
    }

    // lấy thông tin taxpayer trả về đối tượng đầy đủ thông tin trừ password,
    // không lấy đc trả về null
    public taxPayer getInfomationByTaxCode (long taxCode) {
        taxPayer result = null;
        try {
            String sql = "SELECT * FROM taxpayers WHERE taxCode='" + taxCode + "'";
            ResultSet rs = executeQuery(sql);
            rs.next();

            String password = "*********";
            String email = rs.getString(3);
            Date startDay = Date.valueOf(rs.getString(4));
            Date endDay = Date.valueOf(rs.getString(5));
            String taxAuthorities = rs.getString(6);
            String bank = rs.getString(7);
            long idAccountBank = rs.getLong(8);
            String description = rs.getString(9);
            long balance = rs.getLong("balance");

            long idCard = rs.getLong(10);
            String name = rs.getString(11);
            Date dateOfBirth = Date.valueOf(rs.getString(12));
            byte sex = rs.getByte(13);
            String address = rs.getString(14);
            long numberPhone = rs.getLong(15);

            people p = new people(idCard, name, dateOfBirth, sex, address, numberPhone);
            result = new taxPayer
                    (p, taxCode, password, email, startDay, endDay, taxAuthorities, bank, idAccountBank,description,balance, null);

        } catch (Exception e) {
            System.out.println("!!!!!!!! không lấy được thông tin !!!!!!!!");
            e.printStackTrace();
        }
        return result;
    }

    // sửa thông tin taxPayer
    // không sửa balance
    public int edit(taxPayer p) {
        String sql = "UPDATE taxpayers SET" +
                " password='" + p.getPassword() + "'" +
                ", email='" + p.getEmail() + "'" +
                ", startDay='" + p.getStartDay() + "'" +
                ", endDay='" + p.getEndDay() + "'" +
                ", taxAuthorities='" + p.getTaxAuthorities() + "'" +
                ", bank='" + p.getBank() + "'" +
                ", idAccountBank='" + p.getIdAccountBank() + "'" +
                ", description='" + p.getDescription() + "'" +
                ", idCard='" + p.getIdCard() + "'" +
                ", name='" + p.getName() + "'" +
                ", dateOfBirth='" + p.getDateOfBirth() + "'" +
                ", sex='" + p.getSex() + "'" +
                ", address='" + p.getAddress() + "'" +
                ", numberPhone='" + p.getNumberPhone() + "'" +
                " WHERE taxCode='" + p.getTaxCode() + "'";
        return executeUpdate(sql);
    }

    public int setBalance(long taxCode, long balance) {
        String sql = "UPDATE taxpayers SET" +
                " balance='" + balance + "'" +
                " WHERE taxCode='" + taxCode + "'";
        return executeUpdate(sql);
    }

    // delete trả về số lượng dòng bị xóa
    public int deleteById(long id) {
        try {
            String sql = "DELETE FROM taxpayers WHERE id='" + id + "'";
            return executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
