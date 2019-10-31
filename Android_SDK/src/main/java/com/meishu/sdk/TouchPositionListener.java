package com.meishu.sdk;

import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import java.sql.Timestamp;

public class TouchPositionListener {
    private AdData adData;

    public TouchPositionListener(@NonNull AdData adData) {
        this.adData = adData;
    }

    public boolean onTouch(View v, MotionEvent event) {
        TouchPosition position = adData.getTouchPosition();
        if(position==null){
            position=new TouchPosition();
            adData.setTouchPosition(position);
        }
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            position.downX =event.getX();
            position.downY =event.getY();
        }
        if(event.getAction()==MotionEvent.ACTION_UP){
            position.upX =event.getX();
            position.upY =event.getY();
            position.touchTime=new Timestamp(System.currentTimeMillis());
        }
        return false;//不拦截点击事件
    }

    public class TouchPosition {
        private float downX;
        private float downY;

        private float upX;
        private float upY;

        private Timestamp touchTime;

        public float getDownX() {
            return downX;
        }

        public float getDownY() {
            return downY;
        }

        public float getUpX() {
            return upX;
        }

        public float getUpY() {
            return upY;
        }

        public Timestamp getTouchTime() {
            return touchTime;
        }
    }
}
