package com.baidu_lishuang10.util;

import android.animation.TimeInterpolator;

/**
 * Created by baidu_lishuang10 on 15/7/30.
 */
public class DecelerateAccelerateInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        float result;
        if (input <= 0.5)
            result = (float) (Math.sin(input * Math.PI) / 2);// 0-0.5
        else
            result = 1 - (float) (Math.sin(input * Math.PI) / 2);
        return result;
    }
}
