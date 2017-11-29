package com.diamond.myvolley.http;

import com.diamond.myvolley.http.interfaces.IHttpListener;
import com.diamond.myvolley.http.interfaces.IHttpService;

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

    public RequestHolder(T requestParams, IHttpService httpService, IHttpListener httpListener, String url) {
        mRequestParams = requestParams;
        mHttpService = httpService;
        mHttpListener = httpListener;
        mUrl = url;
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
}
