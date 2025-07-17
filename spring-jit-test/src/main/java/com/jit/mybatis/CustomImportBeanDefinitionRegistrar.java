package com.jit.mybatis;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
public class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {

		// 读取扫描 Mapper 接口的包路径
		Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(CustomMapperScan.class.getName());
		String path = annotationAttributes.get("value").toString();

		// 实例化自定义的扫描类（实现的是 ClassPathBeanDefinitionScanner）
		CustomBeanDefinitionScanner scanner = new CustomBeanDefinitionScanner(registry);

		// 这里可以设置一些过滤条件，比如只扫描带有 @Mapper 注解的接口
		scanner.registerFilters();

		// 执行扫描
		scanner.scan(path);
	}
}
