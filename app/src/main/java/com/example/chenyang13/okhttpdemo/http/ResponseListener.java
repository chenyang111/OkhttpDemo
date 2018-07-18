package com.example.chenyang13.okhttpdemo.http;

/**
 * Created by chenyang13 on 2018/7/17.
 */

public abstract class ResponseListener<T> {
    //成功返回
    public abstract void onSuccess(T response);
    //失败返回
    public abstract void onFailure(String content,String err);
}
