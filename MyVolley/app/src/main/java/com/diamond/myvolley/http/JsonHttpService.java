package com.diamond.myvolley.http;

import org.apache.http.HttpResponse;


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

public class JsonHttpService extends AbstractHttpService {

    public JsonHttpService() {
    }


    @Override
    protected void handleResponse(HttpResponse response) {
        if (isCancel()) {
            return;
        }
        //响应吗
        int code = response.getStatusLine().getStatusCode();
        if (code == 200) {
            mHttpListener.onSuccess(response.getEntity());
        } else {
            mHttpListener.onFail(code, "error");
        }
    }

}
