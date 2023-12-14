package org.zerock.w1.calc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "calcController", urlPatterns = "/calc/makeResult")
public class CalcController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//HttpServlet의 doPost 메소드를 호출할때 파라미터로 HttpServletResonse 객체를 가져온다.
		
		String num1 = req.getParameter("num1");
		String num2 = req.getParameter("num2");
		
		System.out.printf(" num1: %s", num1);
		System.out.printf(" num2: %s", num2);
		
		resp.sendRedirect("/index");
		// HttpServletResponse 객체의 sendRedirect 메소드에 지정한경로를 파라미터로 넣어 POST처리 이후 호출 시키도록한다.
	}
	
}
