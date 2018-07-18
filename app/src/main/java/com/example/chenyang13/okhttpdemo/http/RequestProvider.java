package com.example.chenyang13.okhttpdemo.http;

/**
 * Created by chenyang13 on 2018/7/18.
 */

public class RequestProvider {

    private static RequestProvider mRequestProvider;

    public static RequestProvider getInstance(){
        if (mRequestProvider == null){
            mRequestProvider = new RequestProvider();
        }
        return mRequestProvider;
    }

    private RequestProvider() {
    }

    public void getTest(String url,final ResponseListener<TestClass> listener){

        OKHttpClient.getInstance().getRequest(url,null,listener);

    }
}
