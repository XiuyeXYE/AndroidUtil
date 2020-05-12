package com.xy.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class AbstractBaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public <T extends AppCompatActivity> void to(Class<T> clazz) {
        startActivity(new Intent(this, clazz));
    }

    public void to(Intent intent) {
        startActivity(intent);
    }

}
