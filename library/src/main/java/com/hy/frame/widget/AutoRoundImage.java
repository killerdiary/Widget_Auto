package com.hy.frame.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.hy.frame.util.AutoUtil;
import com.hy.iframe.auto.R;


public class AutoRoundImage extends ImageView implements IAutoDesign {
    /**
     * 设计尺寸
     */
    private int designScreenWidth;
    /**
     * 设计比例缩放比例
     */
    private float designScale = 1F;

    private final RectF roundRect = new RectF();
    private final RectF borderRect = new RectF();
    private final Paint maskPaint = new Paint();
    private final Paint zonePaint = new Paint();
    private final Paint borderPaint = new Paint();
    private final Paint borderMaskPaint = new Paint();
    /**
     * 圆角的大小
     */
    private float radius = 15;
    private boolean circle;
    private int border;
    private int borderColor;

    public AutoRoundImage(Context context) {
        this(context, null);
    }

    public AutoRoundImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoRoundImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
    }

    @SuppressLint("CustomViewStyleable")
    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoRoundImage, defStyleAttr, 0);
        this.designScreenWidth = a.getDimensionPixelSize(R.styleable.AutoTextView_designScreenWidth, AutoUtil.getDesignScreenWidth(context));
        this.designScale = calDesignScale();
        this.radius = calDesignWidth(a.getDimensionPixelSize(R.styleable.AutoRoundImage_designRadius, 0));
        this.circle = a.getBoolean(R.styleable.AutoRoundImage_designCircle, false);
        this.border = calDesignWidth(a.getDimensionPixelSize(R.styleable.AutoRoundImage_designBorder, 0));
        this.borderColor = a.getColor(R.styleable.AutoRoundImage_designBorderColor, Color.BLACK);
        a.recycle();
        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        zonePaint.setAntiAlias(true);
        zonePaint.setColor(Color.WHITE);
        borderPaint.setAntiAlias(true);
        borderPaint.setColor(Color.WHITE);
        borderMaskPaint.setAntiAlias(true);
        borderMaskPaint.setColor(borderColor);
        borderMaskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
        if (circle) {
            canvas.drawCircle(roundRect.width() / 2, roundRect.height() / 2, radius- border, zonePaint);
        } else {
            canvas.drawRoundRect(borderRect, radius, radius, zonePaint);
        }
        canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        canvas.restore();
        //画边框
        if (border > 0) {
            canvas.saveLayer(roundRect, borderPaint, Canvas.ALL_SAVE_FLAG);
            if (circle) {
                canvas.drawCircle(roundRect.width() / 2, roundRect.height() / 2, radius - border, borderPaint);
            } else {
                canvas.drawRoundRect(borderRect, radius, radius, borderPaint);
            }
            canvas.saveLayer(roundRect, borderMaskPaint, Canvas.ALL_SAVE_FLAG);
            if (circle) {
                canvas.drawCircle(roundRect.width() / 2, roundRect.height() / 2, radius, borderMaskPaint);
            } else {
                canvas.drawRoundRect(roundRect, radius, radius, borderMaskPaint);
            }
            canvas.restore();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int w = getWidth();
        int h = getHeight();
        roundRect.set(0, 0, w, h);
        borderRect.set(border, border, w - border, h - border);
        if (circle) {
            if (w > h) {
                radius = h * 1f / 2;
            } else {
                radius = w * 1f / 2;
            }
        }
    }

    /**
     * 设置圆角大小
     *
     * @param radius
     */
    public void setRadius(float radius) {
        this.radius = radius;
        invalidate();
    }

    @Override
    public int getDesignScreenWidth() {
        return this.designScreenWidth;
    }

    @Override
    public float getDesignScale() {
        return this.designScale;
    }

    @Override
    public float calDesignScale() {
        return AutoUtil.calDesignScale(getContext(), getDesignScreenWidth());
    }

    @Override
    public int calDesignWidth(int width) {
        return AutoUtil.calDesignWidth(width, getDesignScale());
    }
}
