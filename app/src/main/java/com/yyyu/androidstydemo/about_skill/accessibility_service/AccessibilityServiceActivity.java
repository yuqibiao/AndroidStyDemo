package com.yyyu.androidstydemo.about_skill.accessibility_service;

import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.yyyu.androidstydemo.R;
import com.yyyu.androidstydemo.common.activity.BaseActivity;

/**
 * 功能：辅助服务测试界面
 *
 * 笔记在MyAccessibilityService中
 *
 * Created by yyyu on 2016/12/27.
 */

public class AccessibilityServiceActivity extends BaseActivity{

    private static final String TAG = "AccessibilityServiceAct";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_accesibility_service;
    }

    @Override
    protected void beforeInit() {
        //----------开始灰色保活Service
        stopService(new Intent(this , GrayService.class));
        startService(new Intent(this , GrayService.class));
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    public  void stopForegroundService(View view){
        stopService(new Intent(this , ForegroundService.class));
    }

    public void startForegroundService(View view){
        Log.e(TAG, "startForegroundService: ");
        startService(new Intent(this , ForegroundService.class));
    }

    public void addAppLocked(View view){
        Intent intent = new Intent(this , AddLockActivity.class);
        startActivity(intent);
    }

    public void openSettingsScreen(View view){
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
    }

}
