package com.example.restfulapitax.Controller;

import com.example.restfulapitax.Model.BaseResponse;
import com.example.restfulapitax.Model.DAO.DAOTaxPayers;
import com.example.restfulapitax.Model.DAO.InfomationDatabase;
import com.example.restfulapitax.Model.DAO.StartConnect;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.sql.Connection;

import com.example.restfulapitax.Model.*;

@RestController
@RequestMapping(value = "/taxpayer")
public class RestApiTaxpayerController {

    private Connection conn;

    @PostConstruct
    public void initData(){
        conn = StartConnect.getConnection(
                InfomationDatabase.getHostName(),
                InfomationDatabase.getUserName(),
                InfomationDatabase.getPassword()
        );
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponse Login(@RequestBody taxPayer tp) {
        BaseResponse response = new BaseResponse();

        tp = DAOTaxPayers.getTaxPayer(conn, tp);

        response.setCode("00");
        response.setMessage("thanh cong");
        response.setData(tp);

        if (tp.getPasswork() == null)
        {
            response.setCode("01");
            response.setMessage("khong thanh cong");
            response.setData(tp);
        }

        return response;
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public BaseResponse signUp(@RequestBody taxPayer tp) {
        BaseResponse response = new BaseResponse();
        response.setCode("00");
        response.setMessage("thanh cong");
        response.setData(true);

        if(!DAOTaxPayers.addTaxPayer(conn, tp))
        {
            response.setCode("01");
            response.setMessage("khong thanh cong");
            response.setData(false);
        }

        return response;
    }
}
