package com.arctouch.codechallenge.model;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class TmdbApiSingleton {

    private static TmdbApiSingleton uniqueInstance;
    private static TmdbApi api;

    private TmdbApiSingleton() { }

    public static TmdbApiSingleton getInstance() {
        if(uniqueInstance == null){
            uniqueInstance = new TmdbApiSingleton();
        }
        return uniqueInstance;
    }

    synchronized public TmdbApi getApi() {
        if(api == null){
            api = new Retrofit.Builder()
                    .baseUrl(TmdbApi.URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(TmdbApi.class);
        }
        return api;
    }

}
