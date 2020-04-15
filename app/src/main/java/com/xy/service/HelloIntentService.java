package com.xy.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.xy.util.UIUtil;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class HelloIntentService extends IntentService {

    public HelloIntentService() {
        super("HelloIntentService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        UIUtil.log("open HelloIntentService!");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            UIUtil.log(intent);
        }
        stopSelf();
    }


}
