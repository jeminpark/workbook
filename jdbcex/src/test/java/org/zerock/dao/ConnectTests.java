package org.zerock.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
	
	@Test
	public void testConnection() throws Exception {
		
		Class.forName("org.mariadb.jdbc.Driver");
//		- JDBC 드라이버 클래스를 메모리 상으로 로딩하는 역할을 한다.
//		- 이때 문자열은 패키지명과 클래스명의 대소문자까지 정확히 일치해야한다.
//		- 만일 JDBC 드라이버 파일이 없는 경우에는 이부분 에서 예외처리가 발생한다.
		
		Connection connection = DriverManager.getConnection(      //java.sql 패키지의 Connection 은 데이터베이스와 네트워크 연결을 의미한다. 
				"jdbc:mariadb://localhost:3506/webdb",  //jdbc 프로토콜을 이용, localhost:3506 네트워크 연결정보, webdb 연결하려는 database 정보 의미.
				"webuser",  //계정
				"webuser"); 
		
//		DriverManager.getConnection(): 데이터베이스 내에 있는 여러 정보들을 통해서 특정한 데이터베이스에 연결 시도 (예제 webdb)
		
		Assertions.assertNotNull(connection);
		
		connection.close();
				
	}
	
}
