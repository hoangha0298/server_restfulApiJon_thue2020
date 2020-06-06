package com.example.restfulapitax.Model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class declareTax {
    private long id;
    private Date taxPeriod;
    private byte times;
    private String fax;
    private long totalIncome;
    private long minusYourSefl;         // bản thân
    private long minusDependentPerson;  // người phụ thuộc
    private long minusCharity;          // tử thiện
    private long minusInsurance;       // bảo hiểm
    private Date dateCreate;
    private Date paymentDate;

    // mặc định khởi tạo các trường tiền = 0
    public declareTax() {
        totalIncome = 0;
        minusYourSefl = 0;
        minusDependentPerson = 0;
        minusCharity = 0;
        minusInsurance = 0;
    }

    public declareTax(long id, Date taxPeriod, byte times, String fax,
                      long totalIncome, long minusYourSefl, long minusDependentPerson,
                      long minusCharity, long minusInsurance, Date dateCreate, Date paymentDate) {
        this.id = id;
        this.taxPeriod = taxPeriod;
        this.times = times;
        this.fax = fax;
        this.totalIncome = totalIncome;
        this.minusYourSefl = minusYourSefl;
        this.minusDependentPerson = minusDependentPerson;
        this.minusCharity = minusCharity;
        this.minusInsurance = minusInsurance;
        this.dateCreate = dateCreate;
        this.paymentDate = paymentDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTaxPeriod() {
        return taxPeriod;
    }

    public void setTaxPeriod(Date taxPeriod) {
        this.taxPeriod = taxPeriod;
    }

    public byte getTimes() {
        return times;
    }

    public void setTimes(byte times) {
        this.times = times;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public long getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(long totalIncome) {
        this.totalIncome = totalIncome;
    }

    public long getMinusYourSefl() {
        return minusYourSefl;
    }

    public void setMinusYourSefl(long minusYourSefl) {
        this.minusYourSefl = minusYourSefl;
    }

    public long getMinusDependentPerson() {
        return minusDependentPerson;
    }

    public void setMinusDependentPerson(long minusDependentPerson) {
        this.minusDependentPerson = minusDependentPerson;
    }

    public long getMinusCharity() {
        return minusCharity;
    }

    public void setMinusCharity(long minusCharity) {
        this.minusCharity = minusCharity;
    }

    public long getMinusInsurance() {
        return minusInsurance;
    }

    public void setMinusInsurance(long minusInsurance) {
        this.minusInsurance = minusInsurance;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public long caculatorTaxPay () {
        long totalIncomeForTax = totalIncome - minusYourSefl - minusDependentPerson - minusCharity - minusInsurance;
        if (totalIncomeForTax <=0) return 0;

        System.out.println("totalIncomeForTax = " + totalIncomeForTax);

        if (totalIncomeForTax <= 5000000) return (long) (totalIncomeForTax * 0.05);
        else if (totalIncomeForTax <= 10000000) return (long) (totalIncomeForTax * 0.1 - 250000);
        else if (totalIncomeForTax <= 18000000) return (long) (totalIncomeForTax * 0.15 - 750000);
        else if (totalIncomeForTax <= 32000000) return (long) (totalIncomeForTax * 0.2 - 1950000);
        else if (totalIncomeForTax <= 52000000) return (long) (totalIncomeForTax * 0.25 - 4750000);
        else if (totalIncomeForTax <= 80000000) return (long) (totalIncomeForTax * 0.3 - 9750000);
        else return (long) (totalIncomeForTax * 0.35 - 18150000);

    }

    @Override
    public String toString() {
        return "declareTax{" +
                "id=" + id +
                ", taxPeriod=" + taxPeriod +
                ", times=" + times +
                ", fax='" + fax + '\'' +
                ", totalIncome=" + totalIncome +
                ", minusYourSefl=" + minusYourSefl +
                ", minusDependentPerson=" + minusDependentPerson +
                ", minusCharity=" + minusCharity +
                ", minusInsurance=" + minusInsurance +
                ", dateCreate=" + dateCreate +
                ", paymentDate=" + paymentDate +
                '}';
    }

    // chuyển dữ liệu sang kiểu string để chạy sql , dùng reflection
    public String toDataAddSql() {
        return
        "'" + id + "'," +
        "'" + taxPeriod + "'," +
        "'" + times + "'," +
        "'" + fax + "'," +
        "'" + totalIncome + "'," +
        "'" + minusYourSefl + "'," +
        "'" + minusDependentPerson + "'," +
        "'" + minusCharity + "'," +
        "'" + minusInsurance + "'," +
        "'" + dateCreate + "'," +
        "'" + paymentDate + "'";
    }
}
