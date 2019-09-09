package com.meishu.sdk.utils;

import android.util.Log;

import com.meishu.sdk.domain.HttpResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DefaultHttpGetWithNoHandlerCallback implements HttpGetWithStringCallback {
    private static final String TAG = "DefaultHttpGetWithHeade";

    @Override
    public void onFailure(@NotNull IOException e) {
        Log.e(TAG, "onFailure: ", e);
    }

    @Override
    public void onResponse(HttpResponse<String> httpResponse) throws IOException {
    }
}
