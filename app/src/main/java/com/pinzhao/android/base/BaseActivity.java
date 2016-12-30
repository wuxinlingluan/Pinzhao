package com.pinzhao.android.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.meizu.flyme.reflect.StatusBarProxy;
import com.pinzhao.android.activity.MainActivity;
import com.pinzhao.R;
import com.pinzhao.common.util.CommonUtils;
import com.pinzhao.common.util.FlymeUtils;
import com.pinzhao.common.util.LogUtils;
import com.pinzhao.common.util.MIUIUtils;
import com.pinzhao.common.util.PinzhaoConstants;
import com.pinzhao.common.util.SharedPreferencesUtils;
import com.pinzhao.common.util.UIUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by ${sheldon} on 2016/12/30.
 */
public abstract class BaseActivity extends FragmentActivity {
    // 对所有的activity进行管理
    protected static List<Activity> mActivities = new LinkedList<Activity>();
    private static Activity mCurrentActivity;
    private long mPreTime;
    protected View mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        synchronized (mActivities) {
            mActivities.add(this);
        }
        // 锁定竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 测量当前手机的像素密度值
        metricsPhoneDensity();
        // 初始化view
        mRoot = initView();
        // 初始化系统状态栏
//		initSystemStateBar();
//		changeSystemStateBarBlackColorDependOs();
        // 初始化数据
        initData();
        initEvent();

    }

    /**
     * 根据不同产商的Os来更改状态栏字体颜色为黑色
     */
    @SuppressLint("NewApi")
    private void changeSystemStateBarBlackColorDependOs() {
        // 系统大于4.4版本的小于5.0版本
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                && (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)) {
            // 判断是否为小米系统
            if (MIUIUtils.isMIUI()) {
                LogUtils.d("小米系统");
                // 打开黑色模式
                setStatusBarDarkMode(true, this);
            }
            // 判断是否魅族系统
            if (FlymeUtils.isFlyme()) {
                LogUtils.d("魅族系统");
                // 设置状态栏图标文字为黑色
                StatusBarProxy.setStatusBarDarkIcon(getWindow(), true);
            }
        }
        // 系统大于5.0版本的小于5.1版本
        else if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                && (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1)) {
            LogUtils.d("大于5.0版本sdk");
            Window window = getWindow();
            String manufacturer = Build.MANUFACTURER;
            if ("HUAWEI".equals(manufacturer)) {
                LogUtils.d("华为手机大于5.0版本sdk");
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }

            window.setStatusBarColor(getResources().getColor(
                    R.color.custom_title_bg));
        } // 大于于5.1版本
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            String manufacturer = Build.MANUFACTURER;
            Window window = getWindow();
            // 判断是否为努比亚
            if ("nubia".equals(manufacturer)) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(
                        R.color.custom_title_bg));
            } else {

                window.setStatusBarColor(Color.BLACK);
            }

        }
    }

    /**
     * 根据不同产商的Os来更改状态栏字体颜色为白色
     */
    @SuppressLint("NewApi")
    public void changeSystemStateBarWhiteColorDependOs() {
        // 系统大于4.4版本的小于5.0版本
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                && (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)) {
            // 判断是否为小米系统
            if (MIUIUtils.isMIUI()) {
                LogUtils.d("小米系统");
                // 关闭黑色模式
                setStatusBarDarkMode(false, this);
            }
            // 判断是否魅族系统
            if (FlymeUtils.isFlyme()) {
                LogUtils.d("魅族系统");
                // 设置状态栏图标文字为白色
                StatusBarProxy.setStatusBarDarkIcon(getWindow(), false);
            }
        }
        // 系统大于5.0版本小于5.1版本
        else if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                && (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1)) {
            LogUtils.d("大于5.0版本sdk");
            Window window = getWindow();

            String manufacturer = Build.MANUFACTURER;
            if ("HUAWEI".equals(manufacturer)) {
                LogUtils.d("华为手机大于5.0版本sdk");
            } else {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }

            window.setStatusBarColor(getResources().getColor(
                    R.color.telephone_call_systembar_bg));
        } // 大于5.1版本
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            String manufacturer = Build.MANUFACTURER;
            Window window = getWindow();
            // 判断是否为努比亚
            if ("nubia".equals(manufacturer)) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(
                        R.color.telephone_call_systembar_bg));
            } else {
                window.setStatusBarColor(Color.WHITE);
            }

        }
    }

    /**
     * 测量当前手机的像素密度值
     */
    private void metricsPhoneDensity() {
        float density = SharedPreferencesUtils.getFloat(this,
                PinzhaoConstants.DENSITY);
        if (density == 0) {
            DisplayMetrics metric = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metric);
            // 获取屏幕的density值,并将保存到sp中
            SharedPreferencesUtils.putFloat(this, PinzhaoConstants.DENSITY,
                    metric.density);
            LogUtils.d("当前设备---density=" + metric.density);
        }

    }

    protected void closeSystemStateBar() {

    }

    /**
     * 小米系统启动状态栏为黑色字体
     *
     * @param darkmode
     * @param activity
     */
    public void setStatusBarDarkMode(boolean darkmode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class
                    .forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams
                    .getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class,
                    int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag
                    : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 设置悬浮状态
     *
     * @param on
     */
    @SuppressLint("InlinedApi")
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 初始化事件
     */
    protected void initEvent() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (mActivities) {
            mActivities.remove(this);
        }

    }

    @Override
    protected void onResume() {
        CommonUtils.setTextSizeDefault();
        super.onResume();
        mCurrentActivity = this;

    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentActivity = null;

    }

    /**
     * 退出应用
     */
    public static void exitApp() {
        // 遍历所有的activity，finish
        ListIterator<Activity> iterator = mActivities.listIterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            next.finish();
        }
    }

    /**
     * 获取当前界面
     *
     * @return
     */
    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    /**
     * 返回键的处理
     */
    @Override
    public void onBackPressed() {
        if (this instanceof MainActivity) {
            if (System.currentTimeMillis() - mPreTime > 2000) {
                Toast.makeText(UIUtils.getContext(), "再按一次退出应用",
                        Toast.LENGTH_SHORT).show();
                mPreTime = System.currentTimeMillis();
                return;
            } else {
                exitApp();
                finish();
                super.onBackPressed();
            }
        } else {

            super.onBackPressed();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_left_in,
                R.anim.activity_right_out);
    }

    // --------------让子类去实现的方法-------------------
    protected abstract View initView();

    protected abstract void initData();
}
