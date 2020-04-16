package com.xy.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xiuye.util.cls.XType;
import com.xy.broadcast.HelloBroadcastReceiver;
import com.xy.util.UIUtil;

public class BroadcastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        BroadcastReceiver br = new HelloBroadcastReceiver();
        //广播接收器两种注册方式
        //1.AndroidManifest.xml 中声名
        //2.直接用以下代码注册
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("test.nothingfor");
        intentFilter.addCategory("test.nothingfor");
        registerReceiver(br, intentFilter);
        //注意：要注册本地广播，请调用 LocalBroadcastManager
        // .registerReceiver(BroadcastReceiver, IntentFilter)。
        findViewById(R.id.sendBroadcastBtn).setOnClickListener(v -> {
            Intent intent = XType.newInstance(Intent::new);
            intent.setAction("test.nothingfor");
            intent.addCategory("test.nothingfor");
            this.sendBroadcast(intent);
            UIUtil.log("already send broadcast!");
        });

        BroadcastReceiver networkBr = XType.newInstance(NetworkChangeReceiver::new);
        intentFilter = XType.newInstance(IntentFilter::new);
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkBr, intentFilter);

    }

    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = XType.cast(getSystemService(Context.CONNECTIVITY_SERVICE));
            NetworkInfo network = connectivityManager.getActiveNetworkInfo();
            if (network != null && network.isAvailable()) {
                UIUtil.log(context, network, "network is available");
            } else {
                UIUtil.log(context, network, "network is unavailable");
            }
        }
    }
}
