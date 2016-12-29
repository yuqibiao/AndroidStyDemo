package com.yyyu.androidstydemo.about_skill.accessibility_service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 功能：灰色保活
 *
 *思路：开启前台Service但是不让通知栏显示，分api>18和api<18的情况。
 * API < 18，启动前台Service时直接传入new Notification()；
 * API >= 18，同时启动两个id相同的前台Service，然后再将后启动的Service做stop处理；
 *
 * 注意：这种方法只是在用户无感知的情况下开启了一个前台服务，归根到底它只是一个前
 * 台服务，当系统内存过低的时候还是会被回收的。
 *
 *参考：http://www.jianshu.com/p/63aafe3c12af
 *
 * Created by yyyu on 2016/12/29.
 */

public class GrayService extends Service{

    private static final int GRAY_SERVICE_ID = 0001;

    private static final String TAG = "GrayService";

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e(TAG, "onCreate: ===============================" );

        if (Build.VERSION.SDK_INT < 18) {
            //API < 18 ，此方法能有效隐藏Notification上的图标
            startForeground(GRAY_SERVICE_ID, new Notification());
        } else {
            Intent innerIntent = new Intent(this, GrayInnerService.class);
            startService(innerIntent);
            startForeground(GRAY_SERVICE_ID, new Notification());
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Thread.sleep(1000);
                        Log.e(TAG, "run: =================gray service" );
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ===============================" );
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**
     * 给 API >= 18 的平台上用的灰色保活手段
     */
    public static class GrayInnerService extends Service {
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(GRAY_SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

    }

}
