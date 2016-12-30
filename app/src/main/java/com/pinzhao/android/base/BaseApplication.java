package com.pinzhao.android.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.File;

/**
 * Created by ${sheldon} on 2016/12/30.
 */
public class BaseApplication  extends Application {
    private static Context mContext;
    private static Thread mMainThread;
    private static long mMainThreadId;
    private static Looper mMainLooper;
    private static Handler mMainHandler;
    private static BaseApplication instance;

    private File headFile;

    public File getHeadFile() {
        return headFile;
    }

    public void setHeadFile(File headFile) {
        this.headFile = headFile;
    }

    /**
     * 懒汉式获取实例
     *
     * @return
     */
    public static BaseApplication getInstance() {
        if (instance == null) {
            synchronized (BaseApplication.class) {
                if (instance == null) {
                    instance = new BaseApplication();
                }
            }
        }
        return instance;
    }

    public static Context getContext() {
        return mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static Handler getMainHandler() {
        return mMainHandler;
    }

    // 应用程序的入口
    @Override
    public void onCreate() {
        super.onCreate();
        initData();

    }

    /**
     * 初始化相关需要的数据
     */
    private void initData() {
        // 上下文
        mContext = getApplicationContext();
        // 主线程
        mMainThread = Thread.currentThread();
        // 主线程id
        mMainThreadId = android.os.Process.myTid();
        // 获取循环器
        mMainLooper = getMainLooper();
        // 创建主线程的handler
        mMainHandler = new Handler();
    }

}

