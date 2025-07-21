package com.jit.service.impl;

import com.jit.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Service
public class FooServiceImpl implements FooService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private FooService fooService;

	@Override
	@Transactional
	public void add() {
		jdbcTemplate.execute("insert into user(id,name) values(1,'张三')");
		add2();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void add2() {
		jdbcTemplate.execute("insert into user(id,name) values(2,'李四')");
	}

	@Override
	public void hello() {
		System.out.println("Hello AOP!");
	}
}
