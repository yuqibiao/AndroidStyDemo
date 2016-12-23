package com.yyyu.androidstydemo.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yyyu.androidstydemo.R;
import com.yyyu.androidstydemo.common.wdiget.LinearAdapterLayout;

/**
 * 功能：
 * Created by yyyu on 2016/12/22.
 */

public class DrawerItemAdapter extends LinearAdapterLayout.LinearAdapter{

    private Context mContext;

    public DrawerItemAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public View getView(ViewGroup parent , int position) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_drawer , parent , false);
        return contentView;
    }
}
