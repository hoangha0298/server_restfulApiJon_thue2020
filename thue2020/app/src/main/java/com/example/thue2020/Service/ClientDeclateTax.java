package com.example.thue2020.Service;

import com.example.thue2020.modal.Response.BaseResponse;
import com.example.thue2020.modal.Response.BaseResponseDeclareTax;
import com.example.thue2020.modal.Server;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.example.thue2020.modal.*;

public class ClientDeclateTax {
    private static Request request;
    private static RequestBody requestBody;
    private static OkHttpClient client = new OkHttpClient();

    // gửi declaretax, token trả về boolen
    public static boolean add(declareTax dt, String token) {
        requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), ConvertJson.toJson(dt));

        request = new Request.Builder()
                .url(Server.getUrlDeclareTax_Add())
                .addHeader("headerContent-Type", "application/json")
                .addHeader("Authorization", token)
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            BaseResponse baseResponse = ConvertJson.fromJson(response.body().string(), BaseResponse.class);
            if (baseResponse.getCode() == 0) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    // gửi declaretax, token trả về boolen
    public static ArrayList<declareTax> getList(String token) {

        request = new Request.Builder()
                .url(Server.getUrlDeclareTax_List())
                .addHeader("headerContent-Type", "application/json")
                .addHeader("Authorization", token)
                .get()
                .build();

        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            BaseResponseDeclareTax baseResponse = ConvertJson.fromJson(json, BaseResponseDeclareTax.class);
            if (baseResponse.getCode() == 0) return baseResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<declareTax>();
    }

    public static boolean remove(String token, long idDeclareTax) {

        request = new Request.Builder()
                .url(Server.getUrlDeclareTax_Delete(idDeclareTax))
                .addHeader("headerContent-Type", "application/json")
                .addHeader("Authorization", token)
                .delete()
                .build();

        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            BaseResponse baseResponse = ConvertJson.fromJson(json, BaseResponse.class);
            if (baseResponse.getCode() == 0) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    // gửi declaretax, token trả về boolen
    public static boolean pay(String token, long idDeclareTax) {
        requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");

        request = new Request.Builder()
                .url(Server.getUrlDeclareTax_Pay(idDeclareTax))
                .addHeader("headerContent-Type", "application/json")
                .addHeader("Authorization", token)
                .put(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            BaseResponse baseResponse = ConvertJson.fromJson(json, BaseResponse.class);
            if (baseResponse.getCode() == 0) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
