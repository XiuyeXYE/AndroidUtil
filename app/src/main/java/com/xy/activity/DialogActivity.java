package com.xy.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xy.util.Promise;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
//        new Promise<>(getActionBar()).exist(bar->{
//            bar.hide();
//        });
        Promise.resolve(getActionBar()).exist(bar -> {
            bar.hide();
        });

    }
}
