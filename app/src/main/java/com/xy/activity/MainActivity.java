package com.xy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.xy.lifecycle.MainActivityLifecycle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getLifecycle().addObserver(new MainActivityLifecycle());

        Button toDemo1ActivityBtn = this.findViewById(R.id.toDemo1ActivityBtn);
        toDemo1ActivityBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo1Activity.class);
            this.startActivity(intent);
        });
    }

}
