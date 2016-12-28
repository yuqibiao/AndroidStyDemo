package com.yyyu.androidstydemo.about_skill.accessibility_service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yyyu.androidstydemo.R;

/**
 * 功能：前台Service
 *
 * Created by yyyu on 2016/12/27.
 */

public class ForegroundService extends Service{

    @Override
    public void onCreate() {
        super.onCreate();
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("标题 ")
                .setContentText("内容")
                .setTicker("Ticker")
                .setSmallIcon(R.mipmap.icon_locked)
                .setLargeIcon(BitmapFactory.decodeResource(getResources() ,R.mipmap.icon_unlocked))
                .getNotification();
        startForeground(10 , notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
