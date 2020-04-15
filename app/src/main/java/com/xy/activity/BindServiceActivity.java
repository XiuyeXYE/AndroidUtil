package com.xy.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.xiuye.util.cls.XType;
import com.xy.service.LocalService;
import com.xy.util.UIUtil;

public class BindServiceActivity extends AppCompatActivity {

    private LocalService localService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);


    }

    public void serviceClicked(View v) {
        if (localService != null) {
            UIUtil.log(localService.getMsg());
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            UIUtil.log("onServiceConnected");
            localService = XType.cast(XType.<LocalService.LocalBinder, IBinder>cast(service).getService());

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            UIUtil.log("onServiceDisconnected");
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        bindService(XType.newInstance(Intent::new, this, LocalService.class), connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }
}
