package com.pinzhao.android.activity;

import android.app.Activity;
import android.os.Bundle;

import com.pinzhao.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initData(); //初始化数据
        initEvent();
    }

    private void initEvent() {

    }

    private void initData() {
    }

}
