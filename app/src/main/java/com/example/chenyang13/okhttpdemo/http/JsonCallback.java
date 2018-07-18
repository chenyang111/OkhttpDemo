package com.example.chenyang13.okhttpdemo.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by chenyang13 on 2018/7/17.
 */

public class JsonCallback  implements Callback {
    private Handler mHandler;//进行消息转发
    private ResponseListener mListener;

    public JsonCallback(ResponseListener mListener) {
        this.mListener = mListener;
        this.mHandler = new Handler(Looper.getMainLooper()) ;

    }


    @Override
    public void onFailure(Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure("http失败",e.toString());
            }
        });

    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        final String result = response.body().string();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                responseJson(result);
            }
        });

    }

    private void responseJson(Object result) {
      if (null == result && result.toString().trim().equals("")){
          mListener.onFailure("失败码","失败");
          return;
      }

        try {
            JSONObject resultStr = new JSONObject(result.toString());
            if (resultStr.has("0")){
                //取出我们的响应码
                if (resultStr.getInt("0") == 0){

                    Object obj = new Gson().fromJson((String)result,getListenerType(mListener.getClass(),0));

                    if (obj != null){
                        mListener.onSuccess(obj);
                    }else {
                        mListener.onFailure("失败","失败");
                    }

                }else {
                    mListener.onFailure("失败","失败");
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    //拿到这个类的泛型
    private Class getListenerType(Class aClass,int index) {
        Type genType = aClass.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)){
            return  Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0){
            return Object.class;
        }

        if (!(params[index] instanceof Class)){
            return Object.class;
        }

        return (Class) params[index];

    }
}
