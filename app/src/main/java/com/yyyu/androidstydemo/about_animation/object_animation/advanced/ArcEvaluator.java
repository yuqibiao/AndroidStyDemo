package com.yyyu.androidstydemo.about_animation.object_animation.advanced;

import android.animation.TypeEvaluator;

/**
 * 功能：自定义估值器
 *
 *
 *
 * Created by yyyu on 2017/1/3.
 */

public class ArcEvaluator implements TypeEvaluator<ArcBean> {

    /**
     * @param fraction          这个值和Interpolator中的inputValue对应
     * @param startValue      开始值
     * @param endValue       结束值
     * @return
     */
    @Override
    public ArcBean evaluate(float fraction, ArcBean startValue, ArcBean endValue) {
        float startAngle = startValue.getAngle();
        float startOffset = startValue.getOffset();
        float endAngle = endValue.getAngle();
        float endOffset = endValue.getOffset();
        ArcBean currentArc = new ArcBean();
        currentArc.setAngle(startAngle +(endAngle - startAngle)*fraction);
        currentArc.setOffset(startOffset + (endOffset - startOffset)*fraction);
        return currentArc;
    }
}
