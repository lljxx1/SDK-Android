package com.meishu.sdk.domain;

import android.support.annotation.Nullable;

public class HttpResponse<body> {
    private boolean successful;
    /**
     * 连接错误码，如200：成功。404：未找到网页
     */
    private int errorCode=200;
    /**
     * 连接错误描述，如未找到网页
     */
    private String errorDescription;
    /**
     * 响应成功后，响应体
     */
    @Nullable
    private body responseBody;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public body getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(body responseBody) {
        this.responseBody = responseBody;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
