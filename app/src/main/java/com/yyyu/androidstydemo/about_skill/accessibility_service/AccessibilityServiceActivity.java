package com.yyyu.androidstydemo.about_skill.accessibility_service;

import android.content.Intent;
import android.provider.Settings;
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

    @Override
    protected int setLayoutId() {
        return R.layout.activity_accesibility_service;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

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
