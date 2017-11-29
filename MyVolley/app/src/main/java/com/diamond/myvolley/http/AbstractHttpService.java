package com.diamond.myvolley.http;

import com.diamond.myvolley.http.interfaces.IHttpListener;
import com.diamond.myvolley.http.interfaces.IHttpService;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
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
    protected byte[] mData;
    protected HttpClient httpClient = new DefaultHttpClient();
    /**
     * 是否取消请求
     */
    protected boolean mHasCancel = false;
    private Map<String, String> mRequestHeader;

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
        mData = data;
    }

    public void setRequestHeader(Map<String, String> map) {
        mRequestHeader = map;
    }

    protected void constrcutHeader(HttpRequestBase request) {
        for (String key : mRequestHeader.keySet()) {
            String value = mRequestHeader.get(key);
            request.addHeader(key, value);
        }
    }
}
