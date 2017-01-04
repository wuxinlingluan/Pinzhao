package com.pinzhao.android.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.pinzhao.R;
import com.pinzhao.android.base.BaseActivity;
import com.pinzhao.android.fragment.HomeFragment;
import com.pinzhao.android.fragment.MeFragment;
import com.pinzhao.android.fragment.MessageFragment;
import com.pinzhao.android.fragment.OrderFragment;
import com.pinzhao.android.view.ButtomMenuWithBadge;
import com.pinzhao.common.util.PinzhaoConstants;
import com.pinzhao.common.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_fragment_content)
    FrameLayout flFragmentContent;
    @BindView(R.id.bmwb_home)
    ButtomMenuWithBadge bmwbHome;
    @BindView(R.id.bmwb_message)
    ButtomMenuWithBadge bmwbMessage;
    @BindView(R.id.bmwb_order)
    ButtomMenuWithBadge bmwbOrder;
    @BindView(R.id.bmwb_me)
    ButtomMenuWithBadge bmwbMe;
    @BindView(R.id.root)
    LinearLayout root;
    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private List<ButtomMenuWithBadge> mTabButtonList = new ArrayList<ButtomMenuWithBadge>();
    // 当前碎片标记索引
    private int currFragmentTagIndex = -1;
    private HomeFragment homeFragment; //首页fragment
    private MessageFragment messageFragment; //消息fragment
    private OrderFragment orderFragment; //订单fragment
    private MeFragment meFragment;//我的fragment
    /**
     * 底部菜单正常状态下的图片资源数组
     */
    private int[] imageNormalArray = {R.drawable.menu_home_normal,
            R.drawable.menu_message_normal, R.drawable.menu_order_normal,
            R.drawable.menu_me_normal};
    /**
     * 底部菜单选中时状态下的图片资源数组
     */
    private int[] imageSelectArray = {R.drawable.menu_home_select,
            R.drawable.menu_message_select, R.drawable.menu_order_select,
            R.drawable.menu_me_select};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View initView() {
        setContentView(R.layout.activity_main);
        View mRoot = findViewById(R.id.ll_bottom);
        fragmentManager = getSupportFragmentManager();
        return mRoot;
    }

    /**
     * 初始化数据操作
     */
    @Override
    protected void initData() {
        mTabButtonList.add(bmwbHome);
        mTabButtonList.add(bmwbMessage);
        mTabButtonList.add(bmwbOrder);
        mTabButtonList.add(bmwbMe);
        changeTextColorAndSelectImage(PinzhaoConstants.HOME_TAG);
    }

    /**
     * 选择相应的碎片界面(通过显示与隐藏的方式,解决Fragment缓存问题)
     *
     * @param index
     */
    private void switchFragment(int index) {
        currFragmentTagIndex = index;
        // 开启事务
        fragmentTransaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideAllFragments(fragmentTransaction);
        // 根据索引进行切换
        switch (index) {

            case PinzhaoConstants.HOME_TAG:
                if (homeFragment == null) {
                    // 如果findBuyerReleaseFragment为空，则创建一个并添加到界面上
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.fl_fragment_content,
                            homeFragment);

                } else {
                    // 如果findBuyerReleaseFragment不为空，则直接将它显示出来
                    fragmentTransaction.show(homeFragment);
                }

                break;

            // 消息
            case PinzhaoConstants.MESSAGE_TAG:

                if (messageFragment == null) {
                    // 如果messageFragment为空，则创建一个并添加到界面上
                    messageFragment = new MessageFragment();
                    fragmentTransaction.add(R.id.fl_fragment_content,
                            messageFragment);
                } else {
                    // 如果messageFragment不为空，则直接将它显示出来
                    fragmentTransaction.show(messageFragment);
                }
                break;

            // 订单
            case PinzhaoConstants.ORDER_TAG:
                if (orderFragment == null) {
                    // 如果orderFragment为空，则创建一个并添加到界面上
                    orderFragment = new OrderFragment();
                    fragmentTransaction
                            .add(R.id.fl_fragment_content, orderFragment);
                } else {
                    // 如果orderFragment不为空，则直接将它显示出来
                    fragmentTransaction.show(orderFragment);
                }
                break;

            // 我
            case PinzhaoConstants.ME_TAG:
                if (meFragment == null) {
                    // 如果meFragment为空，则创建一个并添加到界面上
                    meFragment = new MeFragment();
                    fragmentTransaction.add(R.id.fl_fragment_content, meFragment);
                } else {
                    // 如果meFragment不为空，则直接将它显示出来
                    fragmentTransaction.show(meFragment);
                }
                break;

        }
        // 提交事务
        fragmentTransaction.commit();
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideAllFragments(FragmentTransaction transaction) {
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);
        }
        if (orderFragment != null) {
            transaction.hide(orderFragment);
        }

    }


    /**
     * 改变文本颜色与图片选中的状态
     *
     * @param number
     */
    @SuppressWarnings("deprecation")
    private void changeTextColorAndSelectImage(int number) {
        // 遍历集合
        for (int i = 0; i < mTabButtonList.size(); i++) {

            ButtomMenuWithBadge bmwb = mTabButtonList.get(i);
            // 判断是否为选中的位置
            if (number == i) {
                bmwb.setTextColor(UIUtils.getColor(R.color.main_color));
                bmwb.setTextTopIamge(UIUtils.getResources().getDrawable(
                        imageSelectArray[i]));
            } else {
                bmwb.setTextColor(UIUtils.getColor(R.color.tv_prompt_color));
                bmwb.setTextTopIamge(UIUtils.getResources().getDrawable(
                        imageNormalArray[i]));
            }
            // 判断是否为首页
            if (number == PinzhaoConstants.HOME_TAG) {
                switchFragment(PinzhaoConstants.HOME_TAG);
            } else {
                // 更改界面
                switchFragment(number);
            }
        }
    }

    /**
     * 改变选中菜单样式
     *
     * @param number
     */
    @SuppressWarnings("deprecation")
    private void changeSelectMenu(int number) {
        // 遍历集合
        for (int i = 0; i < mTabButtonList.size(); i++) {
            ButtomMenuWithBadge bmwb = mTabButtonList.get(i);
            // 判断是否为选中的位置
            if (number == i) {
                bmwb.setTextColor(UIUtils.getColor(R.color.main_color));
                bmwb.setTextTopIamge(UIUtils.getResources().getDrawable(
                        imageSelectArray[i]));
            } else {
                bmwb.setTextColor(UIUtils.getColor(R.color.tv_prompt_color));
                bmwb.setTextTopIamge(UIUtils.getResources().getDrawable(
                        imageNormalArray[i]));
            }
        }
    }

    @OnClick({R.id.bmwb_home, R.id.bmwb_message, R.id.bmwb_order, R.id.bmwb_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bmwb_home://点击首页
                changeTextColorAndSelectImage(PinzhaoConstants.HOME_TAG);
                break;
            case R.id.bmwb_message://点击消息
                changeTextColorAndSelectImage(PinzhaoConstants.MESSAGE_TAG);
                break;
            case R.id.bmwb_order://点击订单
                changeTextColorAndSelectImage(PinzhaoConstants.ORDER_TAG);
                break;
            case R.id.bmwb_me://点击我的
                changeTextColorAndSelectImage(PinzhaoConstants.ME_TAG);
                break;
        }
    }
}
