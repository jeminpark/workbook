package org.zerock.jdbcex.domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TodoVO {
	
	private Long tno;
	
	private String title;
	
	private LocalDate dueDate;
	
	private boolean finished;

}
