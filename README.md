# 使用說明

1. Clone 此專案後，用 Android Studio 開啟。

2. 執行 test 內的 InterceptorTest，請忽略 HttpLoggingInterceptor 的 Response。

   - testModifyInApplicationInterceptor：將修改 Body 放在 Application Interceptor 內進行。
     - 修改完 Request 後 Content-Length 是錯的，但是 OkHttp 幫忙修正。
     - HttpLoggingInterceptor 和 LogRealHeaderInterceptor 出來的值是一樣的。
   - testModifyInNetworkInterceptor：將修改 Body 放在 Network Interceptor 內進行。
     - 修改完 Request 後 Content-Length 是錯的，OkHttp 不會去修改。
       - 但只要這時候去設定 Header 即可。
     - HttpLoggingInterceptor 和 LogRealHeaderInterceptor 出來的值是不同的。

## Class 說明

- AddTimestampInterceptor：修改 Request Body 用，增加一個 timestamp。
- HttpLoggingInterceptor：OkHttp 提供 Log Request/Response 的工具。
- LogRealHeaderInterceptor：把真正送出的 Request Header Log 出來的工具。

