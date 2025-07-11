package com.jit.processor;

import com.jit.service.UserService;
import com.jit.service.impl.UserServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Component
public class CustomBeanPostProcessor implements InstantiationAwareBeanPostProcessor, MergedBeanDefinitionPostProcessor {

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (beanName.equals("userServiceImpl"))
			System.out.println("01. before instance");
		return null;
	}

	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		if (beanName.equals("userServiceImpl")) {
			beanDefinition.setInitMethodName("init");
			System.out.println("03. post mergedBeanDefinition call back");
		}
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if (beanName.equals("userServiceImpl"))
			System.out.println("04. after instance");
		return true;
	}

	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		if (beanName.equals("userServiceImpl")) {
			UserService userService = (UserService) bean;
			userService.getOrderService();
			System.out.println("05. before properties");
		}
		return null;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("userServiceImpl"))
			System.out.println("07. before init");
		return InstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("userServiceImpl"))
			System.out.println("11. after init");
		return InstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
