package com.xy.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xy.view.CustomDrawableView;

public class CustomDrawableViewActivity extends AppCompatActivity {

    CustomDrawableView customDrawableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        customDrawableView = new CustomDrawableView(this);

        setContentView(customDrawableView);

    }
}
