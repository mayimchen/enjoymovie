package com.banking.xc.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanUtil {
	public static Object getValue(Object bean, String propertyName) {

		Class klass = bean.getClass();
		boolean includeSuperClass = klass.getClassLoader() != null;
		Method[] methods = (includeSuperClass) ? klass.getMethods() : klass.getDeclaredMethods();

		for (int i = 0; i < methods.length; i += 1) {
			Method method = methods[i];
			String methodName = method.getName();
			if (("get" + propertyName).equalsIgnoreCase(methodName) || (propertyName).equalsIgnoreCase(methodName)) {
				Object result = null;
				try {
					result = method.invoke(bean, (Object[]) null);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}

				return result;
			}
		}

		return null;

	}
}
