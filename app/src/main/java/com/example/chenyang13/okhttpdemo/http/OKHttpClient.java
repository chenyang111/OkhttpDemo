package com.example.chenyang13.okhttpdemo.http;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by chenyang13 on 2018/7/17.
 */

public class OKHttpClient {

    private static final int TIME_OUT = 30;//超时
    private static OkHttpClient mOkHttpClient;
    private static OKHttpClient currentOkHttp;

    public static OKHttpClient getInstance(){
        if (null == currentOkHttp){
            currentOkHttp = new OKHttpClient();
        }
        return currentOkHttp;
    }

    private OKHttpClient() {
        //创建mOkHttpClient 对象的构建者
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT,TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT,TimeUnit.SECONDS)
                //允许重定向
                .followRedirects(true);
        mOkHttpClient = okHttpBuilder.build();
    }

//    static {
//        //创建mOkHttpClient 对象的构建者
//        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
//        okHttpBuilder
//                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
//                .readTimeout(TIME_OUT,TimeUnit.SECONDS)
//                .writeTimeout(TIME_OUT,TimeUnit.SECONDS)
//                //允许重定向
//                .followRedirects(true);
//        mOkHttpClient = okHttpBuilder.build();
//    }

    public<T>  Call get(Request request,ResponseListener<T> responseListener){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new JsonCallback(responseListener));

        return call;
    }

    public<T>  void getRequest(String url,RequestParams params,
                               ResponseListener<T> responseListener){

        get(CommenRequest.createGetRequest(url,params),responseListener);

    }


}
