package com.diamond.myvolley.http;

import android.support.annotation.StringDef;

import com.diamond.myvolley.http.interfaces.IHttpListener;
import com.diamond.myvolley.http.interfaces.IHttpService;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.Map;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/11/29 下午3:22
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/11/29      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public abstract class AbstractHttpService implements IHttpService {
    protected String mUrl;
    protected IHttpListener mHttpListener;
    protected HttpClient httpClient;
    protected HttpRequestBase base;
    /**
     * 是否取消请求
     */
    protected boolean mHasCancel = false;

    public static final String GET = "get";
    public static final String POST = "post";

    @StringDef({GET, POST})
    public @interface RequestType {
    }

    public AbstractHttpService(@RequestType String type) {
        httpClient = new DefaultHttpClient();
        switch (type) {
            case GET:
                base = new HttpGet(mUrl);
                break;
            case POST:
                base = new HttpPost(mUrl);
        }

    }

    @Override
    public void cancel() {
        mHasCancel = true;
    }

    @Override
    public boolean isCancel() {
        return mHasCancel;
    }


    @Override
    public void setUrl(String url) {
        mUrl = url;
    }

    @Override
    public void setHttpListener(IHttpListener listener) {
        mHttpListener = listener;
    }

    @Override
    public void setRequestData(byte[] data) {
        //only POST can add body
        if (base instanceof HttpPost) {
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(data);
            ((HttpPost) base).setEntity(byteArrayEntity);
        }
    }

    public void setRequestHeader(Map<String, String> map) {
        constructHeader(map);
    }

    private void constructHeader(Map<String, String> map) {
        for (String key : map.keySet()) {
            String value = map.get(key);
            base.addHeader(key, value);
        }
    }
}
