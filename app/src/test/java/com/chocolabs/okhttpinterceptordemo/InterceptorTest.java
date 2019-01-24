package com.chocolabs.okhttpinterceptordemo;

import org.junit.Test;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class InterceptorTest {


    private Request generateRequest() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("activity", "AndroidTaipei")
                .build();
        Request request = new Request.Builder()
                .url("http://api.linetv.tw/")
                .header("Content-Length", formBody.contentLength() + "")
                .post(formBody)
                .build();
        return request;
    }


    @Test
    public void testModifyInApplicationInterceptor() throws IOException {
        OkHttpClient client = ClientFactory.newModifyInApplicationInterceptorLayer();
        client.newCall(generateRequest()).execute();
    }

    @Test
    public void testModifyInNetworkInterceptor() throws IOException {
        OkHttpClient client = ClientFactory.newModifyInNetworkInterceptorLayer();
        client.newCall(generateRequest()).execute();
    }
}
