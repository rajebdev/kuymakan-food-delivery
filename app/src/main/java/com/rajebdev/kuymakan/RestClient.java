package com.rajebdev.kuymakan;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static ApiInterface service;

    public static ApiInterface getService(Context context) {

        if (service == null) {
            // Membuat base URL
            String BASE_URL = "http://192.168.0.103/apikuymakan/";

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder client = new OkHttpClient.Builder().addInterceptor(interceptor);

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

            Retrofit retrofit = builder.client(client.build()).build();

            service = retrofit.create(ApiInterface.class);
        }

        return service;
    }
}
