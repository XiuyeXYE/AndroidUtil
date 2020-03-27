package com.xy.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xy.util.UIUtil;

public class Demo1Activity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_demo1);
        findViewById(R.id.toDemo1ActivitySelfBtn).setOnClickListener(v -> {
            Intent intent = new Intent(this, Demo1Activity.class);
            this.startActivity(intent);
        });
        UIUtil.log(this, "Demo1Activity created!");
    }

}
