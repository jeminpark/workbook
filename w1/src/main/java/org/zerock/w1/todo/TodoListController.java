package org.zerock.w1.todo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.w1.todo.dto.TodoDTO;
import org.zerock.w1.todo.service.TodoService;

@WebServlet(name = "todoListController", urlPatterns = "/todo/list" )
public class TodoListController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("/todo/list");
		
		List<TodoDTO> dtoList = TodoService.INSTANCE.getList();
		
		req.setAttribute("list", dtoList);

		req.getRequestDispatcher("/WEB-INF/todo/list.jsp")
			.forward(req, resp);
		
	}
}
