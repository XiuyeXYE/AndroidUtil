package com.xy.activity;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.NonNull;

import com.xy.util.UIUtil;

import java.io.File;
import java.io.IOException;

public class AudioActivity extends AbstractBaseActivity {

    private MediaPlayer mediaPlayer = new MediaPlayer();

    private void initMediaPlayer() {
        File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");
        try {
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        UIUtil.log(this, "path=", Environment.getExternalStorageDirectory());
        findViewById(R.id.audioPlayBtn).setOnClickListener(v -> {
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        });

        findViewById(R.id.audioPauseBtn).setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });
        findViewById(R.id.audioStopBtn).setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.reset();

            }
        });

        UIUtil.checkAndRequestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, 1, () -> {
            initMediaPlayer();
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        UIUtil.handleRequestPermissionResult(this, requestCode, permissions, grantResults, 1, () -> {
            initMediaPlayer();
        });
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
