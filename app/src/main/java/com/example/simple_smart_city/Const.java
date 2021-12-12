package com.example.simple_smart_city;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Const {
    private static String IP = "http://124.93.196.45:10091";
    private String TOKEN = "";

    public static String getIP() {
        return IP;
    }

    public static API getReq() {
        return new Retrofit.Builder().baseUrl(IP + "/").addConverterFactory(GsonConverterFactory.create()).build().create(API.class);
    }

}
