package com.example.restfulapitax.Service;

import com.example.restfulapitax.Model.*;
import com.example.restfulapitax.Repository.DAO.DAODeclareTax;
import com.example.restfulapitax.Repository.DAO.DAOTaxPayers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

@Service
public class DeclareTaxService {
    @Autowired
    DAODeclareTax daoDeclareTax;
    @Autowired
    DAOTaxPayers daoTaxPayers;


    // paymentDate = 1/1/1970
    public boolean add (declareTax dt, long taxCode){
        dt.setPaymentDate(new Date(0));
        return daoDeclareTax.add(dt, taxCode);
    }

    public ArrayList<declareTax> getListByTaxCode (long taxCode) {
        return daoDeclareTax.getListByTaxCode(taxCode);
    }

    // đã thanh toán thì k thanh toán nữa
    // không đủ tiền k thanh toán
    public int payById(long taxCode, long idDeclateTax) {
        declareTax dt = daoDeclareTax.getById(idDeclateTax);
        if(dt == null || !dt.getPaymentDate().equals(new Date(0))) return 0;

        long money = dt.caculatorTaxPay();

        TaxPayer tp = daoTaxPayers.getInfomationByTaxCode(taxCode);
        long balance = tp.getBalance();
        if (balance < money) return 0;
        balance -= money;

        Date paymentDate = new Date(System.currentTimeMillis());

        daoTaxPayers.setBalance(taxCode, balance);
        return daoDeclareTax.setPaymentDateById(idDeclateTax, paymentDate);
    }

    // declare đã thanh toán thì không xóa
    public int deleteById(long id) {
        declareTax dt = daoDeclareTax.getById(id);
        if(dt == null) return 0;

        if (!dt.getPaymentDate().equals(new Date(0))) return 0;

        return daoDeclareTax.deleteById(id);
    }
}
