package com.yyyu.androidstydemo.common.activity;


import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.yyyu.androidstydemo.R;
import com.yyyu.androidstydemo.common.adapter.DrawerItemAdapter;
import com.yyyu.androidstydemo.common.adapter.MainContentAdapter;
import com.yyyu.androidstydemo.common.wdiget.LinearAdapterLayout;
import com.yyyu.androidstydemo.about_widget.recyclerview.adapter.HeaderAndFooterWrapper;
import com.yyyu.androidstydemo.about_widget.recyclerview.click.OnRvItemClickListener;

public class MainActivity extends BaseActivity {

    private LinearAdapterLayout lalDrawer;
    private DrawerLayout dlMain;
    private RecyclerView rvMainContent;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        Toolbar tbMain = getView(R.id.tb_main);
        lalDrawer = getView(R.id.lal_drawer);
        dlMain = getView(R.id.dl_main);
        rvMainContent = getView(R.id.rv_main_content);

        //----set
        setSupportActionBar(tbMain);
        rvMainContent.setLayoutManager(new LinearLayoutManager(this));
        //rvMainContent.setLayoutManager(new GridLayoutManager(this , 3));
        /*StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3 , StaggeredGridLayoutManager.VERTICAL);
        rvMainContent.setLayoutManager(layoutManager);*/
    }

    @Override
    protected void initListener() {

        lalDrawer.setOnLinearItemClick(new LinearAdapterLayout.OnLinearItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this , "position："+position , Toast.LENGTH_LONG).show();
            }
        });

        rvMainContent.addOnItemTouchListener(new OnRvItemClickListener(rvMainContent) {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "点击=="+position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(MainActivity.this, "长按=="+position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void initData() {
        lalDrawer.setLinearAdapter(new DrawerItemAdapter(this));
        /*rvMainContent.setAdapter(new MainContentAdapter(this));*/
        HeaderAndFooterWrapper wrapperAdapter = new HeaderAndFooterWrapper(new MainContentAdapter(this));
        wrapperAdapter.addHeader(LayoutInflater.from(this).inflate(R.layout.item_header , rvMainContent , false));
        wrapperAdapter.addHeader(LayoutInflater.from(this).inflate(R.layout.item_header , rvMainContent , false));
        wrapperAdapter.addFooter(LayoutInflater.from(this).inflate(R.layout.item_footer , rvMainContent , false));
        wrapperAdapter.addFooter(LayoutInflater.from(this).inflate(R.layout.item_footer , rvMainContent , false));
        wrapperAdapter.addFooter(LayoutInflater.from(this).inflate(R.layout.item_footer , rvMainContent , false));
        rvMainContent.setAdapter(wrapperAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_drawer_open:{
                if(dlMain.isDrawerOpen(Gravity.RIGHT)){
                    dlMain.closeDrawer(Gravity.RIGHT);
                }else{
                    dlMain.openDrawer(Gravity.RIGHT);
                }
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(dlMain.isDrawerOpen(Gravity.RIGHT)){
            dlMain.closeDrawer(Gravity.RIGHT);
        }else{
            super.onBackPressed();
        }
    }
}
