package com.meishu.sdk.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebSettings;

import com.meishu.sdk.domain.HttpResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public final class HttpUtil {
    private static final String TAG = "HttpUtil";

    /**
     * 参数类型
     * "text", 文本
     * "image", 图片
     * "audio",音频
     * "video"，视频
     * "object",其他
     */
    private static final MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/png");
    private static final MediaType MEDIA_TYPE_AUDIO = MediaType.parse("audio/mp3");
    private static final MediaType MEDIA_TYPE_VIDEO = MediaType.parse("video/mp4");
    private static final MediaType MEDIA_TYPE_OBJECT = MediaType.parse("application/octet-stream");
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient client;

    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();
    }

    public static void asyncGetJson(
            @NotNull String url,
            Map<String, String> params,
            @NotNull final HttpGetJsonCallback<OriginalResponse> callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get().build();
        Log.d(TAG, "url["+request.url().url().toString()+"]");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                OriginalResponse originalResponse = new OriginalResponse(response.code(), response.isSuccessful());
                originalResponse.setHeaders(response.headers());
                if (responseBody != null) {
                    originalResponse.setBody(responseBody.string());
                }
                response.close();
                callback.onResponse(originalResponse);
            }
        });
    }

    private void asyncGetBytes(
            @NotNull String url,
            @NotNull final HttpGetBytesCallback callback) {
        asyncGetBytes(url, null, callback);
    }

    private void asyncGetBytes(
            @NotNull String url,
            Map<String, String> params,
            @NotNull final HttpGetBytesCallback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                HttpResponse<byte[]> httpResponse = new HttpResponse<>();
                if (response.isSuccessful()) {
                    httpResponse.setSuccessful(true);
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        httpResponse.setResponseBody(responseBody.bytes());
                    }
                } else {
                    httpResponse.setSuccessful(false);
                    httpResponse.setErrorCode(response.code());
                    httpResponse.setErrorDescription(response.message());
                }
                response.close();
                callback.onResponse(httpResponse);
            }
        });
    }


    private static void asyncGetWithHeaders(
            @NotNull String url,
            @Nullable Map<String, String> headers,
            @NotNull final HttpGetWithStringCallback callback) {
        Headers.Builder headersBuilder = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                headersBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        Request request = new Request.Builder()
                .headers(headersBuilder.build())
                .url(urlBuilder.build())
                .get().build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                HttpResponse<String> httpResponse = new HttpResponse<>();
                if (response.isSuccessful()) {
                    httpResponse.setSuccessful(true);
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        httpResponse.setResponseBody(responseBody.string());
                    }
                } else {
                    httpResponse.setSuccessful(false);
                    httpResponse.setErrorCode(response.code());
                    httpResponse.setErrorDescription(response.message());
                }
                response.close();
                callback.onResponse(httpResponse);
            }
        });
    }

    public static void asyncGetWithWebViewUA(
            @NonNull Context context,
            @NotNull String url,
            @NotNull final HttpGetWithStringCallback callback) {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", WebSettings.getDefaultUserAgent(context));
        asyncGetWithHeaders(url, headers, callback);
    }

}
