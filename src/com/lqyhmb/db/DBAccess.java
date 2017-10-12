package com.lqyhmb.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 访问数据库类
 */
public class DBAccess {

	/**
	 * 获取SqlSession
	 * 
	 * @return
	 * @throws IOException
	 */
	public SqlSession getSqlSession() throws IOException {
		// 通过配置文件获取数据库连接信息
		Reader reader = Resources.getResourceAsReader("com/lqyhmb/config/Configuration.xml");
		// 通过配置信息构建SqlSessionFactory
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 通过SqlSessionFactory打开数据库会话
		SqlSession sqlSession = sessionFactory.openSession();
		return sqlSession;
	}

}
