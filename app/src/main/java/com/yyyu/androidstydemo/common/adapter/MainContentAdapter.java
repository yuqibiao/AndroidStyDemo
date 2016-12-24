package com.yyyu.androidstydemo.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yyyu.androidstydemo.R;

import java.util.Random;

/**
 * 功能：主页内容的Adapter
 *
 *
 * Created by yyyu on 2016/12/23.
 */

public class MainContentAdapter extends RecyclerView.Adapter<MainContentAdapter.ContentViewHolder>{

    private Context mContext;

    public MainContentAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_main_content , parent , false);
        ContentViewHolder holder = new ContentViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position) {
        /*ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = new Random().nextInt(400)+150;*/
        holder.tvMainContent.setText("普通方式添加Adapter");
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class ContentViewHolder extends RecyclerView.ViewHolder{

        public TextView tvMainContent;

        public ContentViewHolder(View itemView) {
            super(itemView);
            tvMainContent = (TextView) itemView.findViewById(R.id.tv_main_content);
        }
    }

}
