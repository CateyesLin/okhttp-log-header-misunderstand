package com.chocolabs.okhttpinterceptordemo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ClientFactory {

    private static HttpLoggingInterceptor genLogInterceptor() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                System.out.println(message);
            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logInterceptor;
    }

    public static OkHttpClient newModifyInApplicationInterceptorLayer() {
        return baseClientBuilder()
                .addInterceptor(new AddTimestampInterceptor())
                .addNetworkInterceptor(genLogInterceptor())
                .addNetworkInterceptor(new LogRealHeaderInterceptor())
                .build();
    }

    public static OkHttpClient newModifyInNetworkInterceptorLayer() {
        return baseClientBuilder()
                .addNetworkInterceptor(new AddTimestampInterceptor())
                .addNetworkInterceptor(genLogInterceptor())
                .addNetworkInterceptor(new LogRealHeaderInterceptor())
                .build();
    }

    public static OkHttpClient.Builder baseClientBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder;
    }
}
