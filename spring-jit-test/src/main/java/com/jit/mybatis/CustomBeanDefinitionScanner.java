package com.jit.mybatis;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Iterator;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
public class CustomBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {

	public CustomBeanDefinitionScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {

		// 借助 Spring 的 doScan 方法实现扫描并生成 BeanDefinition 功能
		Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);

		// 扫描完成后，对生成的 BeanDefinition 进行处理
		processBeanDefinitions(beanDefinitionHolders);

		return beanDefinitionHolders;
	}

	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isInterface();
	}

	public void registerFilters() {
		// 这里设置为扫描所有类
		this.addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
	}

	private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
		Iterator var3 = beanDefinitions.iterator();

		// 因为生成的 BeanDefinition 只是个空的对象，无法满足后续实例化要求，所以这里循环赋值
		while (var3.hasNext()) {
			BeanDefinitionHolder holder = (BeanDefinitionHolder) var3.next();
			GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();

			// 强制指定 CustomFactoryBean 的构造函数，让 Spring 实例化对象时，使用该构造函数
			definition.getConstructorArgumentValues().addGenericArgumentValue(definition.getBeanClassName());

			// 将所有 Mapper 接口的类型设置为 CustomFactoryBean（内部创建动态代理），
			definition.setBeanClassName(CustomFactoryBean.class.getName());

			// 让 Spring 自动注入依赖，即执行类中的所有 set 方法，实现 setSqlSession 方法的注入
			definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
		}
	}
}
