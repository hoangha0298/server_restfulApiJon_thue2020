package com.example.thue2020.Service;

import com.example.thue2020.modal.*;
import com.example.thue2020.modal.Response.BaseResponse;
import com.example.thue2020.modal.Response.BaseResponseTaxPayer;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ClientTaxPayer {
    private static Request request;
    private static RequestBody requestBody;
    private static OkHttpClient client = new OkHttpClient();


    // gửi user trả về token
    public static String login(User u) {
        requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), ConvertJson.toJson(u));

        request = new Request.Builder()
                .url(Server.getUrlTaxpayer_Login())
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            BaseResponse baseResponse = ConvertJson.fromJson(response.body().string(), BaseResponse.class);
            if (baseResponse.getCode() == 0) return (String)baseResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    // gửi taxpayer trả về boolen
    public static boolean signUp(taxPayer tp) {
        requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), ConvertJson.toJson(tp));

        request = new Request.Builder()
                .url(Server.getUrlTaxpayer_SignUp())
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            BaseResponse baseResponse = ConvertJson.fromJson(response.body().string(), BaseResponse.class);

            System.out.println(baseResponse);
            if (baseResponse.getCode() == 0) return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    // gửi token trả về taxPayer
    public static taxPayer getInfomation(String token) {

        request = new Request.Builder()
                .url(Server.getUrlTaxPayer_Infomation())
                .addHeader("headerContent-Type", "application/json")
                .addHeader("Authorization", token)
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            BaseResponseTaxPayer baseResponse = ConvertJson.fromJson(json, BaseResponseTaxPayer.class);

            if (baseResponse.getCode() == 0) return baseResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
