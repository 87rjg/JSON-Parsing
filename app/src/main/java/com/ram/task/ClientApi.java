package com.ram.task;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by RAMJEE on 16-02-2018.
 */

public class ClientApi {

    public static final String BASE_URL = "http://www.androidbegin.com/";
    public  static  Retrofit retrofit = null;

    public static Retrofit getClientApi(){


        if(retrofit == null){

            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }



}
