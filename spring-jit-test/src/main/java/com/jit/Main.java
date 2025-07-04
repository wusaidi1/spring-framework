package com.jit;

import com.jit.impl.UserServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Configuration
@ComponentScan("com.jit")
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		UserServiceImpl userService = context.getBean(UserServiceImpl.class);
		userService.sayHello();
	}
}