package com.planiverse.user;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planiverse.event.data.CalAdd;
import com.planiverse.event.model.CalDTO;
import com.planiverse.event.repository.CalDAO;
import com.planiverse.user.repository.UserDAO;
//소셜 로그인을 처리하는 서블릿
@WebServlet("/user/socialLogin.do")
public class socialLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 파라미터에서 이메일과 이름 가져오기
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		// 로그 출력
		System.out.println(email);
		System.out.println(name);
	    // UserDAO 객체 생성
		UserDAO dao = new UserDAO();
		// UserDAO를 사용하여 소셜 로그인 처리
		int result = dao.socialLogin(email, name);
		// 로그 출력
		System.out.println("socialLogin: "+result);
		
		
		// 소셜 로그인 성공 시
		if(result == 1) {
			// 세션에 이메일 저장 (인증 티켓)
			HttpSession session = req.getSession();

			session.setAttribute("id", email);
			// CalDAO 객체 생성
			CalDAO calDao = new CalDAO();
			// 사용자의 캘린더 목록 조회
			ArrayList<CalDTO> list = calDao.list(email);
			session.setAttribute("calDTO", list);
			// 사용자의 공유 캘린더 목록 조회
			ArrayList<CalDTO> shareList = calDao.shareList(email);
			session.setAttribute("shareCalDTO", shareList);
			 // 메인 페이지로 리다이렉트
			resp.sendRedirect("/plan/planiverse.do");
			// 소셜 로그인 실패 시 (신규 사용자)
		}else if (result == 0) {
			// 세션 생성
			HttpSession session = req.getSession();
			// UserDAO를 사용하여 사용자 프로필 추가
			int addprofile = dao.addprofile(email, name);
			System.out.println("addprofile: "+addprofile);
			// 프로필 추가 성공 시 세션에 이메일 저장
			if(addprofile == 1) {

				session.setAttribute("id", email);
			}
			// CalDAO 객체 생성
			CalDAO calDao = new CalDAO();
			// 새 캘린더 생성
			int calSeq = calDao.newCal("기본", calDao.newCalList(email));
			// 캘린더 생성 성공 시
			if (calSeq != -1) {
				String token;
				// 고유 토큰 생성
				try {
					token = CalAdd.generateUniqueToken(calSeq, name);
					calDao.inShare(email, calSeq, token);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
			// 사용자의 캘린더 목록 조회
			ArrayList<CalDTO> list = calDao.list(email);
			session.setAttribute("calDTO", list);
			 // 사용자가 공유받은 캘린더 목록 조회
			ArrayList<CalDTO> shareList = calDao.shareList(email);
			session.setAttribute("shareCalDTO", shareList);
			// 메인 페이지로 리다이렉트
            resp.sendRedirect("/plan/planiverse.do");
        } else { // 소셜 로그인 실패
            // 로그인 실패 결과를 request 속성에 저장
			req.setAttribute("result", 0);
			System.out.println("로그인 실패");
		}
	}
}
