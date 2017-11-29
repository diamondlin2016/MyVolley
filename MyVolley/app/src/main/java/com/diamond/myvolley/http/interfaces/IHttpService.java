package com.diamond.myvolley.http.interfaces;

import java.util.Map;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/11/28 下午5:05
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/11/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public interface IHttpService {

    /**
     * 设置 url
     *
     * @param url url address
     */
    void setUrl(String url);

    /**
     * 设置处理接口
     *
     * @param listener 处理接口
     */
    void setHttpListener(IHttpListener listener);

    /**
     * 设置请求参数
     *
     * @param data 请求参数 byte 数组
     */
    void setRequestData(byte[] data);

    /**
     * 执行请求
     */
    void excute();

    void cancel();

    boolean isCancel();

    void setRequestHeader(Map<String, String> map);

}
