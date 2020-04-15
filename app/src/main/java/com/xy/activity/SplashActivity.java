package com.xy.activity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.xiuye.util.cls.XType;
import com.xy.util.UIUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    public Object getSystemService(@NonNull String name) {
        return super.getSystemService(name);
    }

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

        findViewById(R.id.toPercentLayoutActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, PercentLayoutActivity.class));
        });

        findViewById(R.id.toFruitListViewActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, ListViewActivity.class));
        });

        findViewById(R.id.notificationBtn).setOnClickListener(v -> {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Game")
                    .setSmallIcon(R.drawable.greenapple)
                    .setContentTitle("My notification")
                    .setContentText("Much longer text that cannot fit one line...")
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Much longer text that cannot fit one line..."))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            Notification notification = builder.build();
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(123, notification);
        });

        findViewById(R.id.toRecyclerViewActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, RecyclerViewActivity.class));
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
