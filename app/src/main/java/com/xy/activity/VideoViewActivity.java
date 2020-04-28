package com.xy.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.xy.util.UIUtil;

import java.io.File;

public class VideoViewActivity extends AppCompatActivity {

    private VideoView videoView;

    private void initVideoView() {
        File file = new File(Environment.getExternalStorageDirectory(), "Pictures/movie.mp4");
        this.videoView.setVideoPath(file.getPath());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        videoView = findViewById(R.id.videoView);
        findViewById(R.id.videoPlayBtn).setOnClickListener(v -> {
            if (!videoView.isPlaying()) {
                videoView.start();
            }
        });
        findViewById(R.id.videoPauseBtn).setOnClickListener(v -> {
            if (videoView.isPlaying()) {
                videoView.pause();
            }
        });
        findViewById(R.id.videoRestartBtn).setOnClickListener(v -> {
            if (videoView.isPlaying()) {
                videoView.resume();
            }
        });


        UIUtil.checkAndRequestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, 1, () -> {
            initVideoView();
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        UIUtil.handleRequestPermissionResult(this, requestCode, permissions, grantResults, 1, () -> {
            initVideoView();
        });
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
    }
}
