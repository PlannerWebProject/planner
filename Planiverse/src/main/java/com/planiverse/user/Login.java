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

@WebServlet("/user/login.do")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String loginId = req.getParameter("login-form-username");
		String loginPw = req.getParameter("login-form-password");

		System.out.println(loginId);
		System.out.println(loginPw);

		UserDAO dao = new UserDAO();
		boolean result = dao.login(loginId, loginPw);
		System.out.println(result);

		if (result) {
			System.out.println("로그인 성공");
			HttpSession session = req.getSession();

			session.setAttribute("id", loginId); // 인증 티켓
			CalDAO calDao = new CalDAO();

			ArrayList<CalDTO> list = calDao.list(loginId);
			session.setAttribute("calDTO", list);

			resp.sendRedirect("/plan/planiverse.do");

		} else {
			req.setAttribute("result", 0);
			System.out.println("로그인 실패");
		}
	}
}
