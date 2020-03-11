package com.hy.frame.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hy.frame.util.AutoUtil;
import com.hy.iframe.auto.R;

/**
 * title 无
 * author heyan
 * time 19-7-10 下午4:24
 * desc 无
 */
public class AutoLinearLayout extends LinearLayout implements IAutoDesign {
    /**
     * 设计尺寸
     */
    private int designScreenWidth;
    /**
     * 设计比例缩放比例
     */
    private float designScale = 1F;

    public AutoLinearLayout(Context context) {
        this(context, null);
    }

    public AutoLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public AutoLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs, defStyleAttr);
    }

    @SuppressLint("CustomViewStyleable")
    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoLinearLayout, defStyleAttr, 0);
        this.designScreenWidth = a.getDimensionPixelSize(R.styleable.AutoLinearLayout_designScreenWidth, AutoUtil.getDesignScreenWidth(context));
        this.designScale = calDesignScale();
        int padding = a.getDimensionPixelSize(R.styleable.AutoLinearLayout_designPadding, 0);
        int paddingLeft = a.getDimensionPixelSize(R.styleable.AutoLinearLayout_designPaddingLeft, padding);
        int paddingTop = a.getDimensionPixelSize(R.styleable.AutoLinearLayout_designPaddingTop, padding);
        int paddingRight = a.getDimensionPixelSize(R.styleable.AutoLinearLayout_designPaddingRight, padding);
        int paddingBottom = a.getDimensionPixelSize(R.styleable.AutoLinearLayout_designPaddingBottom, padding);
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
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        LayoutParams newParams;
        if (params == null)
            newParams = generateDefaultLayoutParams();
        else if (params instanceof LayoutParams)
            newParams = (LayoutParams) params;
        else
            newParams = new LayoutParams(params);
        if (newParams.width > 0) {
            newParams.width = calDesignWidth(newParams.width);
        }
        if (newParams.height > 0) {
            newParams.height = calDesignWidth(newParams.height);
        }
        if (newParams.leftMargin > 0) {
            newParams.leftMargin = calDesignWidth(newParams.leftMargin);
        }
        if (newParams.topMargin > 0) {
            newParams.topMargin = calDesignWidth(newParams.topMargin);
        }
        if (newParams.rightMargin > 0) {
            newParams.rightMargin = calDesignWidth(newParams.rightMargin);
        }
        if (newParams.bottomMargin > 0) {
            newParams.bottomMargin = calDesignWidth(newParams.bottomMargin);
        }
        super.addView(child, index, newParams);
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
