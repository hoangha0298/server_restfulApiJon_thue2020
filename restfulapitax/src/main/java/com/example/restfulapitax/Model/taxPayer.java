package com.example.restfulapitax.Model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class taxPayer extends people implements Serializable {

    private static final long serialVersionUID = 1L;

    private long taxCode;
    private String password;
    private String email;
    private Date startDay;
    private Date endDay;
    private String taxAuthorities;
    private String bank;
    private long idAccountBank;
    private String description;
    private long balance;

    ArrayList<declareTax> declareTaxes;

    public taxPayer() {
    }

    public taxPayer(people p, long taxCode, String password, String email, Date startDay, Date endDay,
                    String taxAuthorities, String bank, long idAccountBank, String description, long balance,
                    ArrayList<declareTax> declareTaxes) {
        super(p);
        this.taxCode = taxCode;
        this.password = password;
        this.email = email;
        this.startDay = startDay;
        this.endDay = endDay;
        this.taxAuthorities = taxAuthorities;
        this.bank = bank;
        this.idAccountBank = idAccountBank;
        this.description = description;
        this.balance = balance;
        this.declareTaxes = declareTaxes;
    }

    public long getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(long taxCode) {
        this.taxCode = taxCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public String getTaxAuthorities() {
        return taxAuthorities;
    }

    public void setTaxAuthorities(String taxAuthorities) {
        this.taxAuthorities = taxAuthorities;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public long getIdAccountBank() {
        return idAccountBank;
    }

    public void setIdAccountBank(long idAccountBank) {
        this.idAccountBank = idAccountBank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<declareTax> getDeclareTaxes() {
        return declareTaxes;
    }

    public void setDeclareTaxes(ArrayList<declareTax> declareTaxes) {
        this.declareTaxes = declareTaxes;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "taxPayer{" +
                "taxCode=" + taxCode +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", startDay=" + startDay +
                ", endDay=" + endDay +
                ", taxAuthorities='" + taxAuthorities + '\'' +
                ", bank='" + bank + '\'' +
                ", idAccountBank=" + idAccountBank +
                ", description='" + description + '\'' +
                ", balance=" + balance +
                ", declareTaxes=" + declareTaxes +
                ", idCard=" + idCard +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", numberPhone=" + numberPhone +
                '}';
    }

    // chuyển dữ liệu sang kiểu string để chạy sql , dùng reflection
    public String toDataAllSql() {
        return
        "'" + taxCode + "'," +
        "'" + password + "'," +
        "'" + email + "'," +
        "'" + startDay + "'," +
        "'" + endDay + "'," +
        "'" + taxAuthorities + "'," +
        "'" + bank + "'," +
        "'" + idAccountBank + "'," +
        "'" + description + "'," +
        "'" + idCard + "'," +
        "'" + name + "'," +
        "'" + dateOfBirth + "'," +
        "'" + sex + "'," +
        "'" + address + "'," +
        "'" + numberPhone + "'," +
        "'" + 0 + "'";
    }

}
