package com.yyyu.androidstydemo.about_skill.accessibility_service;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityManager;

import java.util.List;

/**
 * 功能：
 *
 * Created by yyyu on 2016/12/29.
 */

public class LogicUtils {

    private static final String TAG = "LogicUtils";

    /**
     * 判断AccessibilityService是否被打开
     *
     * @param serviceName
     */
    public static boolean isAccessibilityEnable(Context context , String serviceName){
        AccessibilityManager am = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        List<AccessibilityServiceInfo> enableServiceList = am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);
        //List<AccessibilityServiceInfo> installedAccessibilityServiceList = am.getInstalledAccessibilityServiceList();
        am.isEnabled();
        for (AccessibilityServiceInfo info : enableServiceList) {
            if (serviceName.equals(info.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断某个APP是否正在前台运行
     *
     * 5.0后
     *
     * @param context
     * @return
     */
    public static boolean isAppOnForeground(Context context , String pkgName) {

        boolean isForeGround = false;
        ActivityManager am = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH){
            List<ActivityManager.RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
            if (appProcesses == null){
                isForeGround = false;
            }
            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                if (appProcess.processName.equals(pkgName)
                        && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.e(TAG, "isAppOnForeground:===pkgName："+appProcess.processName );
                    isForeGround = true;
                }
            }
        }else{//---5.0以下，记得要添加 android.permission.GET_TASKS 权限
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(pkgName)) {
                isForeGround = true;
            }
        }

        return isForeGround;
    }

}
