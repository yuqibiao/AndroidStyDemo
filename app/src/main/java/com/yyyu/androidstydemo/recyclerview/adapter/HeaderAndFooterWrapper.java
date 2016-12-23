package com.yyyu.androidstydemo.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * 功能：扩展Adapter 使得能添加Header和Footer
 *
 *思路：
 *
 *
 * key：
 * 使用设计模式中的装饰者模式
 *
 *
 * 参考：http://blog.csdn.net/lmj623565791/article/details/51854533
 *
 * Created by yyyu on 2016/12/23.
 */

public class HeaderAndFooterWrapper extends RecyclerView.Adapter{

    private static final String TAG = "HeaderAndFooterWrapper";

    private final static int BASE_HEADER_NUM = 200000;
    private final static int BASE_FOOTER_NUM  = BASE_HEADER_NUM+100000;
    private RecyclerView.Adapter innerAdapter;
    private SparseArray<View> headerList;
    private SparseArray<View> footerList;

    public HeaderAndFooterWrapper(RecyclerView.Adapter adapter){
        this.innerAdapter = adapter;
        headerList = new SparseArray<>();
        footerList = new SparseArray<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(headerList.get(viewType) !=null){
            return new TempViewHolder(headerList.get(viewType));
        }else if(footerList.get(viewType) != null){
            return new TempViewHolder(footerList.get(viewType));
        }else{
            return innerAdapter.onCreateViewHolder(parent , viewType);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(isHeaderPosition(position) || isFooterPosition(position)){
            return;
        }else{
            innerAdapter.onBindViewHolder(holder , position);
        }
    }

    @Override
    public int getItemCount() {
        return headerList.size()+getRealItemCount()+footerList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(isHeaderPosition(position)){
            return headerList.keyAt(position);
        }else if (isFooterPosition(position)){
            return footerList.keyAt(position-headerList.size()-getRealItemCount());
        }
        return position;
    }

    public boolean isFooterPosition(int position){
        return position>=headerList.size()+getRealItemCount();
    }

    public boolean isHeaderPosition(int position){
        return position<headerList.size();
    }

    private class TempViewHolder extends RecyclerView.ViewHolder{
        public TempViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 添加Footer
     *
     * @param footerView
     */
    public void addFooter(View footerView){
        footerList.put(BASE_FOOTER_NUM+footerList.size(), footerView);
    }

    /**
     * 添加Header
     *
     * @param headerView
     */
    public void addHeader(View headerView){
        headerList.put(BASE_HEADER_NUM+headerList.size() ,headerView );
    }

    /**
     * 得到内容item的格式
     * @return
     */
    public int getRealItemCount(){
        return innerAdapter.getItemCount();
    }


}
