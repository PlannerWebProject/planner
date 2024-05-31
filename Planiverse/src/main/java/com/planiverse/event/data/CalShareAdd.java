package com.planiverse.event.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planiverse.event.model.CalDTO;
import com.planiverse.event.repository.CalDAO;
//이 서블릿은 "/calendar/shareadd.do" 경로로 요청이 들어오면 실행됨
@WebServlet("/calendar/shareadd.do")
public class CalShareAdd extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  // 현재 세션을 가져옴
        HttpSession session = req.getSession();
        // 세션에서 사용자 ID를 가져옴
        String id = (String) session.getAttribute("id");

        // 토큰을 초기화
        String token = "";
        // 캘린더 시퀀스 번호를 초기화
        int calSeq = -1;
        // CalDTO 객체 생성
        CalDTO dto = new CalDTO();

        // 사용자 ID가 null이 아닌 경우에만 실행
        if (id != null) {
            // 요청 파라미터에서 토큰 값을 가져옴
			token = req.getParameter("token");
			// CalDAO 객체 생성
			CalDAO dao = new CalDAO();

			try {
				 // CalDAO를 사용하여 토큰으로 공유 정보 가져오기
				dto = dao.getShare(id, token);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        // CalDAO 객체 생성
        CalDAO calDao = new CalDAO();
        // 사용자가 공유받은 캘린더 목록 가져오기
        ArrayList<CalDTO> shareList = calDao.shareList(id);
        // 공유 캘린더 목록을 세션에 저장
        session.setAttribute("shareCalDTO", shareList);

        // 응답 컨텐츠 타입을 application/json으로 설정
        resp.setContentType("application/json");
        // 응답 문자 인코딩을 UTF-8로 설정
        resp.setCharacterEncoding("UTF-8");
        // 응답 출력 스트림 생성
        PrintWriter writer = resp.getWriter();
        // 출력 스트림 닫기
        writer.close();
	}
	
}
