package com.example.restfulapitax.Service;

import com.example.restfulapitax.Repository.DAO.DAODeclareTax;
import com.example.restfulapitax.Model.declareTax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

@Service
public class DeclareTaxService {
    @Autowired
    DAODeclareTax daoDeclareTax;

    // paymentDate = 1/1/1970
    public boolean add (declareTax dt, long taxCode){
        dt.setPaymentDate(new Date(0));
        return daoDeclareTax.add(dt, taxCode);
    }

    public ArrayList<declareTax> getListByTaxCode (long taxCode) {
        return daoDeclareTax.getListByTaxCode(taxCode);
    }

    public int deleteById(long id) {
        return daoDeclareTax.deleteById(id);
    }
}
