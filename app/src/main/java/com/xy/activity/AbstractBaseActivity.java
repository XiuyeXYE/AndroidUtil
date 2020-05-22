package com.xy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.xiuye.util.cls.XType;
import com.xy.util.Promise;

public abstract class AbstractBaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public <T extends AppCompatActivity> void to(Class<T> clazz) {
        to(new Intent(this, clazz));
    }

    public void to(Intent intent) {
        startActivity(intent);
    }

    public void to(Intent intent, int requesCode) {
        startActivityForResult(intent, requesCode);
    }

    public <T extends AppCompatActivity> void clickTo(int id, Class<T> clazz) {
        findViewById(id).setOnClickListener(v -> {
            to(clazz);
        });
    }

    public void clickBind(int id) {
        Promise.resolve(this instanceof View.OnClickListener)
                .truely(b -> {
                    clickBind(id, XType.cast(this));
                });
    }

    public void clickBind(int id, View.OnClickListener clicker) {
        byId(id).exist(view -> {
                    view.setOnClickListener(clicker);
                }
        );
    }

    public <T extends View> Promise<T> byId(int id) {
        return new Promise<>(() -> {
            return findViewById(id);
        });
    }


}
