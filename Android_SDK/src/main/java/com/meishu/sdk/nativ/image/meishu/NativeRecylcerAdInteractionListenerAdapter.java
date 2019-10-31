package com.meishu.sdk.nativ.image.meishu;

import com.meishu.sdk.nativ.image.ImageAdInteractionListener;
import com.meishu.sdk.nativ.recycler.RecylcerAdInteractionListener;

public class NativeRecylcerAdInteractionListenerAdapter implements RecylcerAdInteractionListener {

    private ImageAdInteractionListener apiInteractionListener;

    public NativeRecylcerAdInteractionListenerAdapter(ImageAdInteractionListener apiInteractionListener) {
        this.apiInteractionListener = apiInteractionListener;
    }

    @Override
    public void onAdClicked() {
        if (this.apiInteractionListener != null) {
            this.apiInteractionListener.onAdClicked();
        }
    }
}
