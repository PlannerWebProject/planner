package com.planiverse.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planiverse.user.repository.UserDAO;
//사용자 ID 중복 체크를 처리하는 서블릿
@WebServlet("/user/idcheck.do")
public class IdCheck extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 파라미터 인코딩을 UTF-8로 설정
		req.setCharacterEncoding("UTF-8");
		// 요청 파라미터에서 ID 값 가져오기
		String id = req.getParameter("id");
		// UserDAO 객체 생성
		UserDAO dao = new UserDAO();
		// UserDAO를 사용하여 ID 중복 체크
		int result = dao.idcheck(id);
		// 중복 체크 결과를 request 속성에 저장
		req.setAttribute("result", result);

	}

}