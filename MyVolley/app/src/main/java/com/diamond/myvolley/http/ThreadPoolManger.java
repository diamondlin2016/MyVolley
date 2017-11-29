package com.diamond.myvolley.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/11/28 下午5:36
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/11/28      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class ThreadPoolManger {
    private static volatile ThreadPoolManger mInstance;
    private final ExecutorService mExecutorService;

    private ThreadPoolManger() {
        mExecutorService = Executors.newFixedThreadPool(4);
    }

    public static ThreadPoolManger getInstance() {
        if (mInstance == null) {
            synchronized (ThreadPoolManger.class) {
                mInstance = new ThreadPoolManger();
            }
        }
        return mInstance;
    }

    public void execute(HttpTask task) {
        mExecutorService.execute(task);
    }

}
