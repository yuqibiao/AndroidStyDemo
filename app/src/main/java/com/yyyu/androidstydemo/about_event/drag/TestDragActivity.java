package com.yyyu.androidstydemo.about_event.drag;

import android.content.ClipData;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;

import com.yyyu.androidstydemo.R;
import com.yyyu.androidstydemo.common.activity.BaseActivity;

/**
 * 功能：View的拖拽事件
 *
 * 参考：
 * http://gavinliu.cn/2014/03/25/Android---让控件变得可拖拽/
 * Created by yyyu on 2017/1/4.
 */

public class TestDragActivity extends BaseActivity{

    private static final String TAG = "TestDragActivity";

    private ImageView ivDrag;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_test_drag;
    }

    @Override
    protected void initView() {
        ivDrag = getView(R.id.iv_drag);
    }

    @Override
    protected void initListener() {

        ivDrag.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(ivDrag);
                ClipData data = ClipData.newPlainText("dot", "Dot : " + v.toString());
                ivDrag.startDrag(data , shadowBuilder , ivDrag , 0);
                return true;
            }
        });

        //---设置拖拽监听
        ivDrag.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final int action = event.getAction();
                switch (action) {
                    // 开始拖拽
                    case DragEvent.ACTION_DRAG_STARTED:
                        ivDrag.setVisibility(View.INVISIBLE);
                        Log.e(TAG, "image1 ACTION_DRAG_STARTED");
                        break;
                    // 结束拖拽
                    case DragEvent.ACTION_DRAG_ENDED:
                        ivDrag.setVisibility(View.VISIBLE);
                        Log.e(TAG, "image1 ACTION_DRAG_ENDED");
                        break;
                    // 拖拽进某个控件后，退出
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.e(TAG, "image1 ACTION_DRAG_EXITED");
                        break;
                    // 拖拽进某个控件后，保持
                    case DragEvent.ACTION_DRAG_LOCATION:
                        Log.e(TAG, "image1 ACTION_DRAG_LOCATION");
                        break;
                    // 推拽进入某个控件
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.e(TAG, "image1 ACTION_DRAG_ENTERED");
                        break;
                    // 推拽进入某个控件，后在该控件内，释放。即把推拽控件放入另一个控件
                    case DragEvent.ACTION_DROP:
                        Log.e(TAG, "image1 ACTION_DROP");
                        break;
                }
                return true;
            }
        });

    }


}
