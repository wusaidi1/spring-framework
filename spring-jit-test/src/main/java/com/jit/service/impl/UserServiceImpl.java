package com.jit.service.impl;

import com.jit.service.OrderService;
import com.jit.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Service
public class UserServiceImpl implements UserService, BeanNameAware, InitializingBean, DisposableBean {

	@Autowired
	private OrderService orderService;

	public UserServiceImpl() {
		System.out.println("02. post constructor ");
	}

	@Override
	public void sayHello() {
		System.out.println("Hello, Spring!");
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("06. post aware interface");
	}

	@Override
	public OrderService getOrderService() {
		System.out.println("orderService: " + orderService);
		return orderService;
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("08. init callback (post construct)");
	}

	@Override
	public void afterPropertiesSet() {
		System.out.println("09. init callback (afterPropertiesSet)");
	}

	public void init() {
		System.out.println("10. init callback (post init method)");
	}

	@Override
	public void destroy() {
		System.out.println("12. destroy callback (destroy method)");
	}
}




