package org.zerock.w1;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "sampleServlet", urlPatterns = "/sample")
public class SampleServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet...."+ this);
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy.........................");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init(ServletConfig).................");
		
		//브라우저를 통해 SampleServlet의 경로 /sample 호출시 init() 메소드와 doGet()이 실행되는것을 확인할수있다.
//		 첫시작은 init(ServletConfig).................
//		doGet....org.zerock.w1.SampleServlet@6fce626a
		
//		이상태에서 /sample을 여러번 호출하면 init..부분은 더이상 출력되지않고 doGet만 출력된다.
//		doGet의 this값 결과로 출력되는 @이하의 값이 모두 같은데 이것은 동일하게 하나의 객체로 처리된다는 의미이다.
		
//		톰캣 종료시 destroy()가 호출된다
//		destroy.........................
		
//		결과에서 알수 있듯이 init() 과 destroy()는 한번씩만 호출, doGet()/doPost()는 동일한객체를 이용해 여러번 호출한다.
	}
}
