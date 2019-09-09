package com.meishu.sdk.nativ.image.meishu;

import com.meishu.sdk.nativ.recycler.AdInteractionListener;

public class NativeAdInteractionListenerAdapter implements AdInteractionListener {

    private com.meishu.sdk.nativ.image.AdInteractionListener apiInteractionListener;

    public NativeAdInteractionListenerAdapter(com.meishu.sdk.nativ.image.AdInteractionListener apiInteractionListener) {
        this.apiInteractionListener = apiInteractionListener;
    }

    @Override
    public void onAdClicked() {
        if (this.apiInteractionListener != null) {
            this.apiInteractionListener.onAdClicked();
        }
    }
}
