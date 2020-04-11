package com.xy.view.layout;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.xiuye.util.cls.XType;
import com.xy.activity.R;
import com.xy.util.UIUtil;

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        findViewById(R.id.title_back).setOnClickListener(v -> {
            UIUtil.log("Back prepared!");
            Context ctx = getContext();
            if (ctx instanceof Activity) {
                Activity ac = XType.cast(ctx);
                if (!ac.isFinishing())
                    ac.finish();
            }
        });
        findViewById(R.id.title_edit).setOnClickListener(v -> {
            UIUtil.log("You clicked Edit button");
        });
    }
}
