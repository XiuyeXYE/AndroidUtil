package com.xy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

import com.xy.activity.R;

public class CustomDrawableView extends View {

    ShapeDrawable shapeDrawable;

    public CustomDrawableView(Context context) {
        super(context);
        int x = 10;
        int y = 10;
        int width = 300;
        int height = 50;

        setContentDescription(context.getResources().getString(R.string.desc));

        shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(0xff74AC23);
        shapeDrawable.setBounds(x, y, x + width, y + height);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shapeDrawable.draw(canvas);
    }
}

