package com.diamond.myvolley.http;

import com.diamond.myvolley.http.interfaces.IHttpListener;
import com.diamond.myvolley.http.interfaces.IHttpService;
import com.diamond.myvolley.http.interfaces.RequestType;

import java.util.HashMap;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/11/28 下午5:22
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/11/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class RequestHolder<T> {


    private String mRequestType;
    /**
     * 请求参数
     */
    private T mRequestParams;

    /**
     * 执行下载类
     */
    private IHttpService mHttpService;

    /**
     * 回调结果类
     */
    private IHttpListener mHttpListener;

    /**
     * 请求 url
     */
    private String mUrl;

    private HashMap<String, String> mRequestHeader = new HashMap<>();

    public RequestHolder(T requestParams, IHttpService httpService, IHttpListener httpListener, String url, @RequestType String type) {
        mRequestParams = requestParams;
        mHttpService = httpService;
        mHttpListener = httpListener;
        mUrl = url;
        mRequestHeader.putAll(Volley.mGlobalHeader);
        mRequestType = type;
    }

    public T getRequestParams() {
        return mRequestParams;
    }

    public void setRequestParams(T requestParams) {
        mRequestParams = requestParams;
    }

    public IHttpService getHttpService() {
        return mHttpService;
    }

    public void setHttpService(IHttpService httpService) {
        mHttpService = httpService;
    }

    public IHttpListener getHttpListener() {
        return mHttpListener;
    }

    public void setHttpListener(IHttpListener httpListener) {
        mHttpListener = httpListener;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public void addRequestHeader(String key, String valus) {
        mRequestHeader.put(key, valus);
    }

    public HashMap<String, String> getRequestHeader() {
        return mRequestHeader;
    }

    public void setRequestHeader(HashMap<String, String> requestHeader) {
        mRequestHeader = requestHeader;
    }

    public String getRequestType() {
        return mRequestType;
    }

    public void setRequestType(String requestType) {
        mRequestType = requestType;
    }

}
