package org.zerock.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.jdbcex.dao.TodoDAO;

public class TodoDAOTests {
	// DAO를 작성하면 항상 테스트 코드를 이용해서 동작에 문제가 없는지 확인하는것이 좋다.
	
	private TodoDAO todoDAO;
	
	@BeforeEach
	public void ready() {
		//TodoDAOTests는 @BeforeEach를 이용하는 ready()를 통해서 모든 테스트 전에 TodoDAO 타입의 객체를 생성하도록한다.
		
		todoDAO = new TodoDAO();
		
	}
	
	@Test
	public void testTime() throws Exception {
		// testTime을 이용해서 TodoDAO에 작성한 getTime() 이 정상동작 하는지 확인한다.
		
		System.out.println(todoDAO.getTime());
		
	}

}
