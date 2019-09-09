package com.meishu.sdk.utils;

import okhttp3.Headers;

public class OriginalResponse {
    private boolean successful;
    private int code;
    private String body;
    private Headers headers;

    public OriginalResponse(int code, boolean successful) {
        this.code = code;
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public int getCode() {
        return code;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }
    public String header(String name){
        String headerValue = null;
        if (headers != null) {
            headerValue = headers.get(name);
        }
        return headerValue;
    }
}
