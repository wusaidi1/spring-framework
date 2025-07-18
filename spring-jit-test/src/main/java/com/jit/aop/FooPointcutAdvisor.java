package com.jit.aop;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
public class FooPointcutAdvisor {

	@Nullable
	public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
		System.out.println("1before invoke target method...");
		Object result = invocation.proceed();
		System.out.println("1after invoke target method...");
		return result;
	}
}
