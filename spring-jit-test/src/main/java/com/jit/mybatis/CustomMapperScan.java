package com.jit.mybatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(CustomImportBeanDefinitionRegistrar.class)
public @interface CustomMapperScan {

	String value();
}
