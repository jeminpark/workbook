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
		Long tno = Long.parseLong(req.getParameter("tno")); //서블릿 요청 객체의 파라미터는 문자열타입으로 반환된다. long타입으로 저장
		
		TodoDTO dto = TodoService.INSTANCE.get(tno); // long타입 tno를 파라미터로 TodoService에서 TodoDTO 객체 가져오기
		
		req.setAttribute("dto", dto); // 서블릿 요청의 dto 속성을 TodoService에서 가져온 dto로 설정
		
		req.getRequestDispatcher("/WEB-INF/todo/read.jsp").forward(req, resp); // read.jsp 경로로 요청을 넘겨서 수행제어를 완전히 넘겨서 해당 자원의 수행결과가 대신 클라이언트로 응답되게함.
		
		//.forward(req, resp) : redirect와 달리 주소가 달라지지않는다. 사용자요청에의해 컨테이너에서 생성된 req, resp를 다른 리소스로 넘겨주는역할
		
		
	}
}
