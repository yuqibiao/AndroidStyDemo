package com.yyyu.androidstydemo.about_animation.object_animation.advanced;

import android.animation.ValueAnimator;
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
    private ValueAnimator anim;
    /**
     * 新增一个color属性，并提供get set方法。
     */
    private String color;

    public ArcAnimView(Context context) {
        this(context , null);
    }

    public ArcAnimView(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public ArcAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        arcBean = new ArcBean(0 ,130);
        mPaint = new Paint();
        mPaint.setStrokeWidth(6);
        mPaint.setAntiAlias(true);
        color = "#ff00ff";
        mPaint.setColor(Color.parseColor(color));
        //--使用valueAnimator
        anim = ValueAnimator.ofObject
                (new ArcEvaluator(),new ArcBean(0 , 0) , new ArcBean(360 , 360) );
        anim.setInterpolator(new ArcInterpolator());
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.setDuration(2000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                arcBean = (ArcBean) valueAnimator.getAnimatedValue();
                Log.e(TAG, "onAnimationUpdate: ===="+valueAnimator.getAnimatedValue() );
                ArcAnimView.this.postInvalidate();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        rectF = new RectF();
        float arcWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        float arcHeight = MeasureSpec.getSize(heightMeasureSpec) - getPaddingBottom() - getPaddingTop();
        rectF.left = getPaddingLeft();
        rectF.right = arcWidth+ rectF.left;
        rectF.top = getPaddingTop();
        rectF.bottom = arcHeight + rectF.top;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArc(canvas );
    }

    private void drawArc(Canvas canvas) {
        canvas.drawArc(rectF , arcBean.getAngle() , arcBean.getOffset() ,true , mPaint );
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        //Log.e(TAG, "onVisibilityChanged: ==="+visibility );
        super.onVisibilityChanged(changedView, visibility);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if(visibility == VISIBLE){
            anim.start();
        }else{
            anim.cancel();
        }
        Log.e(TAG, "onWindowVisibilityChanged: ====visibility："+visibility );
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        postInvalidate();
    }
}
