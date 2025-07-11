package com.jit;

import com.jit.factorybean.AnimalFactoryBean;
import com.jit.service.UserService;
import com.jit.service.impl.UserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Configuration
@ComponentScan
public class Main {
	public static void main(String[] args) {

//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//		System.out.println(context.getBean("dog"));
//		System.out.println(context.getBean("dog"));
//		System.out.println(context.getBean("dog"));

//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
//		System.out.println(context.getBean("&animalFactoryBean"));
//		System.out.println(context.getBean("animalFactoryBean"));

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		UserService userService = context.getBean(UserService.class);
		userService.sayHello();
		context.close();
	}
}