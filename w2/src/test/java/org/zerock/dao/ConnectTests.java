package org.zerock.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectTests {

//	@Test
//	public void Test1() {
//		
//		int v1 = 10;
//		int v2 = 10;
//		
//		Assertions.assertEquals(v1,v2);
//		
//		
//	}
	
//	@Test
//	public void testConnection() throws Exception {
//		
//		Class.forName("org.mariadb.jdbc.Driver");
////		- JDBC 드라이버 클래스를 메모리 상으로 로딩하는 역할을 한다.
////		- 이때 문자열은 패키지명과 클래스명의 대소문자까지 정확히 일치해야한다.
////		- 만일 JDBC 드라이버 파일이 없는 경우에는 이부분 에서 예외처리가 발생한다.
//		
//		Connection connection = DriverManager.getConnection(      //java.sql 패키지의 Connection 은 데이터베이스와 네트워크 연결을 의미한다. 
//				"jdbc:mariadb://localhost:3506/webdb",  //jdbc 프로토콜을 이용, localhost:3506 네트워크 연결정보, webdb 연결하려는 database 정보 의미.
//				"webuser",  //계정
//				"webuser"); 
//		
////		DriverManager.getConnection(): 데이터베이스 내에 있는 여러 정보들을 통해서 특정한 데이터베이스에 연결 시도 (예제 webdb)
//		
//		Assertions.assertNotNull(connection);
//		
//		connection.close();
//				
//	}
	
	@Test
	public void testHikariCP() throws Exception {
		
		HikariConfig config = new HikariConfig();						// HikariConfig 객체 선언
		config.setDriverClassName("org.mariadb.jdbc.Driver");			// setDriverClass로 mariadb JDBC드라이버 설정
		config.setJdbcUrl("jdbc:mariadb://localhost:3506/webdb");		// setJdbcUrl로 jdbc드라이버 정보 및 주소 설정
		config.setUsername("webuser");									// 계정 설정
		config.setPassword("webuser");
		config.addDataSourceProperty("cachePrepStmts", "true");			// MySQL은 PreparedStatement Caching을 비활성화하고 있기 때문에, 이 옵션을 허용해줘야 아래의 옵션값들이 실제 DB에 영향을 줄 수 있다.
		config.addDataSourceProperty("prepStmtCacheSize", "250");		// MariaDB 드라이버가 Connection마다 캐싱할 PreparedStatement의 개수를 지정하는 옵션이다. HikariCP에서는 250 ~ 500개 정도를 추천한다.
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");	// MariaDB 드라이버가 캐싱할 PreparedStatement의 최대 길이를 지정하는 옵션이다. HikariCP 개발자들의 경험상, Hibernate와 같은 ORM framework를 사용하는 경우에 특히 이 기본값이 턱없이 모자란다고 한다.
		
		HikariDataSource ds = new HikariDataSource(config);				// 설정해놓은 HikariConfig를 이용해서 HikariDatasource 객체를 선언
		Connection connection = ds.getConnection();						// HikariDataSource 객체를 사용해서 Connection 객체 선언
		
		System.out.println(connection);									// 연결되는지 출력
		
		connection.close();												// 연결 끊기
	}
	
}
