package com.diamond.myvolley.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.BasicResponseHandler;

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

public class JsonHttpService extends AbstractHttpService {

    public JsonHttpService(@RequestType String type) {
        super(type);
    }

    @Override
    public void excute() {
        try {
            httpClient.execute(base, new HttpResponseHandler());
        } catch (IOException e) {
            mHttpListener.onFail(0, e.getMessage());
        }
    }

    private class HttpResponseHandler extends BasicResponseHandler {
        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException {
            if (mHasCancel) {
                return null;
            }
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
