package com.planiverse.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planiverse.user.repository.UserDAO;

@WebServlet("/user/idcheck.do")
public class IdCheck extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("id");
		
		UserDAO dao = new UserDAO();
		
		int result = dao.idcheck(id);
		
		req.setAttribute("result", result);

	}

}