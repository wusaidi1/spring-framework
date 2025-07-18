package com.jit.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Aspect
@Component
public class FooAspect {

	@Pointcut("execution(* com.jit.service.FooService.hello(..))")
	public void pointcut() {}

	@Before("pointcut()")
	public void fooBefore(JoinPoint joinPoint) {
		System.out.println("before invoke target method...");
	}
}
