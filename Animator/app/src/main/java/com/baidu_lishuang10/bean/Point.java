package com.baidu_lishuang10.bean;

/**
 * Created by baidu_lishuang10 on 15/7/16.
 */
public class Point {
    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    private float x;
    private float y;
}
