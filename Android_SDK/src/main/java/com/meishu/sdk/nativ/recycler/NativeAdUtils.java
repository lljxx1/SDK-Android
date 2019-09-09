package com.meishu.sdk.nativ.recycler;

import android.view.ViewGroup;
import android.view.ViewParent;

import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.widget.NativeAdContainer;

public class NativeAdUtils {

    /**
     * 删除adContainer父节点及父节点以上节点中所有是NativeAdContainer的节点。
     * @param adContainer
     */
    public static void removeGdtNativeAdContainer(ViewGroup adContainer) {
        NativeAdContainer oldGdtNativeAdContainer = null;
        ViewGroup cur = adContainer;
        while (cur != null) {//移除父容器中包含的NativeAdContainer，因为NativeAdContainer是附加上去的
            if (cur instanceof NativeAdContainer) {
                oldGdtNativeAdContainer = (NativeAdContainer) cur;
                if (oldGdtNativeAdContainer.getParent() != null) {
                    ViewGroup gdtParent = (ViewGroup) oldGdtNativeAdContainer.getParent();
                    oldGdtNativeAdContainer.removeView(adContainer);
                    gdtParent.removeView(oldGdtNativeAdContainer);
                    gdtParent.addView(adContainer);
                } else {
                    oldGdtNativeAdContainer.removeView(adContainer);
                }
            }
            ViewParent parent = cur.getParent();
            if (parent instanceof ViewGroup) {
                cur = (ViewGroup) parent;
            } else {
                cur = null;
            }
        }
    }

//    public static void removeGdtMediaView(ViewGroup mediaView){
//        MediaView oldGdtMediaViewContainer = null;
//        ViewGroup cur = mediaView;
//        while (cur != null) {//移除父容器中包含的MediaView，因为MediaView是附加上去的
//            if (cur instanceof MediaView) {
//                oldGdtMediaViewContainer = (MediaView) cur;
//                if (oldGdtMediaViewContainer.getParent() != null) {
//                    ViewGroup gdtParent = (ViewGroup) oldGdtMediaViewContainer.getParent();
//                    oldGdtMediaViewContainer.removeView(mediaView);
//                    gdtParent.removeView(oldGdtMediaViewContainer);
//                    gdtParent.addView(mediaView);
//                } else {
//                    oldGdtMediaViewContainer.removeView(mediaView);
//                }
//            }
//            ViewParent parent = cur.getParent();
//            if (parent instanceof ViewGroup) {
//                cur = (ViewGroup) parent;
//            } else {
//                cur = null;
//            }
//        }
//    }
}
