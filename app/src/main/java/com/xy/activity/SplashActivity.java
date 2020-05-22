package com.xy.activity;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.xiuye.util.cls.XType;
import com.xiuye.util.code.XYClassLoader;
import com.xiuye.util.code.XYCompiler;
import com.xy.activity.ui.login.LoginActivity;
import com.xy.itf.ADemo;
import com.xy.service.HelloIntentService;
import com.xy.util.UIUtil;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class SplashActivity extends AbstractBaseActivity {

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
            Intent intent = new Intent(this, ListViewActivity.class);
            PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Game")
                    .setSmallIcon(R.drawable.greenapple)
                    .setContentTitle("My notification")
                    .setContentText("Much longer text that cannot fit one line...")
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.greenapple))
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
//                    .setStyle(new NotificationCompat.BigTextStyle()
//                            .bigText("Much longer text that cannot fit one line..."))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            Notification notification = builder.build();
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(2, notification);
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
            managerCompat.notify(3, builder.build());
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
        findViewById(R.id.toFragmentActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, FragmentActivity.class));
        });

        findViewById(R.id.generateClassAndCallFunctionBtn).setOnClickListener(v -> {

            String aDemoCode =
                    "package com.xy.itf.impl;" +
                            "import com.xiuye.util.log.XLog;\n" +
                            "import com.xy.itf.ADemo;\n" +
                            "class ADemoImpl implements ADemo{\n" +
                            "\n" +
                            "    @Override\n" +
                            "    public String says() {\n" +
                            "        XLog.lg(\"called ADemoImpl::says()\");\n" +
                            "        return \"Hello,I am ADemo son : ADemoImpl!\";\n" +
                            "    }\n" +
                            "}";
            Map<String, String> codes = XType.map();
            codes.put("com.xy.itf.impl.ADemoImpl", aDemoCode);
            UIUtil.log(XYCompiler.compileCode(codes) ? "Compile successful!" : "Compile failed!");
            try {
                XYClassLoader cl = XType.createClassLoader();
                Class<ADemo> clazz = cl.load("com.xy.itf.impl.ADemoImpl");
                ADemo ad = clazz.newInstance();
                UIUtil.log("ADemo says:", ad.says());
            } catch (Exception e) {
                e.printStackTrace();
                UIUtil.log(e.getMessage());
            } finally {
                UIUtil.log("Loading class over!");
            }
        });

        findViewById(R.id.springForAndroidBtn).setOnClickListener(v -> {
            // The connection URL
            String url = "https://www.baidu.com";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();

            // Add the String message converter
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            // Make the HTTP GET request, marshaling the response to a String
            String result = restTemplate.getForObject(url, String.class);
            UIUtil.log("spring for android (RestTemplate) : ", result);

        });

        findViewById(R.id.toLoginActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, LoginActivity.class));
        });
        findViewById(R.id.toStoreFileActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, StoreFileActivity.class));
        });
        findViewById(R.id.toSharedPreferencesActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, SharedPreferencesActivity.class));
        });
        findViewById(R.id.toSqliteActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, SqliteActivity.class));
        });
        findViewById(R.id.toLitepalActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, LitepalActivity.class));
        });

        findViewById(R.id.toContactsActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, ContantsActivity.class));
        });
        findViewById(R.id.toContentProviderActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, ContentProviderActivity.class));
        });

        findViewById(R.id.toPhotoActivityBtn).setOnClickListener(v -> {
            startActivity(XType.newInstance(Intent::new, this, PhotoActivity.class));
        });

        findViewById(R.id.toAudioActivityBtn).setOnClickListener(v -> {
//            startActivity(XType.newInstance(Intent::new,this,AudioActivity.class));
            to(AudioActivity.class);
        });
        findViewById(R.id.toVideoActivityBtn).setOnClickListener(v -> {
//            startActivity(XType.newInstance(Intent::new,this,AudioActivity.class));
            to(VideoViewActivity.class);
        });

        findViewById(R.id.toPDFActivityBtn).setOnClickListener(v -> {
            to(PDFActivity.class);
        });
        findViewById(R.id.toAnimationActivityBtn).setOnClickListener(v -> {
            to(AnimationActivity.class);
        });
        findViewById(R.id.toMixtureActivityBtn).setOnClickListener(v -> {
            to(MixtureActivity.class);
        });
        findViewById(R.id.toSettingsActivityBtn).setOnClickListener(v -> {
            to(MySettingsActivity.class);
        });

        findViewById(R.id.toSearchActivityBtn).setOnClickListener(v -> {
            to(SearchableActivity.class);
        });

        findViewById(R.id.toZoomActivityBtn).setOnClickListener(v -> {
            to(ZoomActivity.class);
        });

        findViewById(R.id.toSpringAnimationActivityBtn).setOnClickListener(v -> {
            to(SpringAnimationActivity.class);
        });

        findViewById(R.id.toOpenGLES20ActivityBtn).setOnClickListener(v -> {
            to(OpenGLES20Activity.class);
        });
//        findViewById(R.id.toCustomDrawableActivityBtn).setOnClickListener(v -> {
//            to(CustomDrawableViewActivity.class);
//        });
        //equals:
        this.clickTo(R.id.toCustomDrawableViewActivityBtn, CustomDrawableViewActivity.class);

        clickTo(R.id.toMyDrawableActivityBtn, MyDrawableActivity.class);

        clickTo(R.id.toTransitionDrawableActivityBtn, TransitionDrawableActivity.class);

        clickTo(R.id.toImageViewActivityBtn, ImageViewActivity.class);

        clickTo(R.id.toChartsActivityBtn, ChartsActivity.class);

        clickTo(R.id.toDialogActivityBtn, DialogActivity.class);

        clickTo(R.id.toCameraActivityBtn, CameraActivity.class);

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

