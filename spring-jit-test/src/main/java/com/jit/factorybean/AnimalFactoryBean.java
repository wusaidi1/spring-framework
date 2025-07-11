package com.jit.factorybean;

import com.jit.dto.Dog;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Component
public class AnimalFactoryBean implements FactoryBean {
	@Override
	public Object getObject() {
		return new Dog();
	}

	@Override
	public Class<?> getObjectType() {
		return Dog.class;
	}
}
