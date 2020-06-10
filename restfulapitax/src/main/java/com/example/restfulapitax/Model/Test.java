package com.example.restfulapitax.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Date;

public class Test {
    public static void main(String[] args) {
        Date d = new Date(0);
        System.out.println(d.toString());

        declareTax dt = createdeclareTaxExamp();
        TaxPayer tp = createTaxPayerExamp();
        caculatorTax();
    }

    public static declareTax createdeclareTaxExamp() {

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
                minusYourSefl, minusDependentPerson, minusCharity, minusInsurrance, dateCreate, null);

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

    public static TaxPayer createTaxPayerExamp() {
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
        TaxPayer tp = new TaxPayer
                (p, taxCode, passwork, email, startDay, endDay, taxAuthorities, bank, idAccountBank,description, 0, null);

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

    public static void caculatorTax() {
        declareTax dt = new declareTax();
        dt.setTotalIncome(21370000);
        dt.setMinusYourSefl(9000000);
        dt.setMinusDependentPerson(3600000);
        dt.setMinusInsurance(2100000);
        System.out.println(dt.caculatorTaxPay());
    }
}
