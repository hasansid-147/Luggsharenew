package com.android.luggshare.business.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    //public static final String BASE_URL = "http://tnssuet.com.192-185-7-212.hgws27.hgwin.temp.domains/api/";
    public static final String BASE_URL ="http://luggshare-001-site1.htempurl.com/api/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}