package com.diamond.myvolley.http.interfaces;

import android.support.annotation.StringDef;

import static com.diamond.myvolley.http.interfaces.RequestType.GET;
import static com.diamond.myvolley.http.interfaces.RequestType.POST;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/11/29 下午4:19
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/11/29      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

@StringDef({GET, POST})
public @interface RequestType {
    String GET = "get";
    String POST = "post";
}
