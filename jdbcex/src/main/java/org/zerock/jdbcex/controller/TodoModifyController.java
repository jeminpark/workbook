package org.zerock.jdbcex.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.jdbcex.dto.TodoDTO;
import org.zerock.jdbcex.service.TodoService;

import lombok.extern.log4j.Log4j2;


@WebServlet(name = "todoModifyController", value = "/todo/modify")
@Log4j2
public class TodoModifyController extends HttpServlet{
	
	private TodoService todoService = TodoService.INSTANCE;
	private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	
	// GET방식으로 tno파라미터를 이용해서 수정/삭제가 가능한 화면에 내용들을 보여주고, POST방식으로 수정작업을 처리 할 수 있도록 구성한다.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			Long tno = Long.parseLong(req.getParameter("tno"));
			TodoDTO todoDTO = todoService.get(tno);
			
			//데이터 담기
			req.setAttribute("dto", todoDTO);
			req.getRequestDispatcher("/WEB-INF/todo/modify.jsp").forward(req, resp);
			
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServletException("modify get... error");
		}
	}
	
	
	// form태그에서 전송된 title, finished 등을 이용해서 TodoDTO를 구성한다. 이때 주의해야하는 항목이 boolean타입으로 처리된 finished 이다.
	// 만들어진 TodoDTO는 TodoService 객체로 전달되고, 목록화면으로 다시 이동하면서 수정된 결과를 볼 수 있다.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String finishedStr = req.getParameter("finished");
		
		TodoDTO todoDTO = TodoDTO.builder()
				.tno(Long.parseLong(req.getParameter("tno")))
				.title(req.getParameter("title"))
				.dueDate(LocalDate.parse(req.getParameter("dueDate")))
				.finished(finishedStr != null && finishedStr.equals("on"))
				.build();
		
		log.info("/todo/modify POST....");
		log.info(todoDTO);
		try {
			todoService.modify(todoDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("/todo/list");
	}
}
