package com.xy.activity;


import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiuye.util.cls.XType;

public class AnimationActivity extends AbstractBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        TextView tv = findViewById(R.id.animationText);
        ValueAnimator animation = ValueAnimator.ofFloat(0f, 100f);
        animation.setDuration(3000);
        animation.addUpdateListener(a -> {
            tv.setTranslationX(XType.cast(a.getAnimatedValue()));
        });
        animation.start();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv, "translationY", 200);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
//        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 50f);
//        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 100f);
//        ObjectAnimator.ofPropertyValuesHolder(myView, pvhX, pvhY).start();
        ValueAnimator xmlAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(this,
                R.animator.animator);
        xmlAnimator.addUpdateListener(a -> {
            tv.setTextSize(XType.cast(a.getAnimatedValue()));
        });
        xmlAnimator.start();

        ImageView iv = findViewById(R.id.animImageView);
        iv.setBackgroundResource(R.drawable.imgbackanimator);
        AnimationDrawable ad = XType.cast(iv.getBackground());
        iv.setOnClickListener(v -> {
            ad.start();
        });

    }
}
