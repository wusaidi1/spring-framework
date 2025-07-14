package com.jit.processor;

import com.jit.service.UserService;
import com.jit.service.impl.UserServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Component
public class CustomBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor, MergedBeanDefinitionPostProcessor {

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (beanName.equals("userServiceImpl"))
			System.out.println("01. before instance");
		return SmartInstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
	}

	@Override
	public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
		if (beanName.equals("userServiceImpl")) {
			System.out.println("02. determine constructors");
		}
		return SmartInstantiationAwareBeanPostProcessor.super.determineCandidateConstructors(beanClass, beanName);
	}

	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		if (beanName.equals("userServiceImpl")) {
			beanDefinition.setInitMethodName("init");
			System.out.println("04. post mergedBeanDefinition call back");
		}
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if (beanName.equals("userServiceImpl"))
			System.out.println("05. after instance");
		return SmartInstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
	}

	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		if (beanName.equals("userServiceImpl")) {
			UserService userService = (UserService) bean;
			userService.getOrderService();
			System.out.println("06. before properties");
		}
		return SmartInstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
	}


	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("userServiceImpl"))
			System.out.println("09. before init");
		return SmartInstantiationAwareBeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals("userServiceImpl"))
			System.out.println("12. after init");
		return SmartInstantiationAwareBeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
}
