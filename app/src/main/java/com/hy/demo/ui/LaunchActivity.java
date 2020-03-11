package com.hy.demo.ui;

import android.view.View;
import android.widget.TextView;

import com.hy.demo.app.BaseActivity;
import com.hy.demo.R;
import com.hy.frame.util.TimerUtil;
import com.hy.iframe.auto.BuildConfig;

/**
 * title 无
 * author heyan
 * time 19-8-15 上午10:00
 * desc 无
 */
public class LaunchActivity extends BaseActivity implements TimerUtil.ICallback {
    private TextView txtMsg;
    private TimerUtil timer;

    @Override
    public int getLayoutId() {
        return R.layout.v_launch;
    }

    @Override
    public void initView() {
        txtMsg = findViewById(R.id.launch_txtMsg);
    }

    @Override
    public void initData() {
        txtMsg.setText(getString(R.string.launch_str, BuildConfig.VERSION_NAME));
        timer = new TimerUtil(getCurContext());
        timer.delayed(1500L, this);
    }


    @Override
    public void doNext() {
        timer.cancel();
        timer = null;
        txtMsg = null;
        startAct(MainActivity.class);
        finish();
    }
}
