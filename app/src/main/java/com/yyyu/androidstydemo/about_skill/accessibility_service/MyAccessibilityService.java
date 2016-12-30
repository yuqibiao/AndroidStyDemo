package com.yyyu.androidstydemo.about_skill.accessibility_service;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import com.yyyu.androidstydemo.about_skill.debug_test.DebugTestActivity;
import com.yyyu.androidstydemo.common.utils.MySPUtils;

/**
 * 功能：辅助服务的使用
 *
 * 使用步骤：
 * 1.在res/xml目录下新建一个accessibility_service_config.xml文件(文件名称不是固定的)。
 * 在里面可以通过packageName来设置监听哪些应用。
 * 2.在string.xml文件中写一下description中指定的内容。
 * 3.在清单文件中添加相应的service，其中label是在设置界面显示的提示。
 *
 * 参考：
 * https://mp.weixin.qq.com/s/7L2ysyTlFR1Xz4tk73dxuA（总的概括）
 * http://blog.csdn.net/guolin_blog/article/details/47803149（实现静默安装）
 * http://blog.csdn.net/dd864140130/article/details/51794318(深入理解)
 *
 * Created by yyyu on 2016/12/26.
 */

public class MyAccessibilityService extends AccessibilityService{

    private static final String TAG = "MyAccessibilityService";

    public MyAccessibilityService(){
        Log.e(TAG, "MyAccessibilityService: ==================" );
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        String pkgName = accessibilityEvent.getPackageName().toString();
        if(isLocked(pkgName)){
            Log.e(TAG, "onAccessibilityEvent: ===被监听的应用====");
            Intent intent = new Intent(this, DebugTestActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Toast.makeText(this, "j被监听的应用====", Toast.LENGTH_SHORT).show();
        }
        Log.e(TAG, "onAccessibilityEvent: ===监听到了=2="+pkgName);
    }

    @Override
    public void onInterrupt() {
        Log.e(TAG, "onInterrupt:========" );
    }

    private boolean isLocked(String pkgName){
        return (boolean) MySPUtils.get(this , pkgName  , false);
    }

}
