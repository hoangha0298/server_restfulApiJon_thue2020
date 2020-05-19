package com.example.restfulapitax.Model.DAO.TestDatabase;

import com.example.restfulapitax.Model.DAO.DAOCreateDataBase;
import com.example.restfulapitax.Model.DAO.DAODeclareTax;
import com.example.restfulapitax.Model.DAO.DAOTaxPayers;
import com.example.restfulapitax.Model.DAO.StartConnect;
import com.example.restfulapitax.Model.declareTax;
import com.example.restfulapitax.Model.people;
import com.example.restfulapitax.Model.taxPayer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

public class TestDataBase {
    private static String hostName = "localhost";
    private static String userName = "root";
    private static String password = "";
    private static String dbName = "thue2020";

    public static void main(String[] args) {
        Connection connection = StartConnect.getConnection(hostName, userName, password);
        DAOCreateDataBase.run(connection, dbName);
        StartConnect.selectedDatabase(dbName);

        taxPayer tp = createTaxPayerExamp();

        // test add user
        System.out.println(DAOTaxPayers.isExistTaxCode(connection, tp));
        DAOTaxPayers.addTaxPayer(connection, tp);
        System.out.println(DAOTaxPayers.isExistTaxCode(connection, tp));

        //test login
        taxPayer tp1 = new taxPayer();
        tp1.setTaxCode(1);
        tp1.setPasswork("11111111");
        System.out.println(DAOTaxPayers.getTaxPayer(connection, tp1));

        // test add declareTax
        declareTax dt = createDeclareTaxExamp();
        DAODeclareTax.addDeclareTax(connection, dt, tp1);

        // test get declareTax
        ArrayList<declareTax> result = DAODeclareTax.getListDeclareTax(connection, tp1);
        for (declareTax d : result) {
            System.out.println(d);
        }

        // test delete declareTax
        declareTax d = new declareTax();
        d.setId(1);
        boolean b = DAODeclareTax.deleteDeclareTax(connection, d);
        System.out.println(b);

        // test get declareTax
        ArrayList<declareTax> results = DAODeclareTax.getListDeclareTax(connection, tp1);
        for (declareTax d1 : results) {
            System.out.println(d1);
        }
    }

    public static declareTax createDeclareTaxExamp() {

        long id = 0;
        Date taxPeriod = new Date(System.currentTimeMillis());
        byte times = 1;
        String fax = "adlei3493sdflk";
        long totalIncome = 9000000;
        long minusYourSefl = 0;
        long minusDependentPerson = 0;
        long minusCharity = 0;
        long minusInsurrance = 0;
        Date dateCreate = new Date(System.currentTimeMillis());

        declareTax result = new declareTax(id, taxPeriod, times, fax, totalIncome,
                minusYourSefl, minusDependentPerson, minusCharity, minusInsurrance, dateCreate);

        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("Chuyển đổi đối tượng thành chuỗi JSON với Format:");
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            System.out.println(jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static taxPayer createTaxPayerExamp() {
        long taxCode = 1;
        String passwork = "11111111";
        String email = "@gmail.com";

        Date startDay = Date.valueOf("2020-12-22");
        Date endDay = Date.valueOf("2020-12-22");
        String taxAuthorities = "co quan thue Phu Tho";
        String bank = "vp bank";
        long idAccountBank = 12954357;
        String description = "dang hoat dong";

        long idCard = 132321345;
        String name = "hoang duy ha";
        Date dateOfBirth = Date.valueOf("1998-2-24");
        byte sex = 0;
        String address = "Phu Tho";
        long numberPhone = 123456789;

        people p = new people(idCard, name, dateOfBirth, sex, address, numberPhone);
        taxPayer tp = new taxPayer(p, taxCode, passwork, email, startDay, endDay, taxAuthorities, bank, idAccountBank,description);

        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("Chuyển đổi đối tượng thành chuỗi JSON với Format:");
            String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tp);
            System.out.println(jsonInString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return tp;
    }
}
