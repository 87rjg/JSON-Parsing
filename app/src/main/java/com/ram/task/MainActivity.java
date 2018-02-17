package com.ram.task;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager  mLayoutManager;
    private RecyclerAdapter mAdapter;
    private ProgressDialog mProgress;

    private ArrayList<Country> countries;

    private ClientInterfaceApi clientInterfaceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Loading...");

         countries = new ArrayList<>();

        mRecyclerView = findViewById(R.id.recyclerView);
         mLayoutManager = new LinearLayoutManager(this);
         mRecyclerView.setLayoutManager(mLayoutManager);
         mRecyclerView.setHasFixedSize(true);



        // Retrofit retrofit = new Retrofit.Builder().baseUrl()

        mProgress.show();

        if(isNetworkConnected()){
            loadJSON();
        }else{
            mProgress.hide();
            Toast.makeText(this,"Check Internet Connection :)",Toast.LENGTH_LONG).show();


        }




    }


    private void loadJSON() {

        Retrofit retrofit = ClientApi.getClientApi();

        clientInterfaceApi = retrofit.create(ClientInterfaceApi.class);

        Call<JsonResponse> call = clientInterfaceApi.getDetails();

        call.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {

                if(response.isSuccessful()){

                   // JsonResponse jsonResponse = response.body();
                   // countries = new ArrayList<>((jsonResponse.getWorldpopulation()));
                    countries = response.body().getWorldpopulation();

                    mAdapter = new RecyclerAdapter(countries,MainActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                    mProgress.dismiss();
                }else{
                    if(!isNetworkConnected())
                    Toast.makeText(MainActivity.this,"Error Occurred :) Check Internet Connection",Toast.LENGTH_LONG).show();
                    else Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();

                    mProgress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadJSON();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
