package com.xy.lifecycle;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.xy.util.LogUtil;

public class MainActivityLifecycle implements LifecycleObserver {

    private static final String TAG = "MainActivityLifecycle";

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy(LifecycleOwner source) {
        Log.d(TAG, "destroy source:" + source);
        if (source instanceof AppCompatActivity) {
            LogUtil.closeActivityDialog((AppCompatActivity) source);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void init(LifecycleOwner source) {
        Log.d(TAG, "create source:" + source);
        if (source instanceof AppCompatActivity) {
            LogUtil.log((AppCompatActivity) source, "Punch down EAST 731!");
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume(LifecycleOwner owner) {
        Log.d(TAG, "resume source:" + owner);
        if (owner instanceof AppCompatActivity) {

        }

    }


}
