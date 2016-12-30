package com.yyyu.androidstydemo.about_widget.custom.md_progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.yyyu.androidstydemo.R;

import static android.support.v7.appcompat.R.attr.colorAccent;
import static android.support.v7.appcompat.R.attr.colorPrimary;
import static android.support.v7.appcompat.R.attr.colorPrimaryDark;

/**
 * 功能：
 * Created by yyyu on 2016/12/30.
 */

public class MaterialProgress extends ImageView{

    private static final String TAG = "MaterialProgress";

    private MaterialProgressDrawable mpDrawable;

    public MaterialProgress(Context context) {
        this(context , null);
    }

    public MaterialProgress(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public MaterialProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        int[] colors = new int[3];

        TypedArray ta = context.getTheme().obtainStyledAttributes(new int[]{
                colorPrimary,colorPrimaryDark , colorAccent
        });
        colors[0] = ta.getColor(0,Color.parseColor("#ff00ff"));
        colors[1] = ta.getColor(1,Color.parseColor("#ff00ff"));
        colors[2] = ta.getColor(2,Color.parseColor("#0000ff"));
        ta.recycle();

        mpDrawable = new MaterialProgressDrawable(context , this);
        mpDrawable.setAlpha(0);
        mpDrawable.setBackgroundColor(Color.parseColor("#00ffffff"));
        mpDrawable.setColorSchemeColors(colors);
        mpDrawable.start();
        setBackgroundDrawable(getResources().getDrawable(R.drawable.shadow_circle_bg));
        setImageDrawable(mpDrawable);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        //---解决RecyclerView中回收Item动画停止
        if (visibility == VISIBLE){
            if(!mpDrawable.isRunning()){
                mpDrawable.stop();
                mpDrawable.start();
            }
        }else{
            mpDrawable.stop();
        }
    }

    public MaterialProgressDrawable getMpDrawable(){

        return mpDrawable;
    }


}
