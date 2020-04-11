package com.xy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.xiuye.util.cls.XType;
import com.xy.util.UIUtil;

public class PercentLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent_layout);

    }

    public void buttonClicked(View v) {
        if (v instanceof Button) {
            Button b = XType.cast(v);
            UIUtil.log(b.getText().toString(), "clicked!");
        }
    }
}
