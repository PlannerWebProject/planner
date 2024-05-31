package com.planiverse.event.data;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planiverse.event.repository.CalDAO;
//이 서블릿은 "/calendar/del.do" 경로로 요청이 들어오면 실행됨
@WebServlet("/calendar/del.do")
public class CalDel extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		 // 현재 세션을 가져옴
        HttpSession session = req.getSession();
        // 세션에서 사용자 ID를 가져옴
        String id = (String) session.getAttribute("id");

        // 삭제 결과를 저장할 변수
		int result = 0;
		 // 사용자 ID가 null이 아닌 경우에만 실행
        if (id != null) {
            // CalDAO 객체 생성
			CalDAO dao = new CalDAO();
			 // 요청 파라미터에서 캘린더 시퀀스 번호를 가져옴
			String temp = req.getParameter("calSeq");
			int calSeq = Integer.parseInt(temp);
			
			 // CalDAO를 사용하여 캘린더 삭제
			result = dao.delCal(calSeq);
			
		}

        // 응답 컨텐츠 타입을 application/json으로 설정
        resp.setContentType("application/json");
        // 응답 문자 인코딩을 UTF-8로 설정
        resp.setCharacterEncoding("UTF-8");
        // 응답 출력 스트림 생성
        PrintWriter writer = resp.getWriter();
        // 응답 JSON 데이터 작성
		writer.print("{");
		writer.print("\"result\":\"" + result + "\"");
		writer.print("}");
		// 출력 스트림 닫기
		writer.close();

	}
	
}