package com.yyyu.androidstydemo.about_skill.accessibility_service;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yyyu.androidstydemo.R;
import com.yyyu.androidstydemo.common.activity.BaseActivity;
import com.yyyu.androidstydemo.common.utils.MySPUtils;

import java.util.List;

/**
 * 功能：添加应用锁的Activity
 * <p>
 * Created by yyyu on 2016/12/27.
 */

public class AddLockActivity extends BaseActivity {

    private static final String TAG = "AddLockActivity";

    private List<MyAppInfo> apkInfoList;
    private ListView lvAddLock;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_lock;
    }

    @Override
    protected void initView() {
        lvAddLock = getView(R.id.lv_add_lock);
    }

    @Override
    protected void initListener() {
        Log.e(TAG, "initListener: =================" );
    }

    @Override
    protected void initData() {
        super.initData();
        apkInfoList = ApkTool.scanLocalInstallAppList(getPackageManager());
        lvAddLock.setAdapter(new ApkInfoAdapter());
    }

    private class ApkInfoAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return apkInfoList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 1;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View itemView = LayoutInflater.from(AddLockActivity.this).inflate(R.layout.item_apkinfo , viewGroup , false);
            ImageView ivApkIcon = (ImageView) itemView.findViewById(R.id.iv_apk_icon);
            TextView tvAppName = (TextView) itemView.findViewById(R.id.tv_app_name);
            final ImageButton ibLock = (ImageButton) itemView.findViewById(R.id.ib_lock);
            final MyAppInfo appInfo = apkInfoList.get(i);
            ivApkIcon.setImageDrawable(appInfo.getImage());
            tvAppName.setText(appInfo.getAppName());
            if(isLocked(appInfo.getPkgName())){
                ibLock.setImageResource(R.mipmap.icon_locked);
            }else{
                ibLock.setImageResource(R.mipmap.icon_unlocked);
            }
            ibLock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isLocked(appInfo.getPkgName())){
                        MySPUtils.put(AddLockActivity.this , appInfo.getPkgName(),false);
                        ibLock.setImageResource(R.mipmap.icon_unlocked);
                    }else{
                        MySPUtils.put(AddLockActivity.this , appInfo.getPkgName(),true);
                        ibLock.setImageResource(R.mipmap.icon_locked);
                    }
                }
            });
            return itemView;
        }
    }

    private boolean isLocked(String pkgName){
        return (boolean) MySPUtils.get(this , pkgName  , false);
    }


}
