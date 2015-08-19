package com.baidu_lishuang10.util;

import android.animation.TypeEvaluator;

/**
 * Created by baidu_lishuang10 on 15/7/17.
 */
public class ColorEvaluator implements TypeEvaluator {
    private int mCurrentRed = -1;
    private int mCurrentGreen = -1;
    private int mCurrentBlue = -1;


    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        String startColor = (String) startValue;
        String endColor = (String) endValue;

        int startRed = Integer.parseInt(startColor.substring(1, 3), 16);
        int startGreen = Integer.parseInt(startColor.substring(3, 5), 16);
        int startBlue = Integer.parseInt(startColor.substring(5, 7), 16);

        int endRed = Integer.parseInt(endColor.substring(1, 3), 16);
        int endGreen = Integer.parseInt(endColor.substring(3, 5), 16);
        int endBlue = Integer.parseInt(endColor.substring(5, 7), 16);
        //设置初始化的颜色值
        if (mCurrentRed == -1)
            mCurrentRed = startRed;
        if (mCurrentBlue == -1)
            mCurrentBlue = startBlue;
        if (mCurrentGreen == -1)
            mCurrentGreen = startGreen;
        //计算颜色差值
        int redDiff = Math.abs(startRed - endRed);
        int blueDiff = Math.abs(startBlue - endBlue);
        int greenDiff = Math.abs(startGreen - endGreen);

        int colorDiff = redDiff + blueDiff + greenDiff;
        if (mCurrentRed != endRed)
            mCurrentRed = getCurrentColor(startRed, endRed, colorDiff, 0, fraction);
        if (mCurrentGreen != endGreen)
            mCurrentGreen = getCurrentColor(startGreen, endGreen, colorDiff, redDiff, fraction);
        if (mCurrentBlue != endBlue)
            mCurrentBlue = getCurrentColor(startBlue, endBlue, colorDiff, redDiff + greenDiff, fraction);

        String currentColor = "#" + getHexString(mCurrentRed) + getHexString(mCurrentGreen) + getHexString(mCurrentBlue);
        return currentColor;
    }

    //计算当前颜色值
    private int getCurrentColor(int startColor, int endColor, int colorDiff, int offset, float fraction) {
        int currentColor;
        if (startColor > endColor) {
            currentColor = (int) (startColor - (colorDiff * fraction - offset));
            if (currentColor < endColor)
                currentColor = endColor;
        } else {
            currentColor = (int) (startColor + colorDiff * fraction - offset);
            if (currentColor > endColor)
                currentColor = endColor;
        }
        return currentColor;
    }

    //将10进制颜色值转换为16进制
    private String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1)
            hexString += "0";
        return hexString;
    }
}
