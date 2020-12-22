package com.arsi.flebie;

import android.app.Application;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class Api extends Application {


    public static ApiInterface login(String clientId) {

        // change your base URL
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://139.59.11.135:8080/auth/realms/"+clientId+"/") //Set the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ApiInterface api = adapter.create(ApiInterface.class);
        return api; // return the APIInterface object
    }

    public static ApiInterface baseOne() {
//        SupportInterceptor authAuthenticator = new SupportInterceptor();

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(new TokenAuthenticator());
        // change your base URL
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://139.59.11.135:9006/api/") //Set the Root URL
                .setClient(new OkClient(okHttpClient))
                .build(); //Finally building the adapter
        //Creating object for our interface
        ApiInterface api = adapter.create(ApiInterface.class);
        return api; // return the APIInterface object
    }


    public static ApiInterface baseTwo() {

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(new TokenAuthenticator());

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://139.59.11.135:9001/api/") //Set the Root URL
                .setClient(new OkClient(okHttpClient))
                .build(); //Finally building the adapter
        //Creating object for our interface
        ApiInterface api = adapter.create(ApiInterface.class);
        return api; // return the APIInterface object
    }

    public static ApiInterface baseThree() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(new TokenAuthenticator());

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://139.59.11.135:9004/api/") //Set the Root URL
                .setClient(new OkClient(okHttpClient))
                .build(); //Finally building the adapter
        //Creating object for our interface
        ApiInterface api = adapter.create(ApiInterface.class);
        return api; // return the APIInterface object
    }
}
