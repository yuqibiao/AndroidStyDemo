package com.yyyu.androidstydemo.about_animation.view_animation;

import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.yyyu.androidstydemo.R;
import com.yyyu.androidstydemo.common.activity.BaseActivity;

/**
 * 功能：View 动画的测试
 *
 *要点：
 *
 * fillBefore是指动画结束时画面停留在此动画的第一帧;
 *fillAfter是指动画结束是画面停留在此动画的最后一帧。
 *
 * xml配置中，500(只写数字) 表示相对屏幕坐标系的绝对位置，50%（数值+%）表示相对于自身的百分比。
 * 50%p(数值+%+p)表示相对于父容器的百分比。scale动画配置的是浮点数，1.2表示缩放到自身的1.2倍。
 *
 * Animation Set 里面可以再加入 Animation Set
 *
 * setStartOffset 用来设置动画开启的时间，达到延迟执行动画的效果。但是在Alpha动画中setStartOffset
 *透明度会先变为动画后的值。
 *
 *
 * Created by yyyu on 2016/12/30.
 */

public class ViewAnimationTestActivity extends BaseActivity{

    private static final String TAG = "ViewAnimationTestActivi";

    private View vTest;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_test_view_animation;
    }

    @Override
    protected void initView() {
        vTest = getView(R.id.v_test);
    }

    @Override
    protected void initListener() {

    }

    public void testMixUseXml(View view){
        Animation animation = AnimationUtils.loadAnimation(this , R.anim.mix_animation_test);
        vTest.startAnimation(animation);
    }

    /**
     * 一个综合的案例
     *
     * set里面可以再添加set
     *
     * 动画结束后不管setFillAfter(true),View还是在原处，此时当执行一个Translate动画后，
     * 又执行一个Rotate动画并且执行RELATIVE_TO_SELF为0.5f,旋转的位置不是预期中的位
     * 移动画以后View位置的一半，而还是最初View位置的一半处。
     *
     */
    public void testMix(final View view ){

        ViewParent parent = vTest.getParent();
        int pWidth = ((LinearLayout)parent).getWidth();
        int cWidth = vTest.getWidth();

        //1.使用代码的方式

        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF , 0.0f ,
                Animation.RELATIVE_TO_PARENT, 0.5f ,
                Animation.ABSOLUTE , 0 ,
                Animation.ABSOLUTE , 100f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        vTest.startAnimation(translateAnimation);

        final AnimationSet animationSet = new AnimationSet(true); // shareInterpolator true:共享一个interpolator
        animationSet.setInterpolator(new AccelerateInterpolator());
        RotateAnimation rotateAnimation = new RotateAnimation(
                0,//fromDegrees
                720,//toDegrees
                Animation.ABSOLUTE, pWidth/2+cWidth/2,//pivotXType pivotXValue
                Animation.ABSOLUTE , 100+cWidth/2////pivotYType pivotYValue
        );
        /*
        // 达不到想要的，相对位移后位置旋转的目的
        RotateAnimation rotateAnimation = new RotateAnimation(
                0,//fromDegrees
                720,//toDegrees
                Animation.RELATIVE_TO_SELF , 0.5f,//pivotXType pivotXValue
                Animation.RELATIVE_TO_SELF , 0.5f////pivotYType pivotYValue
        );
        */
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f , 1.2f , //fromX toX
                1.0f , 1.2f , //fromY toY
                Animation.RELATIVE_TO_SELF , 0.5f,//pivotXType pivotXValue
                Animation.RELATIVE_TO_SELF , 0.5f////pivotYType pivotYValue
        );
        AlphaAnimation alphaAnimation = new AlphaAnimation(
                0.2f , 1.0f // fromAlpha toAlpha
        );
        animationSet.setStartOffset(1500);
        animationSet.setDuration(1500);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);

        AnimationSet outerSet = new AnimationSet(false);
        outerSet.setFillAfter(true);
        outerSet.addAnimation(translateAnimation);
        outerSet.addAnimation(animationSet);

        vTest.startAnimation(outerSet);

    }

    public void testAlpha(View view){
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setFillAfter(true);
        animationSet.setStartOffset(1000);
        animationSet.setDuration(1000);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f , 1.0f);
        animationSet.addAnimation(alphaAnimation);
        vTest.startAnimation(animationSet);
    }



    /**
     * translate动画
     *
     * scale alpha rotate使用类似
     *
     * @param view
     */
    public void testTranslate(View view){
        //1.通过代码的方式
        /**
         * int fromXType        ：RELATIVE_TO_PARENT | RELATIVE_TO_SELF 表示相对的位置
         * float fromXValue  ：表示X方向的起始位置，具体的值受Type的影响，如当XXType为RELATIVE_TO_PARENT XXValue为.5f
         *                                   表示相对服容器一半的位置。XXType为RELATIVE_TO_SELF  XXValue为0.5f表示当对于自身位置一半处
         *
         * int toXType,
         * float toXValue,
         *
         * int fromYType,
         * float fromYValue,
         *
         * int toYType,
         * float toYValue
         *
         */
        /*
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF , 0.0f ,//相对于自身的位置
                Animation.RELATIVE_TO_PARENT, 0.5f ,//相对父容器一半
                Animation.ABSOLUTE , 0 ,//相对于整个坐标系（绝对位置）
                Animation.ABSOLUTE , 100f);
        translateAnimation.setFillAfter(true);//动画结束后保持动画的位置
        translateAnimation.setDuration(1000);
        translateAnimation.setInterpolator(new AccelerateInterpolator());//设置插值器
        vTest.startAnimation(translateAnimation);//是startAnimation
        */

        //2.通过xml配置文件的方式
        Animation translateAnimation2 = AnimationUtils.loadAnimation(this , R.anim.translate_test);
        vTest.startAnimation(translateAnimation2);

    }

}
