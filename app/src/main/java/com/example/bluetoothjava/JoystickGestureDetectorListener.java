package com.example.bluetoothjava;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

interface CoordinateChangedListener{
    void onChangedCoordinates(int originX, int originY, int newX, int newY);
}

public class JoystickGestureDetectorListener extends GestureDetector.SimpleOnGestureListener  {

    private ViewCustomObject joyStick;

    private List<CoordinateChangedListener> listeners = new ArrayList<CoordinateChangedListener>();

    public void addListener(CoordinateChangedListener toAdd) {
        listeners.add(toAdd);
    }

    JoystickGestureDetectorListener(ViewCustomObject viewCustomObject) {
        joyStick = viewCustomObject;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.i("MotionAction",Integer.toString(e.getAction()));
        return true;
    }




    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i("measurement","Height: " + joyStick.getHeight());
        Log.i("measurement","Width: " + joyStick.getWidth());
        Log.i("measurement","Bottom: " + joyStick.getBottom());
        joyStick.getBottom();

        int currentY = joyStick.getInnerCirclePositionY() - (int)distanceY;
        int currentX = joyStick.getInnerCirclePositionX() - (int)distanceX;

        int vectorX = currentX - joyStick.getInnerCirclePositionXReset();
        int vectorY = currentY - joyStick.getInnerCirclePositionYReset();

        double radius =  Math.sqrt(Math.pow(vectorX,2)+Math.pow(vectorY,2));

         double boundaryRadius = joyStick.getOuterCircleRadiusRadius()-joyStick.getInnerCircleRadius();
         Log.i("Events","OnScroll");
        if(radius <= boundaryRadius){
            Log.i("Events","ChangePosition");
            joyStick.setInnerCirclePositionX(currentX);
            joyStick.setInnerCirclePositionY(currentY);
            for (CoordinateChangedListener hl : listeners)
                hl.onChangedCoordinates(joyStick.getInnerCirclePositionXReset(),joyStick.getInnerCirclePositionYReset(),currentX,currentY);
            return true;
        }

       return super.onScroll(e1, e2, distanceX, distanceY);
    }




}
