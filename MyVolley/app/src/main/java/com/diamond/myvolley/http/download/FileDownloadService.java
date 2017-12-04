package com.diamond.myvolley.http.download;

import com.diamond.myvolley.http.AbstractHttpService;

import org.apache.http.HttpResponse;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/12/1 下午3:10
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/12/1      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class FileDownloadService extends AbstractHttpService {

    @Override
    protected void handleResponse(HttpResponse response) {
        int code = response.getStatusLine().getStatusCode();
        if (code == 200 || code == 206) {
            mHttpListener.onSuccess(response.getEntity());
        } else {
            mHttpListener.onFail(code, "error");
        }
    }

}
