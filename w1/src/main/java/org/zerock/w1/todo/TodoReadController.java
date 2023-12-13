package org.zerock.w1.todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.w1.todo.dto.TodoDTO;
import org.zerock.w1.todo.service.TodoService;

@WebServlet(name = "todoReadController", urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("/todo/read");
		
		// /todo/read?tno=123
		Long tno = Long.parseLong(req.getParameter("tno"));
		
		TodoDTO dto = TodoService.INSTANCE.get(tno);
		
		req.setAttribute("dto", dto);
		
		req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp);
		
		//.forward(req, resp) : redirect와 달리 주소가 달라지지않는다. 사용자요청에의해 컨테이너에서 생성된 req, resp를 다른 리소스로 넘겨주는역할
		
		
	}
}
