package com.banking.xc.config;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.banking.xc.utils.Log;

public class Configuration {

	
	private static Properties properties;
	private static Map<String, String> localProperties;

	public static final String ATTEMPTS = "attempts";// 尝试次数
	public static final String ATTEMPTS_TIME = "attemptsTime";// 尝试的间隔时间
	public static final String CONNECT_TIMEOUT = "connectTimeout";// 连接超时
	public static final String CONNECT_TIMEOUT_FOR_WIFI = "connectTimeoutForWIFI";// 连接超时
	public static final String READ_TIMEOUT = "readTimeout";// 读取超时
	public static final String READ_TIMEOUT_FOR_WIFI = "readTimeoutForWIFI";// 读取超时

	
	public static final String INIT_FIRST_POOL_SIZE = "initFirstPoolSize";// 第一线程池：最少非网络线程池
	public static final String MAX_FIRST_POOL_SIZE = "maxFirstPoolSize";// 第一线程池：最大非网络线程池
	public static final String INIT_SECOND_POOL_SIZE = "initSecondPoolSize";// 第二线程池：最少网络线程池数量(json)
	public static final String MAX_SECOND_POOL_SIZE = "maxSecondPoolSize";// 第二线程池：最大网络线程池数量(json)
	public static final String INIT_THIRD_POOL_SIZE = "initThirdPoolSize";// 第三线程池：最少网络线程池数量(image、file)
	public static final String MAX_THIRD_POOL_SIZE = "maxThirdPoolSize";// 第三线程池：最大网络线程池数量(image、file)


	public static final String PRINT_LOG = "printLog";// 日志开关
	public static final String DEBUG_LOG = "debugLog";// 日志开关
	public static final String VIEW_LOG = "viewLog";// 日志开关
	public static final String ERROR_LOG = "errorLog";// 日志开关
	public static final String INFO_LOG = "infoLog";// 日志开关
	public static final String WARN_LOG = "warnLog";// 日志开关


	static {
		localProperties = new HashMap<String, String>();


		localProperties.put(INIT_FIRST_POOL_SIZE, "" + 5);// 第一线程池：最少非网络线程池
		localProperties.put(MAX_FIRST_POOL_SIZE, "" + 5);// 第一线程池：最大非网络线程池
		localProperties.put(INIT_SECOND_POOL_SIZE, "" + 3);// 第二线程池：最少网络线程池(json)
		localProperties.put(MAX_SECOND_POOL_SIZE, "" + 3);// 第二线程池：最大网络线程池(json)
		localProperties.put(INIT_THIRD_POOL_SIZE, "" + 3);// 第三线程池：最少网络线程池(image、file)
		localProperties.put(MAX_THIRD_POOL_SIZE, "" + 3);// 第三线程池：最大网络线程池(image、file)

		localProperties.put(CONNECT_TIMEOUT, "" + 40 * 1000);// 连接超时
		localProperties.put(CONNECT_TIMEOUT_FOR_WIFI, "" + 30 * 1000);// 连接超时
		localProperties.put(READ_TIMEOUT, "" + 40 * 1000);// 读取超时
		localProperties.put(READ_TIMEOUT_FOR_WIFI, "" + 30 * 1000);// 读取超时
		localProperties.put(ATTEMPTS, "" + 3);// 尝试次数
		localProperties.put(ATTEMPTS_TIME, "" + 0);// 尝试的间隔时间
		
		localProperties.put(PRINT_LOG, "false");// 日志开关
		localProperties.put(DEBUG_LOG, "true");// 日志开关
		localProperties.put(VIEW_LOG, "true");// 日志开关
		localProperties.put(ERROR_LOG, "true");// 日志开关
		localProperties.put(INFO_LOG, "true");// 日志开关
		localProperties.put(WARN_LOG, "true");// 日志开关
		try {
			InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream("config.properties");
			if (null != inputStream) {
				properties = new Properties();
				properties.load(inputStream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return getProperty(key, null);
	}

	public static String getProperty(String key, String defaultValue) {
		String result = null;
		if (null != properties) {
			result = properties.getProperty(key);
		}
		if (null == result) {
			result = localProperties.get(key);
		}
		if (null == result) {
			result = defaultValue;
		}
		if(Log.D) {
			Log.d("Configuration", "getProperty-->"+result);
		}
		return result;
	}
	public static Integer getIntegerProperty(String key) {
		return getIntegerProperty(key, null);
	}
	public static Properties getProperties(){
		return properties;
	}
	
	public static Integer getIntegerProperty(String key, Integer defaultValue) {
		String value = getProperty(key);
		if (null == value) {
			return defaultValue;
		}
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
			return defaultValue;
		}

	}
	
	public static Long getLongProperty(String key) {
		return getLongProperty(key, null);
	}

	public static Long getLongProperty(String key, Long defaultValue) {
		String value = getProperty(key);
		if (null == value) {
			return defaultValue;
		}
		try {
			return Long.valueOf(value);
		} catch (Exception e) {
			return defaultValue;
		}

	}

	public static Boolean getBooleanProperty(String key) {
		return getBooleanProperty(key, null);
	}

	public static Boolean getBooleanProperty(String key, Boolean defaultValue) {
		String value = getProperty(key);
		if (null == value) {
			return defaultValue;
		}
		try {
			return Boolean.valueOf(value);
		} catch (Exception e) {
			return defaultValue;
		}

	}

}
