package com.ram.task;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by RAMJEE on 16-02-2018.
 */

public interface ClientInterfaceApi {

    @GET("tutorial/jsonparsetutorial.txt")
    Call<JsonResponse> getDetails();
}