package com.chocolabs.okhttpinterceptordemo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddTimestampInterceptor implements Interceptor {

    private final static String TAG = AddTimestampInterceptor.class.getSimpleName();

    private final static Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder();

        System.out.println("Original Header: ");
        OkHttpTool.logRealHeader(originalRequest);

        // 取出原本的 Body 內容
        StringBuilder rawBodyBuilder = new StringBuilder();
        String rawBody = OkHttpTool.getRawBody(originalRequest);
        rawBodyBuilder.append(rawBody);
        System.out.println("Original Body:");
        System.out.println(rawBody);
        System.out.println("===========================");

        // 增加現在時間
        long timestamp = new Date().getTime() / 1000;
        String newRawBody = rawBodyBuilder.append("&t=" + timestamp).toString();
        RequestBody newBody = RequestBody.create(
                MediaType.parse("application/x-www-form-urlencoded"),
                newRawBody);
        requestBuilder.method(originalRequest.method(), newBody);
//        requestBuilder.header("Content-Length", Long.valueOf(body.contentLength()).toString());

        Request newRequest = requestBuilder.build();
        System.out.println("Modified Header:");
        OkHttpTool.logRealHeader(newRequest);
        System.out.println("Modified Body:");
        System.out.println(newRawBody);
        System.out.println("===========================");

        return chain.proceed(newRequest);
    }
}
