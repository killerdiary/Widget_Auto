package com.hy.demo.app;

import com.hy.frame.common.BaseApplication;

/**
 * title 无
 * author heyan
 * time 19-7-11 下午2:26
 * desc 无
 */
public class MyApplication extends BaseApplication {

    public static MyApplication getInstance() {
        return (MyApplication) BaseApplication.getInstance();
    }


    @Override
    public boolean isLoggable() {
        return true;
    }

}
