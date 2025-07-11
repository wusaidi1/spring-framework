package com.jit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
public class JitAppContext {

	/**
	 * BeanDefinition 缓存
	 */
	private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>(256);

	/**
	 * 单例池 Bean 缓存
	 * 一级缓存
	 */
	private final Map<String, Object> singletonObjects = new HashMap<>(256);

	/**
	 * 二级缓存，用于存储创建 Bean 过程中刚刚实例化完的对象
	 */
	private final Map<String, Object> earlySingletonObjects = new HashMap<>(256);

	/**
	 * 三级缓存
	 */
	private final Map<String, ObjectFactory> factoriesEarlySingletonObjects = new ConcurrentHashMap<>(256);

	public static void main(String[] args) {
		JitAppContext context = new JitAppContext();
		System.out.println(context.getBean("a").getClass());
		B b = (B) context.getBean("b");
		System.out.println(b.getA().getClass());
	}

	public interface AService {
	}

	public interface BService {
	}

	public static class A implements AService {
		@Autowired
		private BService b;
	}

	public static class B implements BService {
		@Autowired
		private AService a;

		public AService getA() {
			return a;
		}
	}

	public JitAppContext() {
		refresh();
	}

	private void refresh() {

		// 扫描并注册 BeanDefinitions
		invokeBeanFactoryPostProcessors();

		// 实例化所有非懒加载单例 Bean
		finishBeanFactoryInitialization();
	}

	private void finishBeanFactoryInitialization() {
		for (String beanName : beanDefinitionMap.keySet()) {
			getBean(beanName);
		}
	}

	private Object getBean(String beanName) {
		return doGetBean(beanName);
	}

	private Object doGetBean(String beanName) {

		Object beanInstance = getSingleton(beanName);
		if (beanInstance != null) {
			return beanInstance;
		}

		beanInstance = getSingleton(beanName, () -> {
			try {
				return createBean(beanName, beanDefinitionMap.get(beanName));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		return beanInstance;
	}

	private Object getSingleton(String beanName) {
		return getSingleton(beanName, true);
	}

	// 模拟 Spring 处理循环依赖的代码
	private Object getSingleton(String beanName, boolean allowEarlyReference) {
		Object singletonObject = singletonObjects.get(beanName);
		if (singletonObject == null) {
			singletonObject = earlySingletonObjects.get(beanName);
			if (singletonObject == null && allowEarlyReference) {
				synchronized (singletonObjects) { // DLC（双重检查锁）
					singletonObject = singletonObjects.get(beanName);
					if (singletonObject == null) {
						singletonObject = earlySingletonObjects.get(beanName);
						if (singletonObject == null) {
							ObjectFactory<?> singletonFactory = factoriesEarlySingletonObjects.get(beanName);
							if (singletonFactory != null) {
								singletonObject = singletonFactory.getObject();
								earlySingletonObjects.put(beanName, singletonObject);
								factoriesEarlySingletonObjects.remove(beanName);
							}
						}
					}
				}
			}
		}
		return singletonObject;
	}

	// 模拟 Spring 创建 Bean 对象的代码
	private Object getSingleton(String beanName, ObjectFactory<?> singletonFactory) {

		// 结合前面的代码，Spring 先尝试从 单例 Bean 缓存池中获取 Bean，
		// 没有才会再走到这，所以这里相当于有个隐藏的判断
		// if (getSingleton(beanName) != null)
		// 即这里本质上是一个 DCL（双重锁检查机制）
		synchronized (singletonObjects) {
			Object singletonObject = singletonObjects.get(beanName);
			if (singletonObject == null) {
				singletonObject = singletonFactory.getObject();
				addSingleton(beanName, singletonObject);
			}
			return singletonObject;
		}
	}

	private void addSingleton(String beanName, Object singletonObject) {
		singletonObjects.put(beanName, singletonObject);
		earlySingletonObjects.remove(beanName);
		factoriesEarlySingletonObjects.remove(beanName);
	}

	private Object createBean(String beanName, BeanDefinition beanDefinition) throws Exception {
		return doCreateBean(beanName, beanDefinition);
	}

	/**
	 * 模拟 Spring 创建 Bean 的过程
	 */
	private Object doCreateBean(String beanName, BeanDefinition beanDefinition) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

		// 创建 Bean 实例
		RootBeanDefinition rootBeanDefinition = (RootBeanDefinition) beanDefinition;
		Class<?> beanClass = rootBeanDefinition.getBeanClass();
		Object bean = beanClass.getConstructor().newInstance();

		// 通过三级缓存的方式延迟 AOP 代理对象的创建
		factoriesEarlySingletonObjects.put(beanName, () -> new JDKProxyBeanPostProcessor().getEarlyBeanReference(bean, beanName));

		// 属性赋值
		Object exposedObject = bean;
		for (Field declaredField : beanClass.getDeclaredFields()) {
			// 模拟解析被 @Autowired 注解标注了的字段
			if (declaredField.getAnnotation(Autowired.class) != null) {
				String dependName = declaredField.getName();
				Object dependBean = doGetBean(dependName);
				declaredField.setAccessible(true);
				declaredField.set(exposedObject, dependBean);
			}
		}
		// ...

		// 初始化
		// ...

		Object earlySingletonReference = getSingleton(beanName);
		if (earlySingletonReference != null) {
			exposedObject = earlySingletonReference;
		}

		return exposedObject;
	}

	private void invokeBeanFactoryPostProcessors() {

		// 扫描 BeanDefinitions
		// ...

		// 注冊 BeanDefinitions
		registerBeanDefinition();
	}

	private void registerBeanDefinition() {
		BeanDefinition A = new RootBeanDefinition(A.class);
		BeanDefinition B = new RootBeanDefinition(B.class);
		beanDefinitionMap.put("a", A);
		beanDefinitionMap.put("b", B);
	}

	public static class DynamicProxy implements InvocationHandler {

		private Object target;

		public DynamicProxy(Object target) {
			this.target = target;
		}

		public <T> T getProxyInstance() {
			return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
					target.getClass().getInterfaces(),
					this);
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return method.invoke(target, args);
		}
	}

	public static class JDKProxyBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {
		@Override
		public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {

			// 这里简化了针对 @PointCut 注解的解析，直接模拟 A 进行 AOP
			if (bean instanceof JitAppContext.A) {
				DynamicProxy dynamicProxy = new DynamicProxy(bean);
				return dynamicProxy.getProxyInstance();
			}

			return bean;
		}
	}
}
