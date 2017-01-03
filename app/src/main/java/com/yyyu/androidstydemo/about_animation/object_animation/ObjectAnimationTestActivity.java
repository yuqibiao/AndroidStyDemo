package com.yyyu.androidstydemo.about_animation.object_animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.yyyu.androidstydemo.R;
import com.yyyu.androidstydemo.common.activity.BaseActivity;

/**
 * 功能：属性动画的测试
 *
 *
 *
 * 参考：
 * http://blog.csdn.net/guolin_blog/article/details/43536355（上）
 * http://blog.csdn.net/guolin_blog/article/details/43816093（中）
 * http://blog.csdn.net/guolin_blog/article/details/44171115（下）
 *
 * Created by yyyu on 2017/1/3.
 */

public class ObjectAnimationTestActivity extends BaseActivity {

    private static final String TAG = "ObjectAnimationTestActi";

    private ImageView ivTest;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_test_object_animation;
    }

    @Override
    protected void initView() {
        ivTest = getView(R.id.iv_test);
    }

    @Override
    protected void initListener() {

    }


    /**
     * 6.ViewPropertyAnimator
     *
     * Android 3.1引入 （其它的是Android3.0引入），专门针对View的属性。
     *
     * 没有ObjectAnimator使用的灵活，只是提供了一些简单动画的使用。
     * 不用去调用显示的调用start()，内部已经帮我们调用了。
     *
     * @param view
     */
    public void testViewPropertyAnimator(View view){
        ivTest.animate()
                .translationX(500f)
                .setInterpolator(new BounceInterpolator())
                .setDuration(1500);
    }

    /**
     * 5.使用xml来配置属性动画
     *
     * 属性动画的xml文件不是放在anim文件夹中，而是放在Animator文件夹中。
     *
     *三种类型标签：
     * <animator>               对应的是ValueAnimator
     * <objectAnimator>   对应的是ObjectAnimator
     * <set>                        对应的是AnimatorSet
     *
     * 尽量使用floatType 使用intType不知道为什么没效果。可能是属性值的类型是float就必须配置为floatType
     *
     * @param view
     */
    public void testAnimatorUseXml(View view){
        Animator anim = AnimatorInflater.loadAnimator(this , R.animator.object_ainmator);
        anim.setTarget(ivTest);
        anim.start();
    }


    /**
     * 4.属性动画的回调
     *
     * anim.start(); 要写在addListener后面！！！ 写在前面当然（可能）不会回调onAnimationStart。
     */
    private boolean isCancel = false;
    private ObjectAnimator anim;
    public void testAnimatorListener(View view){
        if (!isCancel){
            anim =  ObjectAnimator.ofFloat(ivTest , "translationX" , 0f,500f);
            anim.setDuration(3000);
            anim.setRepeatCount(100);
            anim.setRepeatMode(ValueAnimator.REVERSE);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Log.e(TAG, "onAnimationStart: ===========start====" );
                    Toast.makeText(ObjectAnimationTestActivity.this, "动画开始", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.e(TAG, "onAnimationEnd: ============end=======" );
                    Toast.makeText(ObjectAnimationTestActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    Log.e(TAG, "onAnimationCancel: ==========cancel======" );
                    Toast.makeText(ObjectAnimationTestActivity.this, "动画Cancel", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    Log.e(TAG, "onAnimationRepeat: ==========repeat====" );
                    Toast.makeText(ObjectAnimationTestActivity.this, "动画重复", Toast.LENGTH_SHORT).show();
                }
            });
            anim.start();
        }else{
            anim.cancel();
        }
        isCancel = !isCancel;

    }

    /**
     * 3.组合动画
     *
     *
     * @param view
     */
    public void testMixAnimator(View view){
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator  anim1 = ObjectAnimator.ofFloat(ivTest , "translationX" , 0f,500f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(ivTest , "scaleX" , 1.0f , 1.5f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(ivTest , "scaleY" , 1.0f , 1.5f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(ivTest , "rotation" , 0 , 360);

        //先执行anim1再同时执行anim2、anim3最后执行anim4
        animatorSet.play(anim2).with(anim3).after(anim1).before(anim4);

        //同时执行anim1 anim2 anim3 anim4
       // animatorSet.playTogether(anim1, anim2 ,anim3 ,anim4);

        // 按顺序执行，每个Animator都是2000ms
       // animatorSet.playSequentially(anim1,anim2 ,anim3 ,anim4 );
        animatorSet.start();
    }

    /**
     * 2.ObjectAnimator
     *
     * 底层的实现机制是基于ValueAnimator来实现的(extends ValueAnimator)
     *
     * 一般使用到的View属性值有：
     * translationX、translationY | X、Y：X轴和Y轴方向的移动（X、表示的是绝对位置）
     * alpha : 透明度(0f~1.0f)
     * scaleX、scaleY：同时缩放X Y 使用 AnimatorSet 不能 "scaleX|scaleY"这样写
     * rotation、rotationX、rotationY：按平面（类似View动画中的旋转）、X轴，y轴
     *
     * 有哪些属性值可以使用：
     *任意的具有set方法的属性值都可以被传入。ObjectAnimator是通过set方法去改变属性的值。
     *
     * @param view
     */
    public void testObjectAnimator(View view) {
        //同样可以接受多个值 0~100 100~400 各1000ms
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivTest, "translationX", 0f, 100f,400f);
        //ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivTest, "translationX", 0f, 100f);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();
      /*
      //不能这样写！！！objectAnimator和ofFloat返回的ObjectAnimator对象不是同一个
      ObjectAnimator objectAnimator = new ObjectAnimator();
      objectAnimator.ofFloat(ivTest, "translationX", 0.0f, 500f)
                .setDuration(1000)
                .start();
        */
    }

    /**
     *1.ValueAnimator的使用
     *
     * @param view
     */
    public void testValueAnimator(View view){
        //0~100 100~200 200~400 400~800 4000ms被分为4份分别按照给定的Interpolator产生值
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100,200,400,800);
        valueAnimator.setDuration(4000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setStartDelay(1000);//延迟执行时间
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator value) {
                Log.e(TAG, "onAnimationUpdate: -=222222=="+value.getAnimatedValue());
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) ivTest.getLayoutParams();
                params.leftMargin = (int) value.getAnimatedValue();
                ivTest.setLayoutParams(params);
                //ivTest.postInvalidate();
            }
        });
    }

}
