package com.diamond.myvolley.http.download;

import android.os.Environment;
import android.util.Log;

import com.diamond.myvolley.http.HttpTask;
import com.diamond.myvolley.http.RequestHolder;
import com.diamond.myvolley.http.ThreadPoolManger;
import com.diamond.myvolley.http.download.interfaces.IDownloadServiceCallable;
import com.diamond.myvolley.http.interfaces.IHttpListener;
import com.diamond.myvolley.http.interfaces.IHttpService;
import com.diamond.myvolley.http.interfaces.RequestType;

import java.io.File;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/12/1 下午3:04
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/12/1      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class DownLoadFileManger implements IDownloadServiceCallable {
    private static final String TAG = DownLoadFileManger.class.getSimpleName();

    /**
     * 下载
     *
     * @param url
     */
    public void down(String url) {

        synchronized (this) {
            String[] preFix = url.split("/");
            String afterFix = preFix[preFix.length - 1];

            File file = new File(Environment.getExternalStorageDirectory(), afterFix);
            //实例化DownloadItem
            DownloadItemInfo downloadItemInfo = new DownloadItemInfo(url, file);

            IHttpService httpService = new FileDownloadService();

            IHttpListener httpListener = new DownloadListener(downloadItemInfo, this, httpService);

            RequestHolder requestHolder = new RequestHolder(httpService, httpListener, url, RequestType.GET, downloadItemInfo);


            HttpTask httpTask = new HttpTask(requestHolder);

            ThreadPoolManger.getInstance().execute(httpTask);

        }

    }

    @Override
    public void onDownloadStatusChanged(DownloadItemInfo downloadItemInfo) {

    }

    @Override
    public void onTotalLengthReceived(DownloadItemInfo downloadItemInfo) {

    }

    @Override
    public void onCurrentSizeChanged(DownloadItemInfo downloadItemInfo, int percentage, double downLength, long speed) {
        Log.e(TAG, "下载速度：" + speed / 1000 + "k/s");
        Log.e(TAG, "-----路径  " + downloadItemInfo.getFilePath() + "  下载进度度  " + percentage + "%  下载长度  " + bitToKb(downLength) + "   速度  " + speed);
    }

    @Override
    public void onDownloadSuccess(DownloadItemInfo downloadItemInfo) {
        Log.i(TAG, "下载成功    路劲  " + downloadItemInfo.getFilePath() + "  url " + downloadItemInfo.getUrl());
    }

    @Override
    public void onDownloadPause(DownloadItemInfo downloadItemInfo) {

    }

    @Override
    public void onDownloadError(DownloadItemInfo downloadItemInfo, int var2, String var3) {

    }

    private String bitToKb(double length) {
        int count = 0;
        while (length / 1000 > 1) {
            length /= 1000;
            count++;
        }
        switch (count) {
            case 0:
                return length + "b";
            case 1:
                return length + "kb";
            case 2:
                return length + "mb";
            case 3:
                return length + "gb";
        }
        return "";

    }
}
