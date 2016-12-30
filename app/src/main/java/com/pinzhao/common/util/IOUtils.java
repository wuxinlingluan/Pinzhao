package com.pinzhao.common.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by ${sheldon} on 2016/12/30.
 */
public class IOUtils  {
    /**
     * 关闭流
     *
     * @param io
     * @return
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                LogUtils.e(e);
            }
        }
        return true;
    }
}