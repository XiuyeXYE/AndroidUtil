package com.xy.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.xy.drawable.MyDrawable;

public class MyDrawableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_drawable);
        ImageView imageView = findViewById(R.id.myDrawableImageView);
        imageView.setImageDrawable(new MyDrawable());
        imageView.setContentDescription("Desc");
    }
}
