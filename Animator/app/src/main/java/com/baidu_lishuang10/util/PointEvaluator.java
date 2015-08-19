package com.baidu_lishuang10.util;

import android.animation.TypeEvaluator;

import com.baidu_lishuang10.bean.Point;

/**
 * Created by baidu_lishuang10 on 15/7/17.
 */
public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        return new Point(x, y);
    }
}
