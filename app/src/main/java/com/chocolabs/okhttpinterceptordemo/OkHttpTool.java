package com.chocolabs.okhttpinterceptordemo;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

public class OkHttpTool {

    private final static String TAG = OkHttpTool.class.getSimpleName();

    private final static Charset UTF8 = Charset.forName("UTF-8");

    public static void logRealHeader(Request request) {
        Headers headers = request.headers();
        for (int i = 0, count = headers.size(); i < count; i++) {
            System.out.println(headers.name(i) + ": " + headers.value(i));
        }
    }

    public static String getRawBody(Request request) throws IOException {
        RequestBody body = request.body();
        if (null != body) {
            Buffer buffer = new Buffer();
            body.writeTo(buffer);

            MediaType contentType = body.contentType();

            Charset charset = UTF8;
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            return buffer.readString(charset);
        }
        return null;
    }
}
