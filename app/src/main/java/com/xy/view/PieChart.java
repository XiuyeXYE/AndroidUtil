package com.xy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.xy.activity.R;
import com.xy.util.UIUtil;

public class PieChart extends View {

    private boolean mShowText;
    private int textPos;
    private Paint textPaint;
    private Paint piePaint;
    private Paint shadowPaint;
    private int textColor;
    private float textHeight;
    private float textWidth;


    public PieChart(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);

        TypedArray a = ctx.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.PieChart, 0, 0);
        mShowText = a.getBoolean(R.styleable.PieChart_showText, false);
        textPos = a.getInteger(R.styleable.PieChart_labelPosition, 0);
        textHeight = a.getInteger(R.styleable.PieChart_textHeight, 10);

        a.recycle();

        this.init();
    }

//    public boolean isShowText(){
//        return mShowText;
//    }
//
//    public void setShowText(boolean showText){
//        mShowText = showText;
//        invalidate();
//        requestLayout();
//    }

    private void init() {

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        textPaint.setStrokeWidth(10);//宽度10
        textPaint.setColor(Color.BLACK);
        if (textHeight == 0) {
            textHeight = textPaint.getTextSize();
        } else {
            textPaint.setTextSize(textHeight);
        }
//        piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        piePaint.setStyle(Paint.Style.FILL);
//        piePaint.setTextSize(textHeight);
//        shadowPaint = new Paint(0);
//        shadowPaint.setColor(0xff101010);
//        shadowPaint.setMaskFilter(new BlurMaskFilter(8,BlurMaskFilter.Blur.NORMAL));


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Account for padding
//        float xpad = (float)(getPaddingLeft() + getPaddingRight());
//        float ypad = (float)(getPaddingTop() + getPaddingBottom());
//
//        // Account for the label
//        if (mShowText) xpad += textWidth;
//
//        float ww = (float)w - xpad;
//        float hh = (float)h - ypad;
//
//        // Figure out how big we can make the pie.
//        float diameter = Math.min(ww, hh);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // Try for a width based on our minimum
//        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
//        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);
//
//        // Whatever the width ends up being, ask for a height that would let the pie
//        // get as big as it can
//        int minh = MeasureSpec.getSize(w) - (int)textWidth + getPaddingBottom() + getPaddingTop();
//        int h = resolveSizeAndState(MeasureSpec.getSize(w) - (int)textWidth, heightMeasureSpec, 0);
//        UIUtil.log("onMeasure:",widthMeasureSpec+"",heightMeasureSpec+"");
//        setMeasuredDimension(500, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        UIUtil.log("measure:", getMeasuredWidth() + "", getMeasuredHeight() + "");
        UIUtil.log("canvas size:", canvas.getWidth() + "", canvas.getHeight() + "");
        RectF shadowBounds = new RectF(0, 0, 10, 10);
        // Draw the shadow
//        canvas.drawOval(
//                shadowBounds,
//                shadowPaint
//        );

        // Draw the label text
        canvas.drawText("ABC PIE CHART", 20, 200, textPaint);

        // Draw the pie slices
//        for (int i = 0; i < data.size(); ++i) {
//            Item it = data.get(i);
//            piePaint.setShader(it.shader);
//            canvas.drawArc(bounds,
//                    360 - it.endAngle,
//                    it.endAngle - it.startAngle,
//                    true, piePaint);
//        }

        // Draw the pointer
//        canvas.drawLine(textX, pointerY, pointerX, pointerY, textPaint);
//        canvas.drawCircle(pointerX, pointerY, pointerSize, mTextPaint);
    }
}
