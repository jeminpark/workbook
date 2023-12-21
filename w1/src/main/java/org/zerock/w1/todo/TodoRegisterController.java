package org.zerock.w1.todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( name = "todoRegisterController", urlPatterns = "/todo/register")
public class TodoRegisterController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/todo/register.jsp");
		System.out.println("입력 화면을 볼 수 있도록 구성");
		
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("입력을 처리하고 목록 페이지로 이동");
		
		// 브라우저가 호출해야 하는 주소
		resp.sendRedirect("/todo/list");
		
		//POST방식의 호출을 '새로고침'하는 경우 양식 다시 제출 경고창이 뜨지만 해결책이 아니므로 
		// 이를 막기 위해서 PRG패턴을 적용하도록 sendRedirec()가 필요하다.
		//sendRedirect()는 브라우저를 아예 다른 주소로 이동시키기 때문에 사용자가 반복적인 POST요청을 보내는것을 막을수 있다.
	}
}
