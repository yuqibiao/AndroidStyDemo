package com.yyyu.androidstydemo.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 功能：Activity的基类
 *
 * 使用模版方法的设计模式
 *
 * Created by yyyu on 2016/12/22.
 */

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        init();
    }

    /**
     * 初始化方法
     *
     */
    public void init(){
        beforeInit();
        initView();
        initListener();
        initData();
    }

    /**
     *
     * 钩子函数
     * 设置Activity的布局文件
     *
     * @return
     */
    protected abstract int setLayoutId();

    protected  void beforeInit(){

    }

    /**
     * 初始化布局文件
     *
     */
    protected abstract  void initView();


    /**
     * 初始化监听
     */
    protected  abstract void initListener();

    /**
     * 初始化数据 ， 空实现，不强制要求子类重写。
     */
    protected  void initData(){

    }

    /**
     *
     * findViewById
     *
     * @param id
     * @param <T>
     * @return
     */
    protected  <T extends View>T  getView(int id){
        return (T) findViewById(id);
    }


}
