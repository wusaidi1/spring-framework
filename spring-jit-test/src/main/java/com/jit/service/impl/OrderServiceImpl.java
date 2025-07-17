package com.jit.service.impl;

import com.jit.mapper.OrderMapper;
import com.jit.service.OrderService;
import com.jit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	public OrderMapper orderMapper;

	@Override
	public int queryOrderPrice() {
		return orderMapper.selectPrice();
	}
}
