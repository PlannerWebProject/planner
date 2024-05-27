package com.planiverse.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planiverse.event.data.CalAdd;
import com.planiverse.event.repository.CalDAO;
import com.planiverse.user.model.UserDTO;
import com.planiverse.user.repository.UserDAO;

@WebServlet("/user/register.do")
public class Register extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Register.java
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/register.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			String name = req.getParameter("name");
			
			UserDTO dto = new UserDTO();
			
			dto.setId(id);
			dto.setPw(pw);
			dto.setName(name);
			
			UserDAO dao = new UserDAO();
			
			int result = dao.register(dto);
			
			
			if(result ==1) {
				CalDAO calDao = new CalDAO();
				int calSeq = calDao.newCal("기본" , calDao.newCalList(id));
				if (calSeq != -1) {
					String token;
					try {
						token = CalAdd.generateUniqueToken(calSeq, name);
						calDao.inShare(id, calSeq, token);
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
				}
				
				resp.sendRedirect("/plan/planiverse.do");
			} 
			
		} catch (Exception e) {
			System.out.println("Register.doPost");
			e.printStackTrace();
		}
		
	}


}
