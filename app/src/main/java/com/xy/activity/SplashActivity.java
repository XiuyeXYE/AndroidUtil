package com.xy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.xiuye.util.cls.XType;
import com.xy.util.UIUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findViewById(R.id.toMainActivityBtn).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });
        findViewById(R.id.toCustomViewActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, CustomViewActivity.class));
        });
        findViewById(R.id.toSystemInfoBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, SystemInfoActivity.class));
        });
        findViewById(R.id.toComponentsActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, ComponentsActivity.class));
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.splash, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                UIUtil.log("You clicked Add");
                break;
            case R.id.remove_item:
                UIUtil.log("You clicked Remove");
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
