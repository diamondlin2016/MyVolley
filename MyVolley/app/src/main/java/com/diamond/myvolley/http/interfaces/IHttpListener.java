package com.diamond.myvolley.http.interfaces;

import org.apache.http.HttpEntity;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/11/28 下午5:06
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/11/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public interface IHttpListener {

    /**
     * 网络请求成功回调
     *
     * @param httpEntity 网络请求返回结果
     */
    void onSuccess(HttpEntity httpEntity);

    void onFail(int errorCode, String errorMsg);
}
