package com.meishu.sdk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class TouchAdContainer extends FrameLayout {

    private TouchPositionListener touchPositionListener;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.touchPositionListener != null) {
            this.touchPositionListener.onTouch(this, ev);
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setTouchPositionListener(@NonNull TouchPositionListener touchPositionListener) {
        this.touchPositionListener = touchPositionListener;
    }

    public TouchAdContainer(Context context) {
        super(context);
    }

    public TouchAdContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchAdContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
