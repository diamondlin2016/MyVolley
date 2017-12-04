package com.diamond.myvolley.http.download.interfaces;

import com.diamond.myvolley.http.download.DownloadItemInfo;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/12/1 下午3:30
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/12/1      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public interface IDownloadServiceCallable {
    void onDownloadStatusChanged(DownloadItemInfo downloadItemInfo);

    void onTotalLengthReceived(DownloadItemInfo downloadItemInfo);

    void onCurrentSizeChanged(DownloadItemInfo downloadItemInfo, int percentage, double downLength, long speed);

    void onDownloadSuccess(DownloadItemInfo downloadItemInfo);

    void onDownloadPause(DownloadItemInfo downloadItemInfo);

    void onDownloadError(DownloadItemInfo downloadItemInfo, int code, String errorMsg);
}
