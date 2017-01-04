package com.yyyu.androidstydemo.about_animation.object_animation.advanced;

import android.animation.TypeEvaluator;

/**
 * 功能：自定义Evaluator实现颜色的渐变
 *
 * 思路：
 * 将#fffff、#fff 、#ffffffff 按RGB分隔并转换成十进制，进行插值变换
 * 最后再将RGB拼接成#ffffffff返回。
 *
 * Created by yyyu on 2017/1/4.
 */

public class ColorEvaluator implements TypeEvaluator {


    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        StringBuilder resultColor = new StringBuilder();
        String startColor = complementColorStr((String) startValue);
        String endColor = complementColorStr((String) endValue);

        int startRed =Integer.parseInt( startColor.substring(3,5) , 16);
        int startGreen =Integer.parseInt( startColor.substring(5,7) , 16);
        int startBlue =Integer.parseInt( startColor.substring(7,9) , 16);
        int endRed = Integer.parseInt(endColor.substring(3,5),16);
        int endGreen = Integer.parseInt(endColor.substring(5,7),16);
        int endBlue = Integer.parseInt(endColor.substring(7,9),16);

        int redDiff = endRed - startRed;
        int greenDiff = endGreen - startGreen;
        int blueDiff = endBlue - startBlue;

        resultColor.append("#FF");
        resultColor.append( getHexString(Math.round(startRed+fraction*redDiff)) );
        resultColor.append( getHexString(Math.round(startGreen+fraction*greenDiff)) );
        resultColor.append( getHexString(Math.round(startBlue+fraction*blueDiff)) );

        return resultColor.toString();
    }

    /**
     * 将10进制颜色值转换成16进制。
     */
    private String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }

    /**
     * 补全格式
     *#fff、#fffff 都补全成 #ffffffff
     */
    private static  String complementColorStr(String colorStr){
        StringBuilder sb  = new StringBuilder();
        if(colorStr.startsWith("#")){
            if(colorStr.length() == 4){
                sb.append("#FF");
                colorStr = colorStr.substring(1); //去掉#
                for (int i = 0 ; i<colorStr.length() ; i++){
                    sb.append(colorStr.charAt(i));
                    sb.append(colorStr.charAt(i));
                }
            }else if(colorStr.length() == 7){
                sb.append("#FF");
                sb.append(colorStr.substring(1));
            }else if(colorStr.length() == 9){
                sb.append(colorStr);
            }else{
                throw new UnsupportedOperationException("传入的颜色字符串格式不正确！");
            }
        }else{
            throw new UnsupportedOperationException("传入的颜色字符串格式不正确，请以以#开头");
        }
        return sb.toString();
    }


}
