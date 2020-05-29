package com.xy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.xiuye.sharp.Promise;
import com.xiuye.util.cls.XType;

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
//        findViewById(id).setOnClickListener(v -> {
//            to(clazz);
//        });
//        byId(id).exist(v -> {
//            v.setOnClickListener(vi -> to(clazz));
//        });

        clickBind(id, vi -> to(clazz));
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

    public <R extends View> Promise<R> byId(int id) {
//        return new Promise<>(() -> {
//            return findViewById(id);
//        });
        //这个findViewById不能默认调用 resolve(r)反正是
        //奇了怪了！也许和View 实现的接口 callback 有些关系吧！
        //不能直接传入resolve中，难道是因为返回的是泛型类
        // 所以 不知道 具体的类型？
        //确实，经过验证，返回类型是泛型的话，会有类型不确定，
        // 出现一些莫名其妙的问题！
        // 解决办法就是分步变量传入！
//        R r = findViewById(id);
        return Promise.resolve(findViewById(id));
    }


}
