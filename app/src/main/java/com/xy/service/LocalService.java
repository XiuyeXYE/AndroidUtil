package com.xy.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.xy.util.UIUtil;

public class LocalService extends Service {

    private final LocalBinder binder = new LocalBinder();


    public LocalService() {
        UIUtil.log("call LocalService constructor!");
    }

    public class LocalBinder extends Binder {
        public Service getService() {
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public String getMsg() {
        return "Hello World!";
    }
}
