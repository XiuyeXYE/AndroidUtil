package com.xy.activity;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;

import com.xiuye.util.cls.XType;

public class TransitionDrawableActivity extends AbstractBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_drawable);
        TransitionDrawable transition = XType.cast(ResourcesCompat.getDrawable(getResources(), R.drawable.expand_collapse, null));

        this.<ImageView>byId(R.id.transitionDrawableImageView).then((image) -> {
            image.setImageDrawable(transition);
            image.setContentDescription("Desc transition");
            // Then you can call the TransitionDrawable object's methods.
            transition.startTransition(5000);
        });


    }
}
