package com.pinzhao.common.util;

import android.content.res.AssetManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ${sheldon} on 2016/12/31.
 */
public class CopyFileFromAssetsToPhoneUtils {

    /**
     * 拷贝数据库
     *
     * @param fileDir
     * @param name
     */
    public static void copyFile(final String fileDir, final String name) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    // 文件不存才去拷贝
                    if (!isFileExist(fileDir, name)) {
                        copyFile(name);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 拷贝文件
     *
     * @param name
     * @throws IOException
     * @throws FileNotFoundException
     */
    private static void copyFile(String name) throws IOException,
            FileNotFoundException {
        // 获取资产管理者
        AssetManager assets = UIUtils.getContext().getAssets();
        // 获取文件
        InputStream open = assets.open(name);
        // 写入数据
        FileUtils.writeFile(open, FileUtils.getExternalStoragePath() + "files/"
                + name, true);
    }

    /**
     * 判断指定文件是否已经存在
     *
     * @param fileDir
     * @param fileName
     * @return
     */
    private static boolean isFileExist(String fileDir, String fileName) {
        boolean isExist = false;
        // 创建文件
        File file = new File(fileDir, fileName);
        if (file.exists()) {
            isExist = true;
        }
        return isExist;
    }
}
