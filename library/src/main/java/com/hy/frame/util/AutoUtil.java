package com.hy.frame.util;

import android.content.Context;
import android.content.res.TypedArray;

import com.hy.iframe.auto.R;

/**
 * title AutoUtil
 * author heyan
 * time 19-7-10 下午4:34
 * desc  0.5F用于四舍五入 效率高
 */
public final class AutoUtil {
    /**
     * 获取默认 designDesignScreenWidth
     *
     * @param cxt 上下文
     * @return int
     */
    public static int getDesignScreenWidth(Context cxt) {
        TypedArray a = cxt.getTheme().obtainStyledAttributes(new int[]{R.attr.designScreenWidth});
        int width = a.getDimensionPixelSize(0, 0);
        a.recycle();
        return width;
    }
    /**
     * 计算设计比例
     *
     * @param cxt               上下文
     * @param designScreenWidth 设计尺寸
     * @return 比例 float
     */
    public static float calDesignScale(Context cxt, int designScreenWidth) {
        int screenWidth = cxt.getResources().getDisplayMetrics().widthPixels;
        if (designScreenWidth == 0 || designScreenWidth == screenWidth) return 1F;
        return ((float) screenWidth) / designScreenWidth;
    }

    /**
     * 计算设计比例
     *
     * @param width 设计尺寸
     * @param scale 需要缩放比例
     * @return 真实尺寸
     */
    public static int calDesignWidth(int width, float scale) {
        if (scale == 0 || scale == 1) return width;
        return (int) (width * scale + 0.5F);
    }
}
