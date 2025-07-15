package com.jit.listener;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Component
public class CustomLifecycle implements SmartLifecycle {

	public boolean isRunning;

	@Override
	public void start() {
		System.out.println("spring ioc started");
		this.isRunning = true;
	}

	@Override
	public void stop() {
		System.out.println("spring ioc stopped");
	}

	@Override
	public boolean isRunning() {
		return this.isRunning;
	}
}
