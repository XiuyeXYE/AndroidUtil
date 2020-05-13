package com.xy.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.xy.view.MyGLSurfaceView;

public class OpenGLES20Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_opengl_es30_activity);
        setContentView(new MyGLSurfaceView(this));
    }
}
