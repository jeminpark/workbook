package org.zerock.jdbcex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.util.MapperUtil;

import lombok.extern.log4j.Log4j2;

// system.out.println() 대신에 log.info() 로 변경
@Log4j2
//DTO와 VO를 둘다 이용해야 하는 TodoService를 구성하고 ModelMapper의 동작을 확인한다.
// TodoService는 ModelMapper 와 TodoDAO를 이용할수 있도록 구성하고, 새로운 TodoDTO를 등록하는 기능을 추가한다.
public enum TodoService {
	INSTANCE;

	private TodoDAO dao;
	private ModelMapper modelMapper;

	// register()는 TodoDTOfmㄹ 파라미터로 받아서 TodoVO로 변환하는 과정이 필요하다.
	// 이를 확인하기 위해 ModelMapper로 처리된 TodoVO를 system.out.println을 이용해서 확인하도록 한다.
	// (Log4j2로 대체 이전)
	// 마지막으로 TodoDAO를 이용해서 insert()를 실행하고 TodoVO를 등록한다.
	TodoService() {

		dao = new TodoDAO();
		modelMapper = MapperUtil.INSTANCE.get();

	}

	// Todo의 등록기능은 GET방식으로 등록화면을보고 <form> 태그내에 입력 항목 들을 채운후에 POST 방식으로 처리한다.
	// 처리 후에는 목록화면으로 redirect하는 PRG패턴 방식이다.
	// register() 는 파라미터로 TodoDTO 객체를 받아서 TodoVO로 변환하고 이를 저장한다.
	public void register(TodoDTO todoDTO) throws Exception {

		TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

//		System.out.println("todoVO: " + todoVO );
		log.info(todoVO);

		dao.insert(todoVO); // int를 반환 하므로 이를 이용해서 예외처리도 가능
	}

	// TodoDAO 에서 가져온 TodoVO 의 목록을 모두 TodoDTO로 변환해서 반환해야한다.
	// 이떄 ModelMapper와 Java Stream 의 map()을 이용하면 간단하게 처리할수 있다.
	public List<TodoDTO> listAll() throws Exception {

		List<TodoVO> voList = dao.selectAll();

		log.info("voList...............");

		log.info(voList);

		List<TodoDTO> dtoList = voList.stream().map(vo -> modelMapper.map(vo, TodoDTO.class))
				.collect(Collectors.toList());

		return dtoList;

	}

	// 조회기능은 GET방식으로 동작하고 '/todo/read?=tno=12' 와 같은 형태로 tno라는 파라미터를 쿼리 스트링으로 번호를
	// 전달하는 방식이다.
	// 이때 번호는 데이터베이스내에 존재하는 tno값중에 하나 여야한다.
	// TodoService에서는 TodoDTO 를 반환하고 이를 컨트롤러에서 HttpServletRequest 에 담아서 JSP에 출력하게된다.
	// TodoDAO의 selectOne()을 통해서 TodoVO 객체를 가져오고, ModelMapper를 이용해서 이를 TodoDTO로 변환한다.
	public TodoDTO get(Long tno) throws Exception {

		log.info("tno: " + tno);
		TodoVO todoVO = dao.selectOne(tno);
		TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);
		return todoDTO;
	}
	
	
	// 수정과 삭제는 특이하게도 하나의 화면에서 다른동작을 선택해서 이루어진다. 수정과 삭제 모두 POST 방식으로 처리되므로 화면에 두개의 <form> 태그를 작성해서 처리하거나
	// 자바스크립트를 이용해서 하나의 <form> 태그의 action 속성을 변경해서 처리할 수 있다.
	// remove()는 번호(tno)만을 이용한다.
	public void remove(Long tno) throws Exception {
		
		log.info("tno: " + tno);
		dao.deleteOne(tno);
	}
	
	// modify()의 경우에는 TodoDTO 타입을 파라미터로 이용한다.
	public void modify(TodoDTO todoDTO) throws Exception {
		
		log.info("todoDTO: " + todoDTO);
		
		TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
		
		dao.updateOne(todoVO);
	}

}
