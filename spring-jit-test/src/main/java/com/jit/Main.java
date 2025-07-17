package com.jit;

import com.jit.mapper.OrderMapper;
import com.jit.mybatis.CustomMapperScan;
import com.jit.service.OrderService;
import com.jit.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */
@Configuration
@ComponentScan
@CustomMapperScan("com.jit.mapper")
@MapperScan("com.jit.mapper")
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

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
		OrderService orderService = context.getBean(OrderService.class);
		System.out.println(orderService.queryOrderPrice());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
}