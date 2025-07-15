package com.jit.processor;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Component
public class CustomSmartInitializingSingleton implements SmartInitializingSingleton {
	@Override
	public void afterSingletonsInstantiated() {
		System.out.println("finish singletons instantiation.");
	}
}
