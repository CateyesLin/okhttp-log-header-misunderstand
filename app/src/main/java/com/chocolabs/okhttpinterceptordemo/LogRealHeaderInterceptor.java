package com.chocolabs.okhttpinterceptordemo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LogRealHeaderInterceptor implements Interceptor {

    private final static String TAG = LogRealHeaderInterceptor.class.getSimpleName();

    private final static Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        System.out.println("===========================");

        System.out.println("Real Header: ");
        OkHttpTool.logRealHeader(originalRequest);

        // 取出原本的 Body 內容
        StringBuilder rawBodyBuilder = new StringBuilder();
        String rawBody = OkHttpTool.getRawBody(originalRequest);
        rawBodyBuilder.append(rawBody);
        System.out.println("Real Body:");
        System.out.println(rawBody);
        System.out.println("===========================");

        return chain.proceed(originalRequest);
    }
}
