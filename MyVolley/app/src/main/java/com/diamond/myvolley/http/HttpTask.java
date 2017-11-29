package com.diamond.myvolley.http;

import com.alibaba.fastjson.JSON;
import com.diamond.myvolley.http.interfaces.IHttpService;

import java.io.UnsupportedEncodingException;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/11/28 下午5:30
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/11/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class HttpTask<T> implements Runnable {

    private IHttpService mHttpService;

    public HttpTask(RequestHolder<T> holder) throws UnsupportedEncodingException {
        mHttpService = holder.getHttpService();
        mHttpService.setHttpListener(holder.getHttpListener());
        mHttpService.setUrl(holder.getUrl());
        mHttpService.setRequestHeader(holder.getRequestHeader());
        T requestParams = holder.getRequestParams();
        if (requestParams != null) {
            String requestInfo = JSON.toJSONString(requestParams);
            mHttpService.setRequestData(requestInfo.getBytes("UTF-8"));
        }
    }

    @Override
    public void run() {
        if (!mHttpService.isCancel()) {
            mHttpService.excute();
        }
    }
}
