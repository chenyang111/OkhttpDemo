package com.example.chenyang13.okhttpdemo.http;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by chenyang13 on 2018/7/16.
 */
//请求参数
public class RequestParams {

    public ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, Object> fileParams = new ConcurrentHashMap<>();


    public RequestParams() {
        this((Map<String, String>) null);
    }


    public RequestParams(Map<String, String> source) {

        if (null != source) {
            for (Map.Entry<String, String> entry : source.entrySet()) {
                //将url以及参数传入
                putUrlParams(entry.getKey(), entry.getValue());
            }
        }
    }

    public void putUrlParams(String key, String value) {
        if (key != null && value != null) {
            urlParams.put(key, value);
        }
    }
}
