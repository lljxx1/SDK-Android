package com.meishu.sdk.utils;

import com.meishu.sdk.domain.HttpResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public interface HttpGetJsonCallback<response> {

    void onFailure(@NotNull IOException e);

    void onResponse(response httpResponse) throws IOException;
}
