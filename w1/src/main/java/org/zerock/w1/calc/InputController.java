package org.zerock.w1.calc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "inputController", urlPatterns = "/calc/input") //InputController는 @WebServlet으로 urlPatterns 속성을 지정해서 처리해야하는 경로 지정
public class InputController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// 부모클래스인 HttpServlet의 doGet을 오버라이드 하고 GET 방식으로 들어오는 요청에 대해서만 처리하도록 구성.
		System.out.println("InputController...doGet...");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/calc/input.jsp");
		// 서블릿에 전달된 요청을 다른쪽으로 전달 혹은 배포 하는 역할을 하는 객체 InputController의 핵심코드
		// RequestDispatcher를 구성하여 이용하면 InputController는 마치 버스정류장처럼 '/WEB-INF/calc/input.jsp' 라는 목적지로 가는 중간 경유지가 된다.
		
		dispatcher.forward(req, resp);
	}
	
}
