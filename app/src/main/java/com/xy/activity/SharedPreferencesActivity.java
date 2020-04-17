package com.xy.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.xy.util.DataUtil;

import java.util.UUID;

public class SharedPreferencesActivity extends AppCompatActivity {

    private static final String filename = "data.bat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        DataUtil.saveDataToSharedPreferences(this, filename, editor -> {
            editor.putBoolean("success", true);
            editor.putInt("age", 200);
            editor.putString("msg", "Hello World!" + UUID.randomUUID());
            editor.apply();
        });

        findViewById(R.id.readDataFromSharedPreferencesBtn).setOnClickListener(v -> {
            DataUtil.readDataFromSharedPreferences(this, filename, r -> {
                TextView tv = findViewById(R.id.readDataFromSharedPreferencesText);
                tv.setText(r.getAll().toString());
            });
        });
    }
}
