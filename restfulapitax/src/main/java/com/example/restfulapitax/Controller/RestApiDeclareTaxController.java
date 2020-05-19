package com.example.restfulapitax.Controller;

import com.example.restfulapitax.Model.BaseResponse;
import com.example.restfulapitax.Model.DAO.DAODeclareTax;
import com.example.restfulapitax.Model.DAO.InfomationDatabase;
import com.example.restfulapitax.Model.DAO.StartConnect;
import com.example.restfulapitax.Model.taxPayer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.util.List;

import com.example.restfulapitax.Model.*;

@RestController
@RequestMapping(value = "/declare_tax")
public class RestApiDeclareTaxController {
    private Connection conn;

    @PostConstruct
    public void initData(){
        conn = StartConnect.getConnection(
                InfomationDatabase.getHostName(),
                InfomationDatabase.getUserName(),
                InfomationDatabase.getPassword()
        );
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse Add(@PathVariable("taxCode") long taxCode, @RequestBody declareTax dt) {
        BaseResponse response = new BaseResponse();

        taxPayer tp = new taxPayer();
        tp.setTaxCode(taxCode);

        response.setCode("00");
        response.setMessage("thanh cong");
        response.setData(true);


        if (!DAODeclareTax.addDeclareTax(conn, dt, tp))
        {
            response.setCode("01");
            response.setMessage("khong thanh cong");
            response.setData(tp);
        }

        return response;
    }

    @RequestMapping(value = "/list/{taxCode}", method = RequestMethod.GET)
    public BaseResponse List(@PathVariable("taxCode") long taxCode) {

        taxPayer tp = new taxPayer();
        tp.setTaxCode(taxCode);
        List<declareTax> declareTaxes = DAODeclareTax.getListDeclareTax(conn, tp);

        BaseResponse response = new BaseResponse();
        response.setCode("00");
        response.setMessage("thanh cong");
        if (declareTaxes.size() == 0) response.setMessage("thanh cong, list declare tax khong co gi");
        response.setData(declareTaxes);

        return response;
    }
}
