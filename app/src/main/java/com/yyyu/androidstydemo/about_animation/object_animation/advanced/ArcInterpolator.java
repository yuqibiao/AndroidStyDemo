package com.yyyu.androidstydemo.about_animation.object_animation.advanced;

import android.animation.TimeInterpolator;

/**
 * 功能：自定义插值器
 *
 * Created by yyyu on 2017/1/3.
 */

public class ArcInterpolator implements TimeInterpolator {

    @Override
    public float getInterpolation(float input) {
        float result;
        if(input<0.5){
            result = input;
        }else{
            result = -input;
        }
        return result;
    }
}
