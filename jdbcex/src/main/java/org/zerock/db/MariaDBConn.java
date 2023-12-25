package org.zerock.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDBConn {
	public static void main(String[] args) {
		
		String url = "jdbc:mariadb://localhost:3506/webdb";
		String username = "webuser";
		String password = "webuser";
		
		//JDBC연결
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			if(connection != null) {
				System.out.println("MariaDB에 연결되었습니다.");
				
				//여기에 데이터베이스 작업 수행
				//예: 쿼리실행 등
			}
		}
		catch(SQLException e) {
			System.out.println("MariaDB 연결 실패");
			e.printStackTrace();
		}
	}

}
