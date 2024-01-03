package org.zerock.jdbcex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.jdbcex.service.TodoService;

import lombok.extern.log4j.Log4j2;

@WebServlet( name = "todoListController", value = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet{
	
	private TodoService todoService = TodoService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		log.info("todo list.............");
	}

}
