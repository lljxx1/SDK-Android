package com.meishu.sdk.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.meishu.sdk.R;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private static ConfirmHandler confirmHandler;
    private static CancelHandler cancelHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_alert_dialog);
        findViewById(R.id.cancel_button).setOnClickListener(this);
        findViewById(R.id.confirm_button).setOnClickListener(this);
    }

    public static void setConfirmHandler(ConfirmHandler confirmHandler) {
        AlertDialogActivity.confirmHandler = confirmHandler;
    }

    public static void setCancelHandler(CancelHandler cancelHandler) {
        AlertDialogActivity.cancelHandler = cancelHandler;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.cancel_button) {
            if (AlertDialogActivity.cancelHandler != null) {
                AlertDialogActivity.cancelHandler.handle();
            }
        } else if (i == R.id.confirm_button) {
            if (AlertDialogActivity.confirmHandler != null) {
                AlertDialogActivity.confirmHandler.handle();
            }
        }
        AlertDialogActivity.this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AlertDialogActivity.confirmHandler = null;
        AlertDialogActivity.cancelHandler = null;
    }

    public interface ConfirmHandler {
        void handle();
    }

    public interface CancelHandler {
        void handle();
    }
}
