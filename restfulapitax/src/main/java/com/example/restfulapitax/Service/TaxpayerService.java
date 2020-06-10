package com.example.restfulapitax.Service;

import com.example.restfulapitax.Model.User;
import com.example.restfulapitax.Repository.DAO.DAOTaxPayers;
import com.example.restfulapitax.Model.TaxPayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class TaxpayerService {
    @Autowired
    private DAOTaxPayers daoTaxPayers;

    // bỏ trống ngày thì ngày tự động về 1/1/1970
    public boolean add(TaxPayer p) {
        if (p.getEndDay() == null) p.setEndDay(new Date(0));
        return daoTaxPayers.add(p);
    }

    public boolean isExistByTaxCode (long taxCode) {
        return daoTaxPayers.isExistByTaxCode(taxCode);
    }

    // check sql injection
    public boolean checkLogin (User user) {
        for (char c : user.getPassword().toCharArray()) {
            if (c == '\'') return false;
        }
        return daoTaxPayers.checkLogin(user.getUsername(), user.getPassword());
    }

    public User loadUserByTaxCode(Long username) {
        return daoTaxPayers.loadUserByUsername(username);
    }

    public TaxPayer getInfomationByTaxCode (long taxCode) {
        return daoTaxPayers.getInfomationByTaxCode(taxCode);
    }

    public int edit(TaxPayer p) {
        return daoTaxPayers.edit(p);
    }

    // numberAddition có thể < 0 (taxpayer thanh toán)
    // balance >= 0
    public int additionOperationBalance(long taxCode, long numberAddition) {
        long balance = daoTaxPayers.getInfomationByTaxCode(taxCode).getBalance();
        balance += numberAddition;
        if (balance<0) return 0;
        else return daoTaxPayers.setBalance(taxCode, balance);
    }

    public int deleteById(long id) {
        return deleteById(id);
    }

}
