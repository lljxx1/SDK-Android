package com.meishu.sdk;

public abstract class BaseAdData implements AdData {
    private TouchPositionListener.TouchPosition touchPosition;

    @Override
    public void setTouchPosition(TouchPositionListener.TouchPosition touchPosition) {
        this.touchPosition=touchPosition;
    }

    @Override
    public TouchPositionListener.TouchPosition getTouchPosition() {
        return this.touchPosition;
    }
}
