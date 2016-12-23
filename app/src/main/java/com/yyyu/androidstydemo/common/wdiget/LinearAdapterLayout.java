package com.yyyu.androidstydemo.common.wdiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 功能：动态添加Item的LinearLayout
 * <p>
 * Build模式的变形，封装传参。
 * <p>
 * Created by yyyu on 2016/12/22.
 */

public class LinearAdapterLayout extends LinearLayout {

    private OnLinearItemClickListener mOnLinearItemClickListener;

    public LinearAdapterLayout(Context context) {
        this(context, null);
    }

    public LinearAdapterLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearAdapterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void setLinearAdapter(LinearAdapter adapter) {
        removeAllViews();
        if(adapter.getOrientation() == 0){
            setOrientation(HORIZONTAL);
        }else if(adapter.getOrientation() == 1){
            setOrientation(VERTICAL);
        }else{
            throw  new  UnsupportedOperationException("传入的Orientation为0或1");
        }
        for (int i = 0; i < adapter.getCount(); i++) {
            final int position = i;
            View cView = adapter.getView(this , position);
            addView(cView);
            if (mOnLinearItemClickListener != null) {
                cView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnLinearItemClickListener.onClick(position);
                    }
                });
            }
        }
    }

    public static abstract class LinearAdapter {

        public abstract int getCount();

        public abstract View getView(ViewGroup parent , int position);

        /**
         * 0：HORIZONTAL
         * 1：VERTICAL
         * @return
         */
        public int getOrientation() {
            return VERTICAL;
        }

    }

    public void setOnLinearItemClick(OnLinearItemClickListener onLinearItemClickListener) {
        this.mOnLinearItemClickListener = onLinearItemClickListener;
    }

    public interface OnLinearItemClickListener {
        void onClick(int position);
    }


}
