package com.jit.service.impl;

import com.jit.service.FooService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Service
public class FooServiceImpl implements FooService {
	@Override
	public void hello() {
		System.out.println("Hello AOP!");
	}
}
