package com.xy.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xy.lifecycle.MainActivityLifecycle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getLifecycle().addObserver(new MainActivityLifecycle());
    }

}
