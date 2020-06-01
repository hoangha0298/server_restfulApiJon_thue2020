package com.example.restfulapitax.Model.TestModel;
import com.example.restfulapitax.Model.*;

public class testCaculatorTax {
    public static void main(String[] args) {
        declareTax dt = new declareTax();
        dt.setTotalIncome(21370000);
        dt.setMinusYourSefl(9000000);
        dt.setMinusDependentPerson(3600000);
        dt.setMinusInsurance(2100000);
        System.out.println(dt.caculatorTaxPay());
    }
}
