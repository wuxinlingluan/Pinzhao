package com.pinzhao.common.util;

import com.pinzhao.common.engine.BuildProperties;

import java.io.IOException;

/**
 * 
 * @author: Biao
 * @创建时间: 2015-12-30 下午5:39:50
 * @描述信息: 判断是否为小米系统的工具类封装
 * @svn提交者: $Author$
 * @提交时间: $Date$
 * @当前版本: $Rev$
 */
public final class MIUIUtils {

	private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
	private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
	private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

	/**
	 * 是否为米轴系统
	 * 
	 * @return
	 */
	public static boolean isMIUI() {
		try {
			final BuildProperties prop = BuildProperties.newInstance();
			return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
					|| prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
					|| prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
		} catch (final IOException e) {
			return false;
		}
	}
}
