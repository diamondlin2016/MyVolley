package com.diamond.myvolley.http;

import com.diamond.myvolley.http.interfaces.IDataListener;
import com.diamond.myvolley.http.interfaces.IHttpListener;
import com.diamond.myvolley.http.interfaces.IHttpService;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/11/28 下午5:18
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/11/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class Volley {
    static Map<String, String> mGlobalHeader = new HashMap<>();

    public static <T, M> IHttpService sendRequest(T requestParams, String url, Class<M> responseClass, IDataListener<M> listener, @AbstractHttpService.RequestType String type) {
        IHttpService jsonHttpService = new JsonHttpService(type);

        IHttpListener jsonDealListener = new JsonDealListener<>(listener, responseClass);

        RequestHolder<T> requestHolder = new RequestHolder<>(requestParams, jsonHttpService, jsonDealListener, url);
        try {
            HttpTask<T> task = new HttpTask<>(requestHolder);
            ThreadPoolManger.getInstance().execute(task);
        } catch (UnsupportedEncodingException e) {
            listener.onFail(0, e.getMessage());
        }
        return jsonHttpService;
    }


    public static void setGlobalHeader(String key, String value) {
        mGlobalHeader.put(key, value);
    }

}
