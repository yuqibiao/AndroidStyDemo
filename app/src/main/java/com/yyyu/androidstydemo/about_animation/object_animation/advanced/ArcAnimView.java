package com.yyyu.androidstydemo.about_animation.object_animation.advanced;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 功能：自定义View
 *
 * Created by yyyu on 2017/1/3.
 */

public class ArcAnimView extends View{

    private static final String TAG = "ArcAnimView";

    private ArcBean arcBean;
    private RectF rectF;
    private Paint mPaint;

    public ArcAnimView(Context context) {
        this(context , null);
    }

    public ArcAnimView(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public ArcAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        arcBean = new ArcBean(30 , 0);
        mPaint = new Paint();
        mPaint.setStrokeWidth(3);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ff00ff"));
        rectF = new RectF();
        float arcWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        float arcHeight = getHeight() - getPaddingBottom() - getPaddingTop();
        rectF.left = getPaddingLeft();
        rectF.right = arcWidth/2+ rectF.left;
        rectF.top = getPaddingTop();
        rectF.bottom = arcHeight/2 + rectF.top;

        Log.e(TAG, "ArcAnimView: ====arcWidth："+arcWidth+"  arcHeight："+arcHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArc(canvas );
    }

    private void drawArc(Canvas canvas) {
        canvas.drawArc(rectF , arcBean.getAngle() , arcBean.getOffset() ,false , mPaint );
    }

    public void stratAnim(){

    }

}
