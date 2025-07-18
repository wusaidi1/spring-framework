package com.jit;

import com.jit.mapper.OrderMapper;
import com.jit.mybatis.CustomMapperScan;
import com.jit.service.FooService;
import com.jit.service.OrderService;
import com.jit.service.UserService;
import com.jit.service.impl.FooServiceImpl;
import com.jit.service.impl.UserServiceImpl;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.context.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Configuration
@ComponentScan
//@CustomMapperScan("com.jit.mapper")
//@MapperScan("com.jit.mapper")
@EnableAspectJAutoProxy
public class Main {
	public static void main(String[] args) throws IOException {

//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
//		context.close();

//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//		System.out.println(context.getBean("dog"));
//		System.out.println(context.getBean("dog"));
//		System.out.println(context.getBean("dog"));

//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
//		System.out.println(context.getBean("&animalFactoryBean"));
//		System.out.println(context.getBean("animalFactoryBean"));

//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
//		UserService userService = context.getBean(UserService.class);
//		userService.sayHello();
//		context.close();

//		InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
//
//		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//
//		OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
//
//		System.out.println(orderMapper.selectPrice());
//
//		sqlSession.commit();
//		sqlSession.flushStatements();
//		sqlSession.commit();

//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
//		OrderService orderService = context.getBean(OrderService.class);
//		System.out.println(orderService.queryOrderPrice());

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		FooService fooService = context.getBean(FooService.class);
		fooService.hello();


//		FooServiceImpl fooServiceImpl = new FooServiceImpl();
//		ProxyFactory proxyFactory = new ProxyFactory();
//		proxyFactory.setTarget(fooServiceImpl);
//		proxyFactory.addAdvisor (new PointcutAdvisor() {
//			@Override
//			public Pointcut getPointcut() {
//				return new StaticMethodMatcherPointcut() {
//					@Override
//					public boolean matches(Method method, Class<?> targetClass) {
//						return method.getName().equals("hello");
//					}
//				};
//			}
//
//			@Override
//			public Advice getAdvice() {
//				return (MethodInterceptor) invocation -> {
//					System.out.println("before invoke target method...");
//					Object result = invocation.proceed();
//					System.out.println("after invoke target method...");
//					return result;
//				};
//			}
//
//			@Override
//			public boolean isPerInstance() {
//				return false;
//			}
//		});
//
//		FooService fooService = (FooService) proxyFactory.getProxy();
//		fooService.hello();
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
}