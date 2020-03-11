package com.hy.frame.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.hy.frame.util.AutoUtil;
import com.hy.iframe.auto.R;

/**
 * title 可控制图标大小TextView，另外只支持drawableLeft居中
 * author heyan
 * time 19-7-10 下午4:24
 * desc 无
 */
public class AutoTextView extends TextView implements IAutoDesign {
    /**
     * 设计尺寸
     */
    private int designScreenWidth;
    /**
     * 设计比例缩放比例
     */
    private float designScale = 1F;
    private int drawLeftWidth;
    private int drawLeftHeight;
    private int drawTopWidth;
    private int drawTopHeight;
    private int drawRightWidth;
    private int drawRightHeight;
    private int drawBottomWidth;
    private int drawBottomHeight;
    private int drawPadding;
    private boolean drawCenter = false;

    public AutoTextView(Context context) {
        this(context, null);
    }

    public AutoTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
    }

    @SuppressLint("CustomViewStyleable")
    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoTextView, defStyleAttr, 0);
        this.designScreenWidth = a.getDimensionPixelSize(R.styleable.AutoTextView_designScreenWidth, AutoUtil.getDesignScreenWidth(context));
        this.designScale = calDesignScale();
        int padding = a.getDimensionPixelSize(R.styleable.AutoTextView_designPadding, 0);
        int paddingLeft = a.getDimensionPixelSize(R.styleable.AutoTextView_designPaddingLeft, padding);
        int paddingTop = a.getDimensionPixelSize(R.styleable.AutoTextView_designPaddingTop, padding);
        int paddingRight = a.getDimensionPixelSize(R.styleable.AutoTextView_designPaddingRight, padding);
        int paddingBottom = a.getDimensionPixelSize(R.styleable.AutoTextView_designPaddingBottom, padding);
        int textSize = a.getDimensionPixelSize(R.styleable.AutoTextView_designTextSize, 0);
        this.drawLeftWidth = calDesignWidth(a.getDimensionPixelSize(R.styleable.AutoTextView_designDrawLeftWidth, 0));
        this.drawLeftHeight = calDesignWidth(a.getDimensionPixelSize(R.styleable.AutoTextView_designDrawLeftHeight, this.drawLeftWidth));
        this.drawTopWidth = calDesignWidth(a.getDimensionPixelSize(R.styleable.AutoTextView_designDrawTopWidth, 0));
        this.drawTopHeight = calDesignWidth(a.getDimensionPixelSize(R.styleable.AutoTextView_designDrawTopHeight, this.drawTopWidth));
        this.drawRightWidth = calDesignWidth(a.getDimensionPixelSize(R.styleable.AutoTextView_designDrawRightWidth, 0));
        this.drawRightHeight = calDesignWidth(a.getDimensionPixelSize(R.styleable.AutoTextView_designDrawRightHeight, this.drawRightWidth));
        this.drawBottomWidth = calDesignWidth(a.getDimensionPixelSize(R.styleable.AutoTextView_designDrawBottomWidth, 0));
        this.drawBottomHeight = calDesignWidth(a.getDimensionPixelSize(R.styleable.AutoTextView_designDrawBottomHeight, this.drawBottomWidth));
        this.drawPadding = calDesignWidth(a.getDimensionPixelSize(R.styleable.AutoTextView_designDrawPadding, 0));
        this.drawCenter = a.getBoolean(R.styleable.AutoTextView_designDrawCenter, false);
        a.recycle();
        if (paddingLeft > 0) paddingLeft = calDesignWidth(paddingLeft);
        else paddingLeft = getPaddingLeft();
        if (paddingTop > 0) paddingTop = calDesignWidth(paddingTop);
        else paddingTop = getPaddingTop();
        if (paddingRight > 0) paddingRight = calDesignWidth(paddingRight);
        else paddingRight = getPaddingRight();
        if (paddingBottom > 0) paddingBottom = calDesignWidth(paddingBottom);
        else paddingBottom = getPaddingBottom();
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        if (textSize > 0)
            setTextSize(TypedValue.COMPLEX_UNIT_PX, calDesignWidth(textSize));

        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if (drawLeftWidth > 0 && left != null)
            left.setBounds(0, 0, drawLeftWidth, drawLeftHeight);
        if (drawTopWidth > 0 && top != null)
            top.setBounds(0, 0, drawTopWidth, drawTopHeight);
        if (drawRightWidth > 0 && right != null)
            right.setBounds(0, 0, drawRightWidth, drawRightHeight);
        if (drawBottomWidth > 0 && bottom != null)
            bottom.setBounds(0, 0, drawBottomWidth, drawBottomHeight);
        super.setCompoundDrawables(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawableLeft = drawables[0];
        if (drawCenter && drawableLeft != null) {
            float textWidth = getPaint().measureText(getText().toString());
            int drawablePadding = getCompoundDrawablePadding();
            if (this.drawPadding > 0) drawablePadding = drawPadding;
            int drawableWidth = drawLeftWidth;
            float bodyWidth = textWidth + drawableWidth + drawablePadding;
            canvas.translate((getMeasuredWidth() - bodyWidth) / 2, 0f);
        }
        super.onDraw(canvas);
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
