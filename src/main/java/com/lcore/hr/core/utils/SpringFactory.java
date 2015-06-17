package com.lcore.hr.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringFactory implements ApplicationContextAware{
	public SpringFactory() {
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}

	public static Object getObject(String id) {
		Object object = null;
		object = context.getBean(id);
		return object;
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	private static ApplicationContext context;
}
