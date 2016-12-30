package com.yyyu.androidstydemo.about_widget.custom;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.yyyu.androidstydemo.R;
import com.yyyu.androidstydemo.about_widget.custom.md_progress.MaterialProgress;
import com.yyyu.androidstydemo.common.activity.BaseActivity;

/**
 * 功能：
 *
 * Created by yyyu on 2016/12/30.
 */

public class CustomWidgetActivity extends BaseActivity{


    private static final String TAG = "CustomWidgetActivity";

    private MaterialProgress mpTest;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_custom_widget;
    }

    @Override
    protected void initView() {
        mpTest = getView(R.id.mp_test);
    }

    @Override
    protected void initListener() {

    }

    boolean isSet= false;
    public void animStart(View view){
        Log.e(TAG, "animStart: ==="+isSet);
        if(!isSet){
           Animation animation = AnimationUtils.loadAnimation(this , R.anim.move_away);
            mpTest.startAnimation(animation);
            isSet = true;
        }else{
            Animation animation = AnimationUtils.loadAnimation(this , R.anim.move_back);
            mpTest.startAnimation(animation);
            isSet = false;
        }
    }

}
