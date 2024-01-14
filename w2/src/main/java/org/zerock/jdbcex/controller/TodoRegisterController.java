package org.zerock.jdbcex.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.jdbcex.domain.TodoVO;
import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

import lombok.extern.log4j.Log4j2;

@WebServlet(name = "todoRegisterController", value = "/todo/register")
@Log4j2
public class TodoRegisterController extends HttpServlet {
	
	private TodoService todoService = TodoService.INSTANCE;
	private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
	// MM패턴 mm오타 주의 input type=date 태그로 값 받아와서 doPost 처리시에 패턴이 MM이 아닌 mm인경우 Minute로 읽어 parse 불가
	
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.info("/todo/register GET.......");
		req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
	}
	
	// doPost() 에는 HttpServletRequest 의 getParameter()로 title/dueDate를 이용해서 TodoDTO를 구성하고 최종적으로 TodoService의 register()를 호출한다.
	// 정상적으로 등록된 후에는 GET방식으로 redirec(/todo/list) 하기 때문에 등록하자마자 바로 목록화면으로 이동하게 된다.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TodoDTO todoDTO = TodoDTO.builder()
				.title(req.getParameter("title"))
				.dueDate(LocalDate.parse(req.getParameter("dueDate"), DATEFORMATTER))
				.build();
		
		log.info("/todo/register POST....");
		log.info(todoDTO);
		
		try {
			todoService.register(todoDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("/todo/list");
	}	
	
}
