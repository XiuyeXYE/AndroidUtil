package com.xy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.xiuye.util.log.XLog;
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

    public <T extends AppCompatActivity> void clickBind(int id, Class<T> clazz) {
        findViewById(id).setOnClickListener(v -> {
            to(clazz);
        });
    }

    public <T extends View> Promise<T> byId(int id) {
        return new Promise<T>(() -> {
            return findViewById(id);
        });
    }


    public static void main(String[] args) {
        Promise m = new Promise(() -> {
            return 123;
        }).then((i) -> {
            System.out.println(i);
            System.out.println(i.getClass());
            return "ABC";
        }).then((s) -> {
            XLog.log(s);
            return XLog.class;
        }).then((x) -> {
            System.out.println(x);
        });
        System.out.println(m);
    }

}
