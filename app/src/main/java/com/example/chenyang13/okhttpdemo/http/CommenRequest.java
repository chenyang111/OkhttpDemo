package com.example.chenyang13.okhttpdemo.http;


import android.app.DownloadManager;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by chenyang13 on 2018/7/16.
 */

//网络请求入参
public class CommenRequest {

    //
    public static Request createGetRequest(String url,RequestParams params){

        StringBuilder urlBuilder = new StringBuilder(url).append("?");

        if (null != params){
            for (Map.Entry<String,String> entry:params.urlParams.entrySet()){
                urlBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");

            }
        }

        return  new Request.Builder().url(urlBuilder.substring(0,urlBuilder.length()-1))
                .get().build();

    }

     public static Request createPostRequset(String url,RequestParams params){
         FormBody.Builder mFormBodyBuilder = new FormBody.Builder();

         if (null != params){

             for (Map.Entry<String,String> entry :params.urlParams.entrySet()){
                 //将请求参数逐一遍历添加到请求构建类中
                 mFormBodyBuilder.add(entry.getKey(),entry.getValue());
             }
         }

         FormBody mFormBody = mFormBodyBuilder.build();

         return new Request.Builder().url(url).post(mFormBody).build();
     }

}

