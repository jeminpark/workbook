package org.zerock.w1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public void init() {message = "Hello World! "; }        

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		
		//Hello
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>" + message + "</h1>");
		out.println("<h1>" + message + "</h1>");
		out.println("<h1>" + message + "</h1>");
		out.println("<h1>" + message + "</h1>");
		out.println("</body></html>");
				
	}
	
	public void destroy() {
		
	}

	

}
