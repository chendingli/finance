package com.chinasoft.util.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware, java.io.Serializable {
	
	// Spring应用上下文环境
	private static ApplicationContext applicationContext;
	
	/*
	 * 实现了ApplicationContextAware 接口，必须实现该方法；
	 *通过传递applicationContext参数初始化成员变量applicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) applicationContext.getBean(name);
	}
}
