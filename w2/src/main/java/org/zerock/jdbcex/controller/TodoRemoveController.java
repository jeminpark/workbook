package org.zerock.jdbcex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.jdbcex.service.TodoService;

import lombok.extern.log4j.Log4j2;

@WebServlet( name = "todoRemoveController", value = "/todo/remove")
@Log4j2
public class TodoRemoveController extends HttpServlet{
	
	private TodoService todoService = TodoService.INSTANCE;
	
	// 특정한 번호를 이용해서 삭제하고 httpServletResponse 의 sendRedirect를 이용해서 /todo/list로 이동하도록 처리한다.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long tno = Long.parseLong(req.getParameter("tno"));
		log.info("tno: "+ tno);
		
		try {
			todoService.remove(tno);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServletException("read error");
		}
		
		resp.sendRedirect("/todo/list");
	}
}
