package com.example.restfulapitax.Repository.DAO;

import com.example.restfulapitax.Model.declareTax;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class DAODeclareTax extends DAO_CRUD {

    //thêm bản khai thuế nếu không thêm đc thì trả về false , thêm đc trả về true
    // không thêm trường id ( tự tăng), thêm paymentDate = 1/1/1970 (code ở service)
    public boolean add (declareTax dt, long taxCode) {
        String sql = "INSERT INTO declareTax VALUES (" + dt.toDataAddSql() + ",'" + taxCode + "')";
        return execute(sql);
    }

    // lấy declare tax ( bằng taxCode) trả về arrayList declare tax
    // không lấy đc trả về đối tượng arrayList là rỗng
    public ArrayList<declareTax> getListByTaxCode (long taxCode) {
        ArrayList<declareTax> result = new ArrayList<declareTax>();
        try {
            String sql = "SELECT * FROM declareTax WHERE taxCode='" + taxCode + "'";
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
                Date dateCreate = rs.getDate(10);
                Date paymentDate = rs.getDate(11);

                declareTax dt = new declareTax(id, taxPeriod, times, fax, totalIncome, minusYourSefl,
                        minusDependentPerson, minusCharity, minusInsurrance, dateCreate, paymentDate);
                result.add(dt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // lấy declare tax ( bằng id) trả về declare tax
    // không lấy đc trả về null
    public declareTax getById (long id) {
        declareTax dt = null;
        try {
            String sql = "SELECT * FROM declareTax WHERE id='" + id + "'";
            ResultSet rs = executeQuery(sql);
            rs.next();

            Date taxPeriod = rs.getDate(2);
            byte times = rs.getByte(3);
            String fax = rs.getString(4);
            long totalIncome = rs.getLong(5);
            long minusYourSefl = rs.getLong(6);
            long minusDependentPerson = rs.getLong(7);
            long minusCharity = rs.getLong(8);
            long minusInsurrance = rs.getLong(9);
            Date dateCreate = rs.getDate(10);
            Date paymentDate = rs.getDate(11);

            dt = new declareTax(id, taxPeriod, times, fax, totalIncome, minusYourSefl,
                    minusDependentPerson, minusCharity, minusInsurrance, dateCreate, paymentDate);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dt;
    }

    // không thanh toán được trả về false
    public int setPaymentDateById (long id, Date date) {
        String sql = "UPDATE declareTax SET" +
                " paymentDate='" + date + "'" +
                " WHERE id='" + id + "'";
        return executeUpdate(sql);
    }

    // delete trả về số lượng dòng bị xóa
    // không xóa được dòng đã trả tiền (paymentDate != null)
    public int deleteById(long id) {
        try {
            String sql = "DELETE FROM declareTax WHERE id='" + id + "'";
            return executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


}
