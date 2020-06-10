package com.example.thue2020.modal;

public class Server {
//    private static String urlHome = "http://192.168.1.100:8080/";
    private static String urlHome = "http://192.168.42.51:8080/";

    private static String urlTaxpayer = urlHome + "taxpayer/";
    private static String urlTaxpayer_SignUp = urlTaxpayer + "sign_up";
    private static String urlTaxpayer_Login = urlTaxpayer + "login";
    private static String urlTaxPayer_Infomation = urlTaxpayer + "information";
    private static String urlTaxpayer_Edit = urlTaxpayer + "edit";

    private static String urlDeclareTax = urlHome + "declare_tax/";
    private static String urlDeclareTax_Add = urlDeclareTax + "add";
    private static String urlDeclareTax_List = urlDeclareTax + "list";
    private static String urlDeclareTax_Delete = urlDeclareTax + "delete/"; // + id
    private static String urlDeclareTax_Pay = urlDeclareTax + "pay/"; // + id

    public static String getUrlTaxpayer_SignUp() {
        return urlTaxpayer_SignUp;
    }

    public static String getUrlTaxpayer_Login() {
        return urlTaxpayer_Login;
    }

    public static String getUrlTaxPayer_Infomation() {
        return urlTaxPayer_Infomation;
    }

    public static String getUrlTaxpayer_Edit() {
        return urlTaxpayer_Edit;
    }

    public static String getUrlDeclareTax_Add() {
        return urlDeclareTax_Add;
    }

    public static String getUrlDeclareTax_List() {
        return urlDeclareTax_List;
    }

    public static String getUrlDeclareTax_Delete(long id) {
        return urlDeclareTax_Delete + id;
    }

    public static String getUrlDeclareTax_Pay(long id) {
        return urlDeclareTax_Pay + id;
    }
}
