package com.diamond.myvolley.http;

import com.diamond.myvolley.http.interfaces.IHttpListener;
import com.diamond.myvolley.http.interfaces.IHttpService;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;


/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/11/28 下午5:42
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/11/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class JsonHttpService implements IHttpService {

    private String mUrl;
    private IHttpListener mHttpListener;
    private byte[] mData;
    private HttpClient httpClient = new DefaultHttpClient();

    public JsonHttpService() {
    }


    @Override
    public void excute() {
        HttpRequestBase base;
        if (mData == null) {
            base = new HttpGet(mUrl);
        } else {
            base = new HttpPost(mUrl);
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(mData);
            ((HttpPost) base).setEntity(byteArrayEntity);
        }
        try {
            httpClient.execute(base, new HttpRespnceHandler());
        } catch (IOException e) {
            mHttpListener.onFail(0, e.getMessage());
        }
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

    private class HttpRespnceHandler extends BasicResponseHandler {
        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException {
            //响应吗
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                mHttpListener.onSuccess(response.getEntity());
            } else {
                mHttpListener.onFail(code, "error");
            }

            return null;
        }
    }
}