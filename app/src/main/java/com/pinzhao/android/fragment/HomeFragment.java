package com.pinzhao.android.fragment;

import android.view.View;

import com.pinzhao.R;
import com.pinzhao.android.base.BaseFragment;
import com.pinzhao.common.util.UIUtils;

/**
 * Created by ${sheldon} on 2017/1/4.
 */
public class HomeFragment extends BaseFragment {
    @Override
    public View initView() {
        View rootView = View.inflate(UIUtils.getContext(),
                R.layout.fragment_home, null);
        return rootView;
    }
}
