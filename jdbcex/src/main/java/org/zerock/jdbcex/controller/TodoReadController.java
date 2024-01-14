package org.zerock.jdbcex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

import lombok.extern.log4j.Log4j2;

// doGet()을 이용하고, '/WEB-INF/todo/read.jsp'로 TodoDTO를 전달하도록 구성한다.

@WebServlet(name = "todoReadController", value = "/todo/read")
@Log4j2
public class TodoReadController extends HttpServlet{
	
	private TodoService todoService = TodoService.INSTANCE;
	
	
	// 'dto' 라는 이름으로 TodoDTO를 담는다.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			Long tno = Long.parseLong(req.getParameter("tno"));
			
			TodoDTO todoDTO = todoService.get(tno);
			
			// 데이터담기
			req.setAttribute("dto", todoDTO);
			
			req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServletException("read error");
		}
	}
	
	

}
