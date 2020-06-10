package com.example.thue2020.modal;

import java.sql.Date;

public class people {
    protected long idCard;
    protected String name;
    protected Date dateOfBirth;
    protected byte sex;
    protected String address;
    protected long numberPhone;

    public people() {
    }

    public people(people p) {
        this.idCard = p.getIdCard();
        this.name = p.getName();
        this.dateOfBirth = p.getDateOfBirth();
        this.sex = p.getSex();
        this.address = p.getAddress();
        this.numberPhone = p.getNumberPhone();
    }

    public people(long idCard, String name, Date dateOfBirth, byte sex, String address, long numberPhone) {
        this.idCard = idCard;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.address = address;
        this.numberPhone = numberPhone;
    }

    public long getIdCard() {
        return idCard;
    }

    public void setIdCard(long idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(long numberPhone) {
        this.numberPhone = numberPhone;
    }

    @Override
    public String toString() {
        return "people{" +
                "idCard=" + idCard +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", numberPhone=" + numberPhone +
                '}';
    }
}
