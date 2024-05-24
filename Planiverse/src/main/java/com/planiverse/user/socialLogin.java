package com.planiverse.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planiverse.event.model.CalDTO;
import com.planiverse.event.repository.CalDAO;
import com.planiverse.user.repository.UserDAO;

@WebServlet("/user/socialLogin.do")
public class socialLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");

		System.out.println(email);
		System.out.println(name);

		UserDAO dao = new UserDAO();
		int result = dao.socialLogin(email, name);
		System.out.println("socialLogin: "+result);

		if(result == 1) {
			HttpSession session = req.getSession();

			session.setAttribute("id", email);
			CalDAO calDao = new CalDAO();

			ArrayList<CalDTO> list = calDao.list(email);
			session.setAttribute("calDTO", list);
			

			resp.sendRedirect("/plan/planiverse.do");
		}else if (result == 0) {
			
			int addprofile = dao.addprofile(email, name);
			System.out.println("addprofile: "+addprofile);
			if(addprofile == 1) {
				HttpSession session = req.getSession();

				session.setAttribute("id", email);
			}
			
			resp.sendRedirect("/plan/planiverse.do");
		} else {
			req.setAttribute("result", 0);
			System.out.println("로그인 실패");
		}
	}
}
