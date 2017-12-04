package com.diamond.myvolley.http.download;

import android.os.Handler;
import android.os.Looper;

import com.diamond.myvolley.http.download.interfaces.DownloadStatus;
import com.diamond.myvolley.http.download.interfaces.IDownloadServiceCallable;
import com.diamond.myvolley.http.interfaces.IHttpListener;
import com.diamond.myvolley.http.interfaces.IHttpService;

import org.apache.http.HttpEntity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/12/1 下午3:26
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/12/1      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class DownloadListener implements IHttpListener {

    private final DownloadItemInfo downloadItemInfo;
    private final IDownloadServiceCallable downloadServiceCallable;
    private final IHttpService httpService;
    private final File file;
    private final long breakPoint;

    public DownloadItemInfo getDownloadItemInfo() {
        return downloadItemInfo;
    }

    public IDownloadServiceCallable getDownloadServiceCallable() {
        return downloadServiceCallable;
    }

    public IHttpService getHttpService() {
        return httpService;
    }

    public File getFile() {
        return file;
    }

    public long getBreakPoint() {
        return breakPoint;
    }

    private Handler handler = new Handler(Looper.getMainLooper());

    public DownloadListener(DownloadItemInfo downloadItemInfo,
                            IDownloadServiceCallable downloadServiceCallable,
                            IHttpService httpService) {
        this.downloadItemInfo = downloadItemInfo;
        this.downloadServiceCallable = downloadServiceCallable;
        this.httpService = httpService;
        this.file = new File(downloadItemInfo.getFilePath());
        /**
         * 得到已经下载的长度
         */
        this.breakPoint = file.length();
        downloadItemInfo.setCurrentLength(breakPoint);
    }

    @Override
    public void onSuccess(HttpEntity httpEntity) {
        InputStream inputStream = null;
        try {
            inputStream = httpEntity.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long startTime = System.currentTimeMillis();
        //用于计算每秒多少k
        long speed = 0L;
        //花费时间
        long useTime;
        //下载的长度
        long getLen = 0L;
        //接受的长度
        long receiveLen = 0L;
        //得到下载的长度
        long dataLength = httpEntity.getContentLength();
        //单位时间下载的字节数
        long calcSpeedLen = 0L;
        //总数
        long totalLength = this.breakPoint + dataLength;
        //更新数量
        this.receiveTotalLength(totalLength);
        //更新状态
        this.downloadStatusChange(DownloadStatus.DOWNLOADING);
        byte[] buffer = new byte[1024];
        int count = 0;
        long currentTime;
        BufferedOutputStream bos = null;
        FileOutputStream fos;

        try {
            if (!makeDir(this.getFile().getParentFile())) {
                downloadServiceCallable.onDownloadError(downloadItemInfo, 1, "创建文件夹失败");
            } else {
                fos = new FileOutputStream(this.getFile(), true);
                bos = new BufferedOutputStream(fos);
                int length;
                while ((length = inputStream.read(buffer)) != -1) {
                    if (this.getHttpService().isCancel()) {
                        downloadServiceCallable.onDownloadError(downloadItemInfo, 1, "用户取消了");
                        return;
                    }

                    if (this.getHttpService().isPause()) {
                        downloadServiceCallable.onDownloadError(downloadItemInfo, 2, "用户暂停了");
                        return;
                    }

                    bos.write(buffer, 0, length);
                    getLen += (long) length;
                    receiveLen += (long) length;
                    calcSpeedLen += (long) length;
                    ++count;
                    if (receiveLen * 100L / totalLength >= 1L || count >= 5000) {
                        currentTime = System.currentTimeMillis();
                        useTime = currentTime - startTime;
                        startTime = currentTime;
                        speed = 1000L * calcSpeedLen / useTime;
                        count = 0;
                        calcSpeedLen = 0L;
                        receiveLen = 0L;
                        this.downloadLengthChange(this.breakPoint + getLen, totalLength, speed);
                    }
                }
                bos.close();
                inputStream.close();
                if (dataLength != getLen) {
                    downloadServiceCallable.onDownloadError(downloadItemInfo, 3, "下载长度不相等");
                } else {
                    this.downloadLengthChange(this.breakPoint + getLen, totalLength, speed);
                    this.downloadServiceCallable.onDownloadSuccess(downloadItemInfo.copy());
                }
            }
        } catch (IOException ioException) {
            if (this.getHttpService() != null) {
//                this.getHttpService().abortRequest();
            }
            return;
        } catch (Exception e) {
            if (this.getHttpService() != null) {
//                this.getHttpService().abortRequest();
            }
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFail(int errorCode, String errorMsg) {

    }

    /**
     * 回调  长度的变化
     *
     * @param totalLength
     */
    private void receiveTotalLength(long totalLength) {
        downloadItemInfo.setCurrentLength(totalLength);
        final DownloadItemInfo copyDownloadItemInfo = downloadItemInfo.copy();
        if (downloadServiceCallable != null) {
            synchronized (this.downloadServiceCallable) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        downloadServiceCallable.onTotalLengthReceived(copyDownloadItemInfo);
                    }
                });
            }
        }
    }

    /**
     * 更改下载时的状态
     *
     * @param downloading
     */
    private void downloadStatusChange(@DownloadStatus int downloading) {
        downloadItemInfo.setStatus(downloading);
        final DownloadItemInfo copyDownloadItemInfo = downloadItemInfo.copy();
        if (downloadServiceCallable != null) {
            synchronized (this.downloadServiceCallable) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        downloadServiceCallable.onDownloadStatusChanged(copyDownloadItemInfo);
                    }
                });
            }
        }
    }

    /**
     * 创建文件夹的操作
     *
     * @param parentFile
     * @return 创建是否成功
     */
    private boolean makeDir(File parentFile) {
        return parentFile.exists() && !parentFile.isFile()
                ? parentFile.exists() && parentFile.isDirectory() :
                parentFile.mkdirs();
    }

    private void downloadLengthChange(final long downLength, final long totalLength, final long speed) {

        downloadItemInfo.setCurrentLength(downLength);
        if (downloadServiceCallable != null) {
            final DownloadItemInfo copyDownItemIfo = downloadItemInfo.copy();
            synchronized (this.downloadServiceCallable) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        downloadServiceCallable.onCurrentSizeChanged(copyDownItemIfo, (int) (downLength * 100 / totalLength), downLength, speed);
                    }
                });
            }

        }

    }


}
