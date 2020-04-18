package com.example.bluetoothjava;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.textclassifier.TextClassifierEvent;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;





public class ViewCustomObject extends View implements View.OnTouchListener {

    Paint outerCircleColor;
    Paint innerCircleColor;
    private int outerCircleRadius;
    private int innerCircleRadius ;
    private int innerCirclePositionX;
    private int innerCirclePositionY;
    private int outerCirclePositionX;
    private int outerCirclePositionY;
    private final int innerCirclePositionXReset;
    private final int innerCirclePositionYReset;
    private final int outerCirclePositionXReset;
    private final int outerCirclePositionYReset;
    Context myContext;

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
        this.innerCirclePositionY = innerCirclePositionY;
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

        myContext = context;

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,R.styleable.ViewCustomObject,0,0);
        try{
            outerCircleRadius= array.getInteger(R.styleable.ViewCustomObject_outerCircleRadius,200);
            innerCircleRadius= array.getInteger(R.styleable.ViewCustomObject_innerCircleRadius,100);
            innerCirclePositionXReset = innerCirclePositionX = array.getInteger(R.styleable.ViewCustomObject_innerCirclePositionX, 200);
            innerCirclePositionYReset = innerCirclePositionY = array.getInteger(R.styleable.ViewCustomObject_innerCirclePositionY, 200);
            outerCirclePositionXReset = outerCirclePositionX = array.getInteger(R.styleable.ViewCustomObject_outerCirclePositionX, 200);
            outerCirclePositionYReset =outerCirclePositionY = array.getInteger(R.styleable.ViewCustomObject_outerCirclePositionY, 200);
        }finally {
            array.recycle();
        }

        JoystickGestureDetectorListener joystickGestureDetectorListener = new JoystickGestureDetectorListener(this);
        joystickGestureDetectorListener.addListener((CoordinateChangedListener) context);
        gesture = new GestureDetector(context, joystickGestureDetectorListener);

    }


    private void init(){
        outerCircleColor = new Paint();
        outerCircleColor.setColor(Color.BLUE);
        innerCircleColor = new Paint();
        innerCircleColor.setColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("ActivitySize", "w: "+ Integer.toString(DensityUtil.dip2px(myContext,w)) + " h: " + Integer.toString(h));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
         //outerCircle
        canvas.drawCircle(outerCirclePositionX,outerCirclePositionY ,outerCircleRadius, outerCircleColor);
        //innerCircle
        canvas.drawCircle(innerCirclePositionX,innerCirclePositionY,innerCircleRadius,innerCircleColor);


    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("Events",Integer.toString(event.getAction()));
        if(event.getAction() == MotionEvent.ACTION_UP){
            resetCustomViewObject();
            Log.i("Events", "ACTION_UP");
            invalidate();
            requestLayout();
            return false;
        }
        else {
            return gesture.onTouchEvent(event);
        }
    }

    public int getInnerCirclePositionXReset() {
        return innerCirclePositionXReset;
    }

    public int getInnerCirclePositionYReset() {
        return innerCirclePositionYReset;
    }

    public int getOuterCirclePositionXReset() {
        return outerCirclePositionXReset;
    }

    public int getOuterCirclePositionYReset() {
        return outerCirclePositionYReset;
    }
    public void resetCustomViewObject(){
        innerCirclePositionX = innerCirclePositionXReset;
        innerCirclePositionY = innerCirclePositionYReset;
        outerCirclePositionX = outerCirclePositionXReset;
        outerCirclePositionY = outerCirclePositionYReset;
    }


}

