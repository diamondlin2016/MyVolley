package com.diamond.myvolley.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.diamond.myvolley.http.interfaces.IDataListener;
import com.diamond.myvolley.http.interfaces.IHttpListener;

import org.apache.http.HttpEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/11/28 下午5:48
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/11/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class JsonDealListener<M> implements IHttpListener {
    private String TAG = this.getClass().getSimpleName();

    private final IDataListener<M> mListener;
    private final Class<M> mResponseClass;
    private Handler handler = new Handler(Looper.getMainLooper());

    public JsonDealListener(IDataListener<M> listener, Class<M> responseClass) {
        mListener = listener;
        mResponseClass = responseClass;
    }

    @Override
    public void onSuccess(HttpEntity httpEntity) {
        String content = getContent(httpEntity);
        final M m = JSON.parseObject(content, mResponseClass);
        handler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onSuccess(m);
            }
        });

    }

    @Override
    public void onFail(int errorCode, String errorMsg) {
        mListener.onFail(errorCode, errorMsg);
    }

    private String getContent(HttpEntity httpEntity) {
        try {
            InputStream inputStream = httpEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } catch (IOException e) {
                mListener.onFail(0, e.getMessage());
                Log.e(TAG, "Error=" + e.toString());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error=" + e.toString());
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            mListener.onFail(0, e.getMessage());
        }
        return "";
    }
}
