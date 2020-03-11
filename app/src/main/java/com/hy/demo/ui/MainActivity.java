package com.hy.demo.ui;

import android.view.View;

import com.hy.demo.app.BaseActivity;
import com.hy.demo.R;

/**
 * title 无
 * author heyan
 * time 19-8-15 上午9:59
 * desc 无
 */
public class MainActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.v_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        initHeader(android.R.drawable.ic_menu_revert, R.string.appName);
    }

    @Override
    public void onViewClick(View v) {

    }

    @Override
    public void onBackPressed() {
        getCurApp().exit();
    }
}
