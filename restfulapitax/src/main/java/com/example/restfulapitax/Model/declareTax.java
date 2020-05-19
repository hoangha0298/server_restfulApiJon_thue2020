package com.example.restfulapitax.Model;

import java.sql.Date;

public class declareTax {
    private long id;
    private Date taxPeriod;
    private byte times;
    private String fax;
    private long totalIncome;
    private long minusYourSefl;
    private long minusDependentPerson;
    private long minusCharity;
    private long minusInsurrance;
    private Date dateCreate;

    public declareTax() {
    }

    public declareTax(long id, Date taxPeriod, byte times, String fax,
                      long totalIncome, long minusYourSefl, long minusDependentPerson,
                      long minusCharity, long minusInsurrance, Date dateCreate) {
        this.id = id;
        this.taxPeriod = taxPeriod;
        this.times = times;
        this.fax = fax;
        this.totalIncome = totalIncome;
        this.minusYourSefl = minusYourSefl;
        this.minusDependentPerson = minusDependentPerson;
        this.minusCharity = minusCharity;
        this.minusInsurrance = minusInsurrance;
        this.dateCreate = dateCreate;
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

    public long getMinusInsurrance() {
        return minusInsurrance;
    }

    public void setMinusInsurrance(long minusInsurrance) {
        this.minusInsurrance = minusInsurrance;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
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
                ", minusInsurrance=" + minusInsurrance +
                ", dateCreate=" + dateCreate +
                '}';
    }

    // chuyển dữ liệu sang kiểu string để chạy sql , dùng reflection
    public String toDataAddSql() {
        return
        "'" + taxPeriod + "'," +
        "'" + times + "'," +
        "'" + fax + "'," +
        "'" + totalIncome + "'," +
        "'" + minusYourSefl + "'," +
        "'" + minusDependentPerson + "'," +
        "'" + minusCharity + "'," +
        "'" + minusInsurrance + "'," +
        "'" + dateCreate + "'";
    }
}
