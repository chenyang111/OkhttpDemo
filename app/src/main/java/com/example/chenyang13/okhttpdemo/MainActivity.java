package com.example.chenyang13.okhttpdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chenyang13.okhttpdemo.http.OKHttpClient;
import com.example.chenyang13.okhttpdemo.http.RequestCenter;
import com.example.chenyang13.okhttpdemo.http.RequestProvider;
import com.example.chenyang13.okhttpdemo.http.ResponseListener;
import com.example.chenyang13.okhttpdemo.http.TestClass;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RequestProvider.getInstance().getTest("", new ResponseListener<TestClass>() {
            @Override
            public void onSuccess(TestClass response) {

            }

            @Override
            public void onFailure(String content, String err) {

            }
        });
    }
}
