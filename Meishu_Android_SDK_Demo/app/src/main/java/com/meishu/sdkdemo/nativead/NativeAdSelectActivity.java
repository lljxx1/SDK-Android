package com.meishu.sdkdemo.nativead;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.meishu.sdkdemo.R;

public class NativeAdSelectActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_select_ad);
        findViewById(R.id.openNativeAD).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.openNativeAD:
                intent.setClass(this, NativeAdActivity.class);
                startActivity(intent);
                break;
        }
    }
}
