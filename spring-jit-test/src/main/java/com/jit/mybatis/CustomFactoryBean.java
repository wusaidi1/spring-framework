package com.jit.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *
 * </p>
 *
 * @Author: JIT
 */

public class CustomFactoryBean implements FactoryBean {

	private Class mapperInterface;

	private SqlSession sqlSession;

	public CustomFactoryBean() {
	}

	public CustomFactoryBean(Class mapperInterface) {
		this.mapperInterface = mapperInterface;
	}

	public void setSqlSession(SqlSessionFactory sqlSessionFactory) {
		sqlSessionFactory.getConfiguration().addMapper(mapperInterface);
		this.sqlSession = sqlSessionFactory.openSession();
	}

	@Override
	public Object getObject() throws Exception {
		return sqlSession.getMapper(mapperInterface);
	}

	@Override
	public Class<?> getObjectType() {
		return mapperInterface;
	}
}
