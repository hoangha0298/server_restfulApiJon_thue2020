package com.example.thue2020.modal.Response;

import java.util.ArrayList;

import com.example.thue2020.modal.*;

public class BaseResponseDeclareTax extends BaseResponse{
    private ArrayList<declareTax> data;

    @Override
    public ArrayList<declareTax> getData() {
        return data;
    }

    public void setData(ArrayList<declareTax> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponseDeclareTax{" +
                "declareTaxes=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
