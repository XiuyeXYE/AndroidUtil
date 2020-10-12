package com.xy.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xiuye.sharp.X;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
//        new Promise<>(getActionBar()).exist(bar->{
//            bar.hide();
//        });
        X.resolve(getActionBar()).E(bar -> {
            bar.hide();
            return X.DEFAULT_OBJECT;
        });

    }
}
