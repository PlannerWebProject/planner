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
//사용자 로그인을 처리하는 서블릿
@WebServlet("/user/login.do")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 파라미터에서 로그인 ID와 비밀번호 가져오기
		String loginId = req.getParameter("login-form-username");
		String loginPw = req.getParameter("login-form-password");
		// 로그 출력
		System.out.println(loginId);
		System.out.println(loginPw);
		// UserDAO 객체 생성
		UserDAO dao = new UserDAO();
		// UserDAO를 사용하여 로그인 처리
		boolean result = dao.login(loginId, loginPw);
		// 로그 출력
		System.out.println(result);
		// 로그인 성공 시
		if (result) {
			System.out.println("로그인 성공");
			HttpSession session = req.getSession();
			// 세션에 사용자 ID 저장 (인증 티켓)
			session.setAttribute("id", loginId); 
			 // CalDAO 객체 생성
			CalDAO calDao = new CalDAO();
			// 사용자의 캘린더 목록 조회
			ArrayList<CalDTO> list = calDao.list(loginId);
			session.setAttribute("calDTO", list);
			 // 사용자가 공유받은 캘린더 목록 조회
			ArrayList<CalDTO> shareList = calDao.shareList(loginId);
			session.setAttribute("shareCalDTO", shareList);
			// 메인 페이지로 리다이렉트
			resp.sendRedirect("/plan/planiverse.do");

		} else {// 로그인 실패 시
			// 로그인 실패 결과를 request 속성에 저장
			req.setAttribute("result", 0);
			System.out.println("로그인 실패");
		}
	}
}
