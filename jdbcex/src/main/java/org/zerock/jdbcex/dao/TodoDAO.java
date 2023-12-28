package org.zerock.jdbcex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.zerock.jdbcex.domain.TodoVO;

import lombok.Cleanup;

public class TodoDAO {
	
	// getTime()은 try-with-resource 기능을 이용해서 try()내에 선언된 변수들이 자동으로 close() 될수 있도록 구성되었다.
	// try() 내에 선언된 변수들은 모두 AutoCloseable이라는 인터페이스를 구현한 타입들이어야만 한다.
	public String getTime() {
		

		String now = null;

		try (Connection connection = ConnectionUtil.INSTANCE.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("select now()");
				ResultSet resultSet = preparedStatement.executeQuery();) {

			resultSet.next(); // 테이블의 다음행을 읽음.

			now = resultSet.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return now;
	}
	
	
	// @cleaup: try-catch 가 중첩되는경우 가독성이 상당히 나빠지게 될떄 적용 고려
	// @cleanup이 추가된 변수는 해당 메소드가 끝날때 close() 가 호출되는것이 보장된다.
	public String getTime2() throws Exception {
		
		
		@Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement preparedStatement = connection.prepareStatement("select now()");
		
		@Cleanup ResultSet resultSet = preparedStatement.executeQuery();
		
		resultSet.next();
		
		String now = resultSet.getString(1);
		
		return now;
		
	}
	
	
	public void insert(TodoVO vo) throws Exception {
		String sql = "insert into tbl_todo(title, dueDate, finished) values (?, ?, ?)";
		
		@Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setString(1, vo.getTitle());
		preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
		preparedStatement.setBoolean(3, vo.isFinished());
		
		preparedStatement.executeUpdate();
		
	}

}
