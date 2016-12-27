package com.yyyu.androidstydemo.about_skill.debug_test;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yyyu.androidstydemo.R;
import com.yyyu.androidstydemo.common.activity.BaseActivity;

/**
 * 功能：Android studio debug 测试
 *
 *
 * 条件断点：
 * 查看某一特定循环下的运行情况，在断点处右键添加condition。使得一个循环在特定的条件下停止。
 *
 * 日志断点：
 *  取消勾选suspend，选中Log evaluated expression，并书写Log语句。
 * 比直接在代码里加入Log代码的侵入性要低，而且不需要再重新部署一遍程序。
 *
 * 方法断点：
 * 在方法的申明处打l断点，适用于只关心方法的参数和返回值的情况。
 *
 * 变量断点：
 * 在变量(一般指成员变量)的前面打断点，适用于只关心某一变量值的变化的情况。
 *
 * 异常断点
 *  添加后当出现指定异常的时候就 suspend。
 *  添加方式：在View BreakPoint里面添加关心的Exception即可。里面还可以添加一些其他类型的断点，
 *  比如每当打印Log的时候就断点。
 *
 *
 * 变量赋值：
 * 按f2或者选中Variables中的变量右键set Value 给变量设置值，记住输入值后按回车。
 * 这样就可以不修改代码去改变程序中变量的值了。
 *
 *变量观察：
 * 选中Variables中的变量右键add to Watches，适用于变量很多时对个别变量的特殊观察。
 *
 * 对象求值：
 *  选中Variables区域中的任意变量-->Evaluate Expression-->在输入框里面可以计算任意代码里出现的变量相互运算
 *  ，方法调用后的返回结果。（如可以在输入框中调用getSum()方法）这功能不能再棒！！！
 *
 * step over：单步执行（f8），不会跳进方法里面。
 * step into :  进入方法（f7）
 * step out：跳出方法（shift+f8）
 *
 * 断点浏览（View BreakPoints）：
 * 查看和增删所打的断点，图标在Debugger栏中，两个红点样式。
 *
 * Attach Debug：
 * 调试已经运行的APP，不用每次都去运行一次程序，图标是带bug的手机（运行图标右边第三个）。
 *
 *
 * 参考：https://gold.xitu.io/post/585885ed8d6d810065bf87a8
 *
 * Created by yyyu on 2016/12/26.
 */

public class DebugTestActivity extends BaseActivity{

    private static final String TAG = "DebugTestActivity";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_debug_test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    /**
     * 1.条件断点：
     * 查看某一特定循环下的运行情况，在断点处右键添加condition
     *
     * @param view
     */
    public void testConditional(View view){
        Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();
        int temp = -1;
        for (int i = 0; i < 50; i++) {
            Log.e(TAG, "testConditional: i= "+i );
            temp = i*2;
        }
    }

    /**
     * 2.日志断点：
     *
     * 取消勾选suspend，选中Log evaluated expression，并书写Log。
     * 比直接在代码里加入Log代码的侵入性要低，而且不需要再重新部署一遍程序。
     *
     * @param view
     */
    public void testLog(View view){

        for (int i = 0; i < 10; i++) {
            int temp = i+2;
        }

        int result = 0;
        int num1 = 10;
        int num2 = 20;
        result = getSum(num1,num2);

        Log.e(TAG, "testLog: 10+20="+result);

    }

    /**
     * 3.异常断点
     *  添加后当出现指定异常的时候就 suspend。
     * @param view
     */
    public void testException(View view){
        int num1 = 10;
        int num2 = 0;
        int result = num1/num2;
        Log.e(TAG, "testException: result="+result );
    }

    /**
     * 3.方法断点
     *
     * @return
     */
    private int getSum(int num1 , int num2){
        return num1+num2;
    }

    public static void startAction(Activity act){
        act.startActivity(new Intent(act , DebugTestActivity.class));
    }

}
