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
//사용자 회원가입을 처리하는 서블릿
@WebServlet("/user/register.do")
public class Register extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 회원가입 JSP 페이지로 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/register.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 요청 파라미터 인코딩을 UTF-8로 설정
			req.setCharacterEncoding("UTF-8");
			// 요청 파라미터에서 ID, 비밀번호, 이름 가져오기
			String id = req.getParameter("id");
			String pw = req.getParameter("pw");
			String name = req.getParameter("name");
			// UserDTO 객체 생성 및 값 설정
			UserDTO dto = new UserDTO();
			
			dto.setId(id);
			dto.setPw(pw);
			dto.setName(name);
			// UserDAO 객체 생성
			UserDAO dao = new UserDAO();
			// UserDAO를 사용하여 회원가입 처리
			int result = dao.register(dto);
			
			// 회원가입 성공 시
			if(result ==1) {
				// CalDAO 객체 생성
				CalDAO calDao = new CalDAO();
				// 새 캘린더 생성
				int calSeq = calDao.newCal("기본" , calDao.newCalList(id));
				// 캘린더 생성 성공 시
				if (calSeq != -1) {
					String token;
					 // 고유 토큰 생성
					try {
						token = CalAdd.generateUniqueToken(calSeq, name);
						calDao.inShare(id, calSeq, token);
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
				}
				// 메인 페이지로 리다이렉트
				resp.sendRedirect("/plan/planiverse.do");
			} 
			
		} catch (Exception e) {
			System.out.println("Register.doPost");
			e.printStackTrace();
		}
		
	}


}
