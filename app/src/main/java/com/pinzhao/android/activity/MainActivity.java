package com.pinzhao.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pinzhao.R;
import com.pinzhao.android.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected View initView() {
        setContentView(R.layout.activity_main);
        TextView tv_test= (TextView) findViewById(R.id.tv_test);
        return tv_test;
    }

    @Override
    protected void initData() {

    }
}
