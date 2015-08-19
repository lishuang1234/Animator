package com.baidu_lishuang10.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

import com.baidu_lishuang10.bean.Point;
import com.baidu_lishuang10.util.ColorEvaluator;
import com.baidu_lishuang10.util.DecelerateAccelerateInterpolator;
import com.baidu_lishuang10.util.PointEvaluator;

/**
 * Created by baidu_lishuang10 on 15/7/17.
 */
public class MyAnimView extends View {
    private Point mCurrentPoint;
    private Paint mPaint;
    private static final float RADIUS = 50f;

    private String color;
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }




    public MyAnimView(Context context) {
        this(context, null);

    }

    public MyAnimView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurrentPoint == null) {//第一次启动动画
            mCurrentPoint = new Point(RADIUS, RADIUS);
            startColorAnim();
        }
        drawCircle(canvas);

    }

    private void drawCircle(Canvas canvas) {

        canvas.drawCircle(mCurrentPoint.getX(), mCurrentPoint.getY(), RADIUS, mPaint);
    }

    private void startAnim() {
        Point startPoint = new Point(RADIUS, RADIUS);
        Point endPoint = new Point(getMeasuredWidth() - RADIUS, getMeasuredHeight() - RADIUS);

        ValueAnimator viewAnimator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        viewAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentPoint = (Point) animation.getAnimatedValue();
                invalidate();//更新时，获取对象重绘
            }
        });
        viewAnimator.setDuration(3000);
        viewAnimator.start();

    }

    private void startColorAnim() {
//
//        Point startPoint = new Point(RADIUS, RADIUS);
//        Point endPoint = new Point(getMeasuredWidth() - RADIUS, getMeasuredHeight() - RADIUS);
        Point startPoint = new Point(getWidth()/2, RADIUS);
        Point endPoint = new Point(getWidth()/2,getHeight()-RADIUS);
        ValueAnimator viewAnimator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        viewAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentPoint = (Point) animation.getAnimatedValue();
                invalidate();//更新时，获取对象重绘
            }
        });
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(), "#0000FF", "#FF0000");
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(viewAnimator).with(objectAnimator);
        animatorSet.setDuration(5000);
        viewAnimator.setInterpolator(new DecelerateAccelerateInterpolator());
        viewAnimator.setRepeatCount(5);
        animatorSet.start();
    }
}
