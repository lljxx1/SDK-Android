package com.meishu.sdk.nativ.recycler;

import android.view.ViewGroup;
import android.view.ViewParent;

import com.meishu.sdk.TouchAdContainer;
import com.qq.e.ads.nativ.widget.NativeAdContainer;

public class RecyclerAdUtils {

    /**
     * 删除adContainer祖先节点中所有是NativeAdContainer的节点。
     * @param adContainer
     */
    public static void removeGdtNativeAdContainer(ViewGroup adContainer) {
        NativeAdContainer oldGdtNativeAdContainer = null;
        ViewGroup cur = adContainer;

        while (cur!=null&& cur.getParent() != null && (cur.getParent() instanceof ViewGroup)) {//移除父容器中包含的NativeAdContainer，因为NativeAdContainer是附加上去的
            ViewGroup parent = (ViewGroup) cur.getParent();
            if (parent instanceof NativeAdContainer) {//如果父节点是NativeAdContainer,则删除该父节点节点
                oldGdtNativeAdContainer = (NativeAdContainer) parent;
                if (oldGdtNativeAdContainer.getParent() != null) {
                    ViewGroup gdtParent = (ViewGroup) oldGdtNativeAdContainer.getParent();
                    oldGdtNativeAdContainer.removeView(cur);
                    gdtParent.removeView(oldGdtNativeAdContainer);
                    gdtParent.addView(cur);
                } else {
                    oldGdtNativeAdContainer.removeView(cur);
                }
            }
            ViewParent rawTypeParent = cur.getParent();
            if (rawTypeParent instanceof ViewGroup) {
                cur = (ViewGroup) rawTypeParent;
            } else {
                cur = null;
            }
        }
    }

    /**
     * 删除adContainer祖先节点中所有是NativeAdContainer的节点。
     * @param adContainer
     */
    public static void removeTouchAdContainer(ViewGroup adContainer) {
        TouchAdContainer oldTouchAdContainer = null;
        ViewGroup cur = adContainer;

        while (cur!=null&& cur.getParent() != null && (cur.getParent() instanceof ViewGroup)) {//移除父容器中包含的NativeAdContainer，因为NativeAdContainer是附加上去的
            ViewGroup parent = (ViewGroup) cur.getParent();
            if (parent instanceof TouchAdContainer) {//如果父节点是TouchAdContainer,则删除该父节点节点
                oldTouchAdContainer = (TouchAdContainer) parent;
                if (oldTouchAdContainer.getParent() != null) {
                    ViewGroup touchAdParent = (ViewGroup) oldTouchAdContainer.getParent();
                    oldTouchAdContainer.removeView(cur);
                    touchAdParent.removeView(oldTouchAdContainer);
                    touchAdParent.addView(cur);
                } else {
                    oldTouchAdContainer.removeView(cur);
                }
            }
            ViewParent rawTypeParent = cur.getParent();
            if (rawTypeParent instanceof ViewGroup) {
                cur = (ViewGroup) rawTypeParent;
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
