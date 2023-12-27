package org.zerock.jdbcex.domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter  // 객체 조회
@Builder  // 객체 생성시에 빌더 패턴 사용
@ToString 
public class TodoVO {

	private Long tno;
	
	private String title;
	
	private LocalDate dueDate;
	
	private boolean finished;
	
}
