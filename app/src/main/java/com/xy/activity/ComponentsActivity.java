package com.xy.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.xiuye.util.cls.XType;
import com.xy.util.UIUtil;

public class ComponentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conponents);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        findViewById(R.id.progressBar).setOnClickListener(v -> {
            if (v instanceof ProgressBar) {
                ProgressBar p = XType.cast(v);
                int progress = p.getProgress();
                if (progress < p.getMax()) {
                    progress += 10;
                    p.setProgress(progress);
                } else {
                    UIUtil.log("arrival max value: ",
                            p.getMax() + ";",
                            "now value: ",
                            progress + ";");
                }


            }
        });
        findViewById(R.id.alertDialogBtn).setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("This is Dialog");
            dialog.setMessage("Something important.");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK", (d, which) -> {

            }).setNegativeButton("Cancel", (d, which) -> {

            });
            dialog.show();
        });

        findViewById(R.id.progressDialogBtn).setOnClickListener(v -> {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("This is ProgressDialog");
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(true);
            progressDialog.show();
        });

    }
}
