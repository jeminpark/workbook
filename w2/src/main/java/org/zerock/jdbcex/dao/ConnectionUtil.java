package org.zerock.jdbcex.dao;

import java.sql.Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum ConnectionUtil {

	INSTANCE;
	
	private HikariDataSource ds;
	
	ConnectionUtil() {
		// 하나의 객체를 만들어서 사용하는 방식으로 구성
		
		// HikariConfig을 이용해서 하나의 HikariDataSource 를 구성한다.
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("org.mariadb.jdbc.Driver");
		config.setJdbcUrl("jdbc:mariadb://localhost:3506/webdb");
		config.setUsername("webuser");
		config.setPassword("webuser");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		
		ds = new HikariDataSource(config);
		
		
	}
	
	public Connection getConnection() throws Exception {
		// 구성된 HikariDataSource = ds 를 getConnection을 통해서 사용하게 되는데
		// 외부에서는 ConnectionUtil.INSTANCE.getConnection()을 통해서 Connection을 얻게 된다.
		return ds.getConnection();
	}
}
