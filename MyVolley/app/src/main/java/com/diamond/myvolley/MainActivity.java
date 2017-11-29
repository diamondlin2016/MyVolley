package com.diamond.myvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.diamond.myvolley.http.Volley;
import com.diamond.myvolley.http.interfaces.IDataListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {
        String url = "http://gank.io/api/data/Android/10/1";
        for (int i = 0; i < 50; i++) {
            Volley.sendRequest(null, url, GankResponse.class, new IDataListener<GankResponse>() {
                @Override
                public void onSuccess(GankResponse response) {
                    Log.e("___", "请求成功");
                }

                @Override
                public void onFail(int errorCode, String errorMsg) {
                    Log.e("___error", errorMsg + "__errorCode:" + errorCode);
                }
            });
        }
    }
}
