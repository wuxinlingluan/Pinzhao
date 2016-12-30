package com.pinzhao.common.engine;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * @author: Biao
 * @创建时间: 2015-12-30 下午5:38:23
 * @描述信息: 构造属性用于识别系统(哪家手机产商的系统)
 * @svn提交者: $Author$
 * @提交时间: $Date$
 * @当前版本: $Rev$
 */
public class BuildProperties {

	private final Properties properties;

	private BuildProperties() throws IOException {
		properties = new Properties();
		properties.load(new FileInputStream(new File(Environment
				.getRootDirectory(), "build.prop")));
	}

	public boolean containsKey(final Object key) {
		return properties.containsKey(key);
	}

	public boolean containsValue(final Object value) {
		return properties.containsValue(value);
	}

	public Set<Entry<Object, Object>> entrySet() {
		return properties.entrySet();
	}

	public String getProperty(final String name) {
		return properties.getProperty(name);
	}

	public String getProperty(final String name, final String defaultValue) {
		return properties.getProperty(name, defaultValue);
	}

	public boolean isEmpty() {
		return properties.isEmpty();
	}

	public Enumeration<Object> keys() {
		return properties.keys();
	}

	public Set<Object> keySet() {
		return properties.keySet();
	}

	public int size() {
		return properties.size();
	}

	public Collection<Object> values() {
		return properties.values();
	}

	public static BuildProperties newInstance() throws IOException {
		return new BuildProperties();
	}
}
