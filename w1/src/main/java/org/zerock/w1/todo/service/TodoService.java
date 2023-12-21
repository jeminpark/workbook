package org.zerock.w1.todo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.zerock.w1.todo.dto.TodoDTO;

public enum TodoService { 
	// enum 타입으로 클래스를 작성하는 경우 가장 큰장점은 정해진 수만큼만 객체를 생성할수 있다는점.

	INSTANCE;
	// 객체의 개수를 결정하는 부분으로 한개만 지정되어 있으므로 하나의 객체만을 생성해서 사용하게 된다.
	// 객체를 사용할때는 TodoService.INSTANCE오 ㅏ같이 간단하게 사용가능.
	// TodoService.INSTANCE는 항상 하나의 객체만을 가리키게 되는데 이처럼 객체를 하나만 생성해서 사용하는 패턴을 '싱글톤 패턴'이라고 한다.
	
	public void register(TodoDTO todoDTO) {
		
		System.out.println("DEBUG......." + todoDTO);
		// 서비스 객체는 절대로 출력문에 담으면 안되지만 디버깅을 위한 용도로 첨가
		// 새로운 TodoDTO 객체를 받아서 확인 할수 있는것을 목표로 작성

	}
	
	public List<TodoDTO> getList() {
		// 10개의 TodoDTO 객체를 만들어서 반환하도록 구성.
		
		List<TodoDTO> todoDTOS = IntStream.range(0, 10).mapToObj(i -> {
			TodoDTO dto = new TodoDTO();
			dto.setTno((long)i);
			dto.setTitle("Todo.. " + i);
			dto.setDueDate(LocalDate.now());
			
			return dto;
			
		}).collect(Collectors.toList());
		
		return todoDTOS;
	}
	
	public TodoDTO get(Long tno) {
		// 특정한 번호를 파라미터로 가져오면 객체를 만듬
		
		TodoDTO dto = new TodoDTO();
		dto.setTno(tno);
		dto.setTitle("Sample Todo");
		dto.setDueDate(LocalDate.now());
		dto.setFinished(true);
		
		return dto;
	}
}
