package com.example.bluetoothjava;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class ViewCustomObject extends View implements View.OnTouchListener {

    Paint outerCircleColor;
    Paint innerCircleColor;
    private int outerCircleRadius;
    private int innerCircleRadius ;
    private int innerCirclePositionX;
    private int innerCirclePositionY;
    private int outerCirclePositionX;
    private int outerCirclePositionY;

    protected GestureDetector gesture;


    public int getOuterCircleRadiusRadius() {
        return outerCircleRadius;
    }

    public void setOuterCircleRadius(int radius) {
        this.outerCircleRadius = radius;
        invalidate();
        requestLayout();
    }

    public int getInnerCircleRadius() {
        return innerCircleRadius;
    }

    public void setInnerCircleRadius(int innerCircleRadius) {
        this.innerCircleRadius = innerCircleRadius;
        invalidate();
        requestLayout();
    }



    public int getInnerCirclePositionX() {
        return innerCirclePositionX;
    }

    public void setInnerCirclePositionX(int innerCirclePositionX) {
        this.innerCirclePositionX = innerCirclePositionX;
        invalidate();
        requestLayout();
    }



    public int getInnerCirclePositionY() {
        return innerCirclePositionY;
    }

    public void setInnerCirclePositionY(int innerCirclePositionY) {
        int upperBoundary = 600-innerCircleRadius;
        int downBoundary = innerCircleRadius;
        if(innerCirclePositionY>=upperBoundary){
            this.innerCirclePositionY =upperBoundary;
        }
        else this.innerCirclePositionY = Math.max(innerCirclePositionY, downBoundary);
        invalidate();
        requestLayout();
    }



    public int getOuterCirclePositionX() {
        return outerCirclePositionX;
    }

    public void setOuterCirclePositionX(int outerCirclePositionX) {
        this.outerCirclePositionX = outerCirclePositionX;
        invalidate();
        requestLayout();
    }



    public int getOuterCirclePositionY() {
        return outerCirclePositionY;
    }

    public void setOuterCirclePositionY(int outerCirclePositionY) {
        this.outerCirclePositionY = outerCirclePositionY;
        invalidate();
        requestLayout();
    }


    public ViewCustomObject(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);



        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,R.styleable.ViewCustomObject,0,0);
        try{
            outerCircleRadius= array.getInteger(R.styleable.ViewCustomObject_outerCircleRadius,200);
            innerCircleRadius= array.getInteger(R.styleable.ViewCustomObject_innerCircleRadius,100);
            innerCirclePositionX = array.getInteger(R.styleable.ViewCustomObject_innerCirclePositionX, 200);
            innerCirclePositionY = array.getInteger(R.styleable.ViewCustomObject_innerCirclePositionY, 200);
            outerCirclePositionX = array.getInteger(R.styleable.ViewCustomObject_outerCirclePositionX, 200);
            outerCirclePositionY = array.getInteger(R.styleable.ViewCustomObject_outerCirclePositionY, 200);
        }finally {
            array.recycle();
        }

        gesture = new GestureDetector(context, new JoystickGestureDetectorListener(this));
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }*/


    private void init(){
        outerCircleColor = new Paint();
        outerCircleColor.setColor(Color.BLUE);
        innerCircleColor = new Paint();
        innerCircleColor.setColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        //outerCircle
        canvas.drawCircle(outerCirclePositionX,outerCirclePositionY,outerCircleRadius, outerCircleColor);
        //innerCircle
        canvas.drawCircle(innerCirclePositionX,innerCirclePositionY,innerCircleRadius,innerCircleColor);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gesture.onTouchEvent(event);
    }
}

