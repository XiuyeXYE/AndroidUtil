package com.xy.activity;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.xiuye.util.cls.XType;
import com.xy.service.HelloIntentService;
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
        findViewById(R.id.toRecyclerViewHorizontalActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, RecyclerViewHorizontalActivity.class));
        });
        findViewById(R.id.toRecyclerViewStaggerActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, RecyclerViewStaggerActivity.class));
        });

        findViewById(R.id.toBubbleActivityBtn).setOnClickListener(v -> {
            // Create bubble intent
            Intent target = new Intent(this, BubbleActivity.class);
            PendingIntent bubbleIntent =
                    PendingIntent.getActivity(this, 0, target, 0 /* flags */);

//            // Create bubble metadata
//            Notification.BubbleMetadata bubbleData =
//                    new Notification.BubbleMetadata.Builder()
//                            .setDesiredHeight(600)
//                            .setIcon(Icon.createWithResource(this, R.drawable.grape))
//                            .setIntent(bubbleIntent)
//                            .build();

            // Create notification
//            Person chatBot = new Person.Builder()
//                    .setBot(true)
//                    .setName("BubbleBot")
//                    .setImportant(true)
//                    .build();

            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this, "game")
                            .setContentIntent(bubbleIntent)
                            .setSmallIcon(R.drawable.greenapple)
//                            .setBubbleMetadata()
                            .addPerson("What?");
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            managerCompat.notify(456, builder.build());
        });

        findViewById(R.id.startDemoServiceBtn).setOnClickListener(v -> {

            startService(XType.newInstance(Intent::new, this, HelloIntentService.class));

        });
        findViewById(R.id.toBindServiceActivityBtn).setOnClickListener(v -> {

            startActivity(XType.newInstance(Intent::new, this, BindServiceActivity.class));

        });
        findViewById(R.id.toBroadcastActivityBtn).setOnClickListener(v -> {

            startActivity(XType.newInstance(Intent::new, this, BroadcastActivity.class));

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
