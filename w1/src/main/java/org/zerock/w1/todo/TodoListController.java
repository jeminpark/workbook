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
		// List<TodoDTO> 객체를 보관
		// 보관된 데이터는 JSP에서 EL로 간단하게 확인 가능

		req.getRequestDispatcher("/WEB-INF/todo/list.jsp")
			.forward(req, resp);
	
		
		//getRequestDispatcher를 사용하여 해당URL 값으로 다시넘김 forward를 통해 req, res를 같이 보냄
		
		//getRequestDispatcher : 요청재지정
		// 해당 객체에서 제공하는 메소드를 이용하여 요청재지정을 할때는 재지정하는 자원이 반드시 현재자원과 동일한 웹어플리케이션에 있어야한다.
		//재지정 메소드 
		//forward(req, res) : 요청을 다른자원으로 넘긴다.
		
		// Requestdispatcher 객체의 forward()는 클라이언트의 요청으로 생성되는request response 객체를 다른자원에 전달하고 수행제어를 완전하게 넘겨서 다른자원의 수행결과를 클라이언트로 응답하도록한다.
		
	}
}
