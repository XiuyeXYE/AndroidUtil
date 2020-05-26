package com.xy.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xy.util.UIUtil;

public class ContentProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        findViewById(R.id.myContentProviderBtn).setOnClickListener(v -> {
            Uri authority = Uri.parse("content://com.xy.provider");
            Cursor cursor = this.getContentResolver().query(authority, null, null, null, null);
            UIUtil.log(cursor);
//            UIUtil.log(client.getLocalContentProvider());
//            UIUtil.log(client.getLocalContentProvider().query(null,null,null,null,null));
//            try {
//                UIUtil.log(client.query(null,null,null,null,null));
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
        });
    }
}
