package com.diamond.myvolley.http.download.interfaces;

import android.support.annotation.IntDef;

import static com.diamond.myvolley.http.download.interfaces.DownloadStatus.DOWNLOADING;
import static com.diamond.myvolley.http.download.interfaces.DownloadStatus.FAILED;
import static com.diamond.myvolley.http.download.interfaces.DownloadStatus.FINISH;
import static com.diamond.myvolley.http.download.interfaces.DownloadStatus.PAUSE;
import static com.diamond.myvolley.http.download.interfaces.DownloadStatus.STARTING;
import static com.diamond.myvolley.http.download.interfaces.DownloadStatus.WAITING;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/11/29 下午4:19
 * Description:下载状态
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/11/29      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

@IntDef({WAITING, STARTING, DOWNLOADING, PAUSE, FINISH, FAILED})
public @interface DownloadStatus {
    int WAITING = 1;
    int STARTING = 2;
    int DOWNLOADING = 3;
    int PAUSE = 4;
    int FINISH = 5;
    int FAILED = 6;
}
