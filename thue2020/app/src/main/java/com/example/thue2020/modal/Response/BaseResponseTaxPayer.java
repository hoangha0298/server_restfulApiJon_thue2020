package com.example.thue2020.modal.Response;

import com.example.thue2020.modal.Response.BaseResponse;
import com.example.thue2020.modal.taxPayer;

public class BaseResponseTaxPayer extends BaseResponse {
    private taxPayer data;

    @Override
    public taxPayer getData() {
        return data;
    }

    public void setData(taxPayer data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponseTaxPayer{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
