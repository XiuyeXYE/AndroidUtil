package com.xy.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDrawable extends Drawable {

    private final Paint redPaint;

    public MyDrawable() {
        redPaint = new Paint();
        redPaint.setARGB(255, 0, 0, 255);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        int width = getBounds().width();
        int height = getBounds().height();

        float radius = Math.min(width, height) / 2;

        canvas.drawCircle(width / 2, height / 2, radius, redPaint);

    }

    @Override
    public void setAlpha(int alpha) {


    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }
}
