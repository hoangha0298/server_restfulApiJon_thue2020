package com.example.restfulapitax.Controller;

import com.example.restfulapitax.Model.BaseResponse;
import com.example.restfulapitax.Service.JwtService;
import com.example.restfulapitax.Service.TaxpayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.restfulapitax.Model.*;

@RestController
@RequestMapping(value = "/taxpayer")
public class ApiTaxpayer {
    @Autowired
    private JwtService jwtService;

    @Autowired
    TaxpayerService taxpayerService;


    // đăng ký
    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public BaseResponse signUp(@RequestBody taxPayer tp) {
        BaseResponse response = new BaseResponse();
        response.setData(tp);
        if (!taxpayerService.add(tp)) {
            response.setCode(99);
            response.setMessage("fail");
        }
        return response;
    }

    // đăng nhập lấy về token
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponse Login(@RequestBody User user) {
        BaseResponse response = new BaseResponse();

        if (taxpayerService.checkLogin(user)) {
            System.out.println("++++++++++++++++++++++");
            String tokenLogin = jwtService.generateTokenLogin(String.valueOf(user.getUsername()));
            response.setData(tokenLogin);
        } else {
            response.setCode(99);
            response.setMessage("Wrong userId and password");
        }

        return response;
    }

    // lấy thông tin taxpayer
    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public BaseResponse information(@RequestHeader String Authorization){
        Long taxCode = Long.valueOf(jwtService.getUsernameFromToken(Authorization));
        BaseResponse response = new BaseResponse();
        response.setData(taxpayerService.getInfomationByTaxCode(taxCode));
        if(response.getData() == null) {
            response.setCode(99);
            response.setMessage("fail");
        }
        return response;
    }

    // sửa thông tin tài khoản
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public BaseResponse Edit(@RequestBody taxPayer tp,
                             @RequestHeader String Authorization) {
        Long taxCode = Long.valueOf(jwtService.getUsernameFromToken(Authorization));
        tp.setTaxCode(taxCode);
        BaseResponse response = new BaseResponse();
        response.setData(tp);
        if(taxpayerService.edit(tp) == 0) {
            response.setCode(99);
            response.setMessage("fail");
        }
        return response;
    }
}
