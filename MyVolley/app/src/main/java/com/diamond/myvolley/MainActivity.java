package com.diamond.myvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.diamond.myvolley.http.Volley;
import com.diamond.myvolley.http.interfaces.IDataListener;

public class MainActivity extends AppCompatActivity {
    public static final String url = "http://gank.io/api/data/Android/10/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View view) {
        for (int i = 0; i < 1; i++) {
            Volley.sendRequest(null, url, GankResponse.class, new IDataListener<GankResponse>() {
                @Override
                public void onSuccess(GankResponse response) {
                    Log.e("___", "请求成功");
                    Log.e("___", JSON.toJSONString(response));
                }

                @Override
                public void onFail(int errorCode, String errorMsg) {
                    Log.e("___error", errorMsg+"__errorCode:"+errorCode);
                }
            });
        }
    }
}
