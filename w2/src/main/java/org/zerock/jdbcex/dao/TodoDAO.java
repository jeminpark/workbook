package org.zerock.jdbcex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	
	// insert()는 파라미터로 입력된 TodoVO 객체의 정보를 이용해서 DML(insert/update/delete)문을 실행하기 때문에 executeUpdate()를 실행하도록 구성한다.
	// PreparedStatement는 '?' 를 이용해서 나중에 전달할 데이터들을 지정하는데 setXXX()를 이용해서 실제값들을 지정한다.
	// 이떄 인덱스번호가 0이아닌 1부터 시작한다.
	// 3개의 '?' 가 존재하므로 setXXX() 역시 3개를 지정한다.
	// setXXX()는 다양한 타입에 맞춰서 가뵤을 지정할 수있는데 날짜의 경우 LocalDate타입을 지원하지 않기 때문에 java.sql.Date 타입을 이용해 변환해서 추가 해야한다.
	public void insert(TodoVO vo) throws Exception {
		String sql = "insert into tbl_todo(title, dueDate, finished) values (?, ?, ?)";
		
		@Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setString(1, vo.getTitle());
		preparedStatement.setDate(2, Date.valueOf(vo.getDueDate()));
		preparedStatement.setBoolean(3, vo.isFinished());
		
		preparedStatement.executeUpdate();
		
	}
	
	
	// selectAll()은 쿼리(select)를 실행해야 하기 때문에 PreparedStatement의 executeQuery를 이용해서 ResultSet을 구한다.
	// ResultSet으로 각 행(row)를 이동하면서 각 행의 데이터를 TodoVO로 변환한다.
	// TodoVO는 빌더 패턴을 이용해서 간편하게 TodoVO 객체를 생성 할수 있는데 tno/title등의 속성값을 ResultSet에서 가져온 데이터로 처리한다.
	// ResultSet의 getXXX()는 컬럼의 인덱스 번호를 이용하거나 컬럼의 이름을 지정해서 가져올수 있는데 인덱스 번호를 이용하는 경우 반드시 1 부터 시작한다.
	public List<TodoVO> selectAll() throws Exception {
		
		String sql = "select * from tbl_todo";
		
		@Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		@Cleanup ResultSet resultSet = preparedStatement.executeQuery();
		
		List<TodoVO> list = new ArrayList<>();
		
		while(resultSet.next()) {
			TodoVO vo = TodoVO.builder()
					.tno(resultSet.getLong("tno"))
					.title(resultSet.getString("title"))
					.dueDate(resultSet.getDate("dueDate").toLocalDate())
					.finished(resultSet.getBoolean("Finished"))
					.build();
			
			list.add(vo);
		}
		
		return list;
	}
	
	
	// 경우에 따라서 특정한 번호(tno)의 데이터만 가져오는 기능이 필요하다.
	// selectOne() 이라는 메소드에는 특정한 번호(tno) 가 파라미터가되고, TodoVO를 리턴타입으로 지정한다.
	// selectOne() 은 selectAll()과 마찬가지로 쿼리(select)를 실행하기 때문에 ResultSet 이 필요하다.
	// 여러개의 데이터가 나오는 selectAll()과 달리 selectOne()은 한 행(row)의 데이터만 나오기 때문에
	// while(resultSet.next()) 대신에 한번만 resultSet.next()를 실행하면된다.
	public TodoVO selectOne(Long tno) throws Exception {
		
		String sql = "select * from tbl_todo where tno = ?";
		
		@Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, tno);
		
		@Cleanup ResultSet resultSet = preparedStatement.executeQuery();
		
		resultSet.next();
		TodoVO vo = TodoVO.builder()
				.tno(resultSet.getLong("tno"))
				.title(resultSet.getString("title"))
				.dueDate(resultSet.getDate("dueDate").toLocalDate())
				.finished(resultSet.getBoolean("finished"))
				.build();
		
		return vo;
	}
	
	
	
	// 삭제 기능은 조회와 비슷하지만 쿼리(select) 가 아니다.
	// 삭제할 때에도 특정한 번호(tno) 가 필요하기 때문에 다음과 같은 형태로 작성한다.
	public void deleteOne(Long tno) throws Exception {
		
		String sql = "delete from tbl_todo where tno = ?";
		
		@Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, tno);
		preparedStatement.executeUpdate();
		
	}
	
	
	// 수정기능은 특정한 번호(tno)를 가진 데이터의 제목(title)과 만료일(dueDate), 완료여부(finish)를 update하도록 구성해야한다.
	// updateOne()은 파라미터로 모든 정보가 다 담겨있는 TodoVO를 받아서 executeUpdate를 실행한다.
	// 중요한 데이터의 처리는 PreparedStatement에 의해서 처리되는데 'update tbl_todo... '부분에 4개의 '?' 가 있으므로 1부터 순번에 맞는 데이터를 
	// TodoVO 객체의 getXXX() 를 통해서 지정한다.
	public void updateOne(TodoVO todoVO) throws Exception {
		
		String sql = "update tbl_todo set title = ?, dueDate = ?, finished = ? where tno = ?";
		
		@Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
		@Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setString(1, todoVO.getTitle());
		preparedStatement.setDate(2, Date.valueOf(todoVO.getDueDate()));
		preparedStatement.setBoolean(3, todoVO.isFinished());
		preparedStatement.setLong(4, todoVO.getTno());
		
		preparedStatement.executeUpdate();
		
	}
	

}
