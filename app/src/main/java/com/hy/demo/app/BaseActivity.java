package com.hy.demo.app;

import android.content.pm.ActivityInfo;
import android.view.View;

/**
 * title 无
 * author heyan
 * time 19-7-11 下午2:30
 * desc 无
 */
public abstract class BaseActivity extends com.hy.frame.ui.simple.BaseActivity {
    @Override
    public boolean isSingleLayout() {
        return false;
    }

    @Override
    public boolean isTranslucentStatus() {
        return true;
    }

    @Override
    public int getScreenOrientation() {
        return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    protected void initHeader(int drawLeft, int titleId, int strRightId) {
        getTemplateController().setHeaderLeft(drawLeft);
        getTemplateController().setTitle(titleId);
        getTemplateController().setHeaderRightTxt(getString(strRightId));
    }

    protected void initHeader(int drawLeft, int titleId) {
        getTemplateController().setHeaderLeft(drawLeft);
        getTemplateController().setTitle(titleId);
    }

    @Override
    public void onViewClick(View v) {

    }
}
