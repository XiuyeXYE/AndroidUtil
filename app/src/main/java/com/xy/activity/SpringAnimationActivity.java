package com.xy.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

public class SpringAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_animation);
        View img = findViewById(R.id.springImageView);
        SpringAnimation springAnimation = new SpringAnimation(img, DynamicAnimation.TRANSLATION_Y, 0);

        springAnimation.setStartValue(500);
        springAnimation.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        springAnimation.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
//        vt.computeCurrentVelocity(1000);
//        float velocity = vt.getYVelocity();
//        anim.setStartVelocity(velocity);

        springAnimation.addEndListener((animation, canceled, value, velocity) -> {
            springAnimation.animateToFinalPosition(value);
        });

        springAnimation.start();
    }
}
