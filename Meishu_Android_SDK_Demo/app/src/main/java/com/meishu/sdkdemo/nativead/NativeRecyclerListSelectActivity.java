package com.meishu.sdkdemo.nativead;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.meishu.sdkdemo.R;

public class NativeRecyclerListSelectActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_recycler_list_select);
        findViewById(R.id.img_text_button).setOnClickListener(this);
        findViewById(R.id.img_text).setOnClickListener(this);
        findViewById(R.id.large_img_or_video).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.img_text_button:
                intent = new Intent(this, ImageTextButtonActivity.class);
                startActivity(intent);
                break;
            case R.id.img_text:
                intent = new Intent(this, ImageTextActivity.class);
                startActivity(intent);
                break;
            case R.id.large_img_or_video:
                intent = new Intent(this, TextAboveImageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
