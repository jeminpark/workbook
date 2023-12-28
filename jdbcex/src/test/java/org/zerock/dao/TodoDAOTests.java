package org.zerock.dao;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;

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
	
	
	// TodoDAO에 작성된 insert() 메소드를 테스트 하기위해 작성
	// TodoVO 에 선언한 @Builder 를 사용하는방법
	// 빌더 패턴은 생성자와 달리 필요한 만큼만 데이터를 세팅할 수 있다
	// finish 속성은 기본적으로 false로 지정되있고, 변경할 필요가 없기 때문에 세팅하는부분이 없다.
	
	@Test
	public void testInsert() throws Exception {
		TodoVO todoVO = TodoVO.builder()
				.title("Sample Title...")
				.dueDate(LocalDate.of(2021, 12, 31))
				.build();
		
		todoDAO.insert(todoVO);
	}
	
	
	
	// selectAll()메소드를 테스트 한다.
	@Test
	public void testList() throws Exception {
		
		List<TodoVO> list = todoDAO.selectAll();
		
		list.forEach(vo -> System.out.println(vo));
	}

}
