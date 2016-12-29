package com.yyyu.androidstydemo.about_wdiget.recyclerview.refresh;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.ListView;

import com.yyyu.androidstydemo.R;
import com.yyyu.androidstydemo.about_wdiget.custom.MaterialProgressDrawable;
import com.yyyu.androidstydemo.about_wdiget.recyclerview.adapter.HeaderAndFooterWrapper;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

/**
 * 功能：SwipeRefreshLayout扩展，使支持加载更多。
 *
 * @author yyyu
 * @date 2016/12/24
 */

public class SwipeRefreshLayoutWrapper extends SwipeRefreshLayout{

    private static final String TAG = "SwipeRefreshLayoutWrapper";

    private Context mContext;
    private RecyclerView mRecyclerView;
    private int touchSlop;
    private float downY;
    private float upY;
    private boolean isLoadingMore = false;

    public SwipeRefreshLayoutWrapper(Context context) {
        this(context , null);
    }

    public SwipeRefreshLayoutWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //---得到ListView或者RecyclerView
        for (int i = 0 ; i< getChildCount() ; i++){
            View cView = getChildAt(i);
            if(cView instanceof RecyclerView){
                mRecyclerView = (RecyclerView) cView;
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case ACTION_DOWN:
                downY = ev.getRawY();
                break;
            case ACTION_UP:
                upY = ev.getRawY();
                break;
            case ACTION_MOVE:
                //---判断是否需要加载更多
                if (isMoveUp() && isBottom(mRecyclerView) && !isLoadingMore){
                    isLoadingMore = true;
                    RecyclerView.Adapter adapter = mRecyclerView.getAdapter();
                    if (adapter instanceof  HeaderAndFooterWrapper){
                        View footer = LayoutInflater.from(mContext).inflate(R.layout.loade_more_footer , mRecyclerView , false);
                        ImageView ivProgress = (ImageView) footer.findViewById(R.id.iv_progress);
                        MaterialProgressDrawable progressDrawable = new MaterialProgressDrawable(mContext , ivProgress);
                        progressDrawable.setBackgroundColor(Color.parseColor("#ffffff"));
                        progressDrawable.setColorSchemeColors(Color.parseColor("#00ff00"),Color.parseColor("#0000ff"));
                        progressDrawable.updateSizes(MaterialProgressDrawable.LARGE);
                        progressDrawable.setAlpha(255);
                        progressDrawable.setStartEndTrim(0f, 0.8f);
                        progressDrawable.setArrowScale(1f); //0~1之间
                        progressDrawable.setProgressRotation(1);
                        progressDrawable.showArrow(true);
                        progressDrawable.start();
                        ivProgress.setImageDrawable(progressDrawable);
                        ((HeaderAndFooterWrapper) adapter).addFooter(footer);
                        adapter.notifyDataSetChanged();
                    }else if(adapter instanceof RecyclerView.Adapter){
                        //TODO
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 设置加载更多完成
     *
     */
    public void setLodeMoreFinished(){
        isLoadingMore = false;
    }

    /**
     * 判断是否为向上滑动
     *
     * @return
     */
    private boolean  isMoveUp(){
        return upY- downY <-touchSlop;
    }

    /**
     * 判断ListView或者是RecyclerView是否滑动到了最后
     *
     * @return
     */
    private boolean isBottom(View view){
        if(view == null){
            return false;
        }
        boolean isBottom = false;
        if(view instanceof  RecyclerView){
            RecyclerView recyclerView = (RecyclerView) view;
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int lastItemPosition = -1;
            if (layoutManager instanceof LinearLayoutManager) {
                lastItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager manager =  (StaggeredGridLayoutManager) layoutManager;
                int [] lastPos = manager.findLastCompletelyVisibleItemPositions(null);
                for (int i : lastPos){
                    lastItemPosition = Math.max(lastItemPosition , i);
                }
                // lastItemPosition = manager.findLastCompletelyVisibleItemPositions(null)[manager.getSpanCount()-1];
            } else if (layoutManager instanceof GridLayoutManager) {
                lastItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else {
                throw new UnsupportedOperationException("目前还不支持该类型LayoutManager");
            }
            isBottom =  (lastItemPosition + 1 == recyclerView.getAdapter().getItemCount() );
        }else  if(view instanceof ListView){
            //---TODO
        }
        return isBottom;
    }


}




