package com.planiverse;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	req.setAttribute("path", "/planiverse");
		
	RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/index.jsp");
	dispatcher.forward(req, resp);
	}
}
