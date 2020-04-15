package com.xy.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.xy.util.UIUtil;

public class HelloBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        UIUtil.log("onReceive:", context, intent);
    }
}
