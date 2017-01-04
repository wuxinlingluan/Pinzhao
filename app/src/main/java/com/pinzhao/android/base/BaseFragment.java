package com.pinzhao.android.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ${sheldon} on 2017/1/4.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = initView();
        initData();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 子类必须完成此方法 初始化控件
     *
     * @return
     */
    public abstract View initView();

    /**
     * 子类覆盖此方法完成初始化数据
     */
    public void initData() {
    }

    /**
     * 子类覆盖此方法完成初始化事件
     */
    public void initEvent() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initEvent();
        super.onActivityCreated(savedInstanceState);

    }
}

