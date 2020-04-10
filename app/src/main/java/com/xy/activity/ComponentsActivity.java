package com.xy.activity;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.xiuye.util.cls.XType;

public class ComponentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conponents);
        findViewById(R.id.progressBar).setOnClickListener(v -> {
            if (v instanceof ProgressBar) {
                ProgressBar p = XType.cast(v);
                int progress = p.getProgress();
                progress += 10;
                p.setProgress(progress);

            }
        });
    }
}
