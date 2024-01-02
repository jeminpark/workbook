package org.zerock.jdbcex.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter  // 객체 조회
@Builder  // 객체 생성시에 빌더 패턴 사용
@ToString 

// VO의 경우 getter만을 사용하고 있는데 이를 처리하기위해 ModelMapper설정을 변경해서 사용하도록 한다.
// ModelMapper를 이용할 떄는 대상 클래스의 생성자를 이용할 수 있도록 
// TodoVO 에 생성자 관련 어노테이션을 추가한다.
@AllArgsConstructor    	// 모든 필드값이 필요한 생성자 생성
@NoArgsConstructor		// 파라미터가 필요없는 생성자 생성
public class TodoVO {

	private Long tno;
	
	private String title;
	
	private LocalDate dueDate;
	
	private boolean finished;
	
}

