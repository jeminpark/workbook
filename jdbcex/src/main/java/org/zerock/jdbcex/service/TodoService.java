package org.zerock.jdbcex.service;

import org.modelmapper.ModelMapper;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.util.MapperUtil;

//DTO와 VO를 둘다 이용해야 하는 TodoService를 구성하고 ModelMapper의 동작을 확인한다.
// TodoService는 ModelMapper 와 TodoDAO를 이용할수 있도록 구성하고, 새로운 TodoDTO를 등록하는 기능을 추가한다.
public enum TodoService {	
	INSTANCE;
	
	private TodoDAO dao;
	private ModelMapper modelMapper;
	
	// register()는 TodoDTOfmㄹ 파라미터로 받아서 TodoVO로 변환하는 과정이 필요하다.
	// 이를 확인하기 위해 ModelMapper로 처리된 TodoVO를 system.out.println을 이용해서 확인하도록 한다. (Log4j2로 대체 이전)
	// 마지막으로 TodoDAO를 이용해서 insert()를 실행하고 TodoVO를 등록한다.
	TodoService() {
		
		dao = new TodoDAO();
		modelMapper = MapperUtil.INSTANCE.get();
		
	}
	
	public void register(TodoDTO todoDTO) throws Exception {
		
		TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
		
		System.out.println("todoVO: " + todoVO );
		
		dao.insert(todoVO); // int를 반환 하므로 이를 이용해서 예외처리도 가능
	}

}
