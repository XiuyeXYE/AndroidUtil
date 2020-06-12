package com.xy.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.xiuye.sharp.Promise;
import com.xiuye.util.cls.XType;
import com.xy.lib.zxing.CaptureActivity;
import com.xy.network.TicketApi;
import com.xy.util.UIUtil;

import java.util.Map;

public class ScanCodeActivity extends AbstractBaseActivity {

    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);

//        clickTo(R.id.scanGunText, CameraCaptureActivity.class, 1);
        clickTo(R.id.scanGunText, CaptureActivity.class, 1);

        dialog = new AlertDialog.Builder(this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Promise.beginS()
                .ef(resultCode == RESULT_OK)
                .then(() -> {
                    Promise.beginS()
                            .match(requestCode)
                            .as(1)
                            .then(() -> {
                                Promise.resolve(data).exist(d -> {
                                    String content = d.getStringExtra("codedContent");
                                    UIUtil.log(this, "扫描结果为：", content);
                                    requestTicket(content);
                                });
                            })
                            .end();
                })
                .end();
    }

    private void requestTicket(String content) {

        TicketApi api = new TicketApi();
        Map<String, Object> map = XType.map();
        map.put("ticketNo", content);
        api.setPathMap(map);
        api.setwBack(data1 -> {
            dialog.setTitle("信息");
            dialog.setMessage(data1.getInfo());
            dialog.setPositiveButton("确定", null);
            dialog.show();
        });
        api.request();

    }
}
