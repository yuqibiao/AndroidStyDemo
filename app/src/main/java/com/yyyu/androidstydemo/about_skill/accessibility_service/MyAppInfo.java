package com.yyyu.androidstydemo.about_skill.accessibility_service;

import android.graphics.drawable.Drawable;

public class MyAppInfo {
    private Drawable image;
    private String pkgName;
    private String appName;  
  
    public MyAppInfo(Drawable image, String appName) {  
        this.image = image;  
        this.appName = appName;  
    }  
    public MyAppInfo() {  
  
    }  
  
    public Drawable getImage() {  
        return image;  
    }  
  
    public void setImage(Drawable image) {  
        this.image = image;  
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getAppName() {
        return appName;  
    }  
  
    public void setAppName(String appName) {  
        this.appName = appName;  
    }  
}