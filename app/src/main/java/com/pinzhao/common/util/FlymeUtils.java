package com.pinzhao.common.util;

import android.os.Build;

import java.lang.reflect.Method;

/**
 * Created by ${sheldon} on 2016/12/30.
 */
public class FlymeUtils {

    /**
     * 根据是否有smartbar来判断是否为魅族系统
     *
     * @return
     */
    public static boolean isFlyme() {
        try {
            // Invoke Build.hasSmartBar()
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        } catch (final Exception e) {
            return false;
        }
    }
}
