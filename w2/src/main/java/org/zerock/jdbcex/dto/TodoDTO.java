package org.zerock.jdbcex.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
	
	private Long tno;
	
	private String title;
	
	private LocalDate dueDate;
	
	private boolean finished;
	
	// 현재 예제에서 TodoDTO의 내부구조를 보면 TodoVO와 완전히 같은 구조를 가지고 있다.
	// 하지만 적용된 어노테이션에 약간의 차이가 있다.
	
	// TodoDTO의 경우 @Data를 이용하고 있는데 @Data는 getter/setter/toString/equals/hashCode등을 모두 컴파일 할떄 생성해준다.
	// VO의 경우 getter만을 이용해서 읽기 전용으로 구성하는것과 차이가 있다. (가능하다면 VO는 주로 읽기 위주의 작업을 위해서만 사용한다.)
	
	

}
