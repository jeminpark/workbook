package org.zerock.service;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TodoServiceTests {
	
	private TodoService todoService;
	
	@BeforeEach
	public void ready() {
		todoService = TodoService.INSTANCE;
	}
	
	
	// TodoService의 register 기능의 동작을 테스트한다.
	// testRegister()를 실행한 후에는 정상적으로 TodoVO의 내용이 출력되는지와 tbl_todo테이블에 insert가 정상적으로 되었는지 확인하는 작업이 필요하다.
	
	// 변경된 코드는 @Log4j2를 이용해서 수정하고, log.info()를 이용해서 출력한다.
	@Test
	public void testRegister() throws Exception {
		
		TodoDTO todoDTO = TodoDTO.builder()
				.title("JDBC Test Title")
				.dueDate(LocalDate.now())
				.build();
		
		log.info("------------------------------");
		log.info(todoDTO);
		
		todoService.register(todoDTO);
		
	}

}
