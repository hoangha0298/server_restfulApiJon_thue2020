package com.example.restfulapitax.Controller;

import com.example.restfulapitax.Model.BaseResponse;
import com.example.restfulapitax.Service.DeclareTaxService;
import com.example.restfulapitax.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

import com.example.restfulapitax.Model.*;

@RestController
@RequestMapping(value = "/declare_tax")
public class ApiDeclareTax {
    @Autowired
    private JwtService jwtService;

    @Autowired
    DeclareTaxService declareTaxService;

    @PostConstruct
    public void initData(){
    }

    // không cần thêm id trong declareTax dbmanager auto tăng
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public BaseResponse Add(@RequestBody declareTax dt,
                            @RequestHeader String Authorization) {
        BaseResponse response = new BaseResponse();
        response.setData(dt);

        Long taxCode = Long.valueOf(jwtService.getUsernameFromToken(Authorization));
        if (!declareTaxService.add(dt, taxCode))
        {
            response.setCode(99);
            response.setMessage("failure");
        }

        return response;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public BaseResponse List(@RequestHeader String Authorization) {
        BaseResponse response = new BaseResponse();

        Long taxCode = Long.valueOf(jwtService.getUsernameFromToken(Authorization));
        List<declareTax> declareTaxes = declareTaxService.getListByTaxCode(taxCode);

        if (declareTaxes.size() == 0) response.setMessage("list declare tax khong co gi");
        response.setData(declareTaxes);

        return response;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public BaseResponse delete(@PathVariable("id") long id) {

        BaseResponse response = new BaseResponse();
        response.setData(true);
        if (declareTaxService.deleteById(id) == 0)
        {
            response.setCode(99);
            response.setMessage("failure");
            response.setData(false);
        }
        return response;
    }

}
