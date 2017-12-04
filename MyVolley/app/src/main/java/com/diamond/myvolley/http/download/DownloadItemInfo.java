package com.diamond.myvolley.http.download;

import com.diamond.myvolley.http.HttpTask;
import com.diamond.myvolley.http.download.interfaces.DownloadStatus;

import java.io.File;
import java.util.HashMap;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/12/1 下午3:31
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/12/1      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class DownloadItemInfo extends BaseEntity<DownloadItemInfo> {

    private long currentLength;

    private long totalLength;

    private String url;

    private String filePath;

    private transient HttpTask httpTask;

    //下载的状态
    @DownloadStatus
    private int status;


    public DownloadItemInfo(String url, File file) {
        this.url = url;
        this.filePath = file.getAbsolutePath();
    }

    public long getCurrentLength() {
        return currentLength;
    }

    public void setCurrentLength(long currentLength) {
        this.currentLength = currentLength;
    }

    public long getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public HttpTask getHttpTask() {
        return httpTask;
    }

    public void setHttpTask(HttpTask httpTask) {
        this.httpTask = httpTask;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(@DownloadStatus int status) {
        this.status = status;
    }

    public void putContentRange(HashMap<String, String> requestHeader) {
        if (currentLength > 0) {
            requestHeader.put("Range", "bytes=" + currentLength + "-");
        }
    }
}
