package com.planiverse.event.data;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planiverse.event.model.EventDTO;
import com.planiverse.event.repository.EventDAO;
import com.planiverse.event.repository.EventDAOImpl;
//이 서블릿은 "/event/dropchange.do" 경로로 요청이 들어오면 실행됨
@WebServlet("/event/dropchange.do")
public class DropChange extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 현재 세션을 가져옴
        HttpSession session = req.getSession();
        // 세션에서 사용자 ID를 가져옴
		String id = (String) session.getAttribute("id");
		// 수정 결과를 저장할 변수
		int result = 0;
		// 사용자 ID가 null이 아닌 경우에만 실행
		if (id != null) {
			 // 요청 파라미터에서 값을 가져옴
			String eventSeq = req.getParameter("eventSeq");
			String allDay = req.getParameter("allDay");
			String start = req.getParameter("start");
			String end = req.getParameter("end");

			 // EventDAO 객체 생성
            EventDAO dao = new EventDAOImpl();
            // EventDTO 객체 생성
			EventDTO dto = new EventDTO();
			 // EventDTO 객체에 값 설정
			dto.setEventSeq(eventSeq);
			System.out.println(allDay);
			// allDay가 true인 경우
			if (allDay.equals("true")) {
				dto.setAllDay("y");
				dto.setStart(start.substring(0, 10));
				if (!end.equals("Invalid date")) {
					dto.setEnd(end.substring(0, 10));
				} else {
					dto.setEnd("");
				}
			} else { // allDay가 false인 경우
				dto.setAllDay("n");
				dto.setStart(start);

				if (!end.equals("Invalid date")) {
					dto.setEnd(end);
				} else {
					dto.setEnd("");
				}
			}
			System.out.println(dto);// ---------------------나중에 지우기
			 // EventDAO를 사용하여 이벤트 정보 수정
			result = dao.dropchange(dto);
		}
		 // 응답 컨텐츠 타입을 application/json으로 설정
        resp.setContentType("application/json");
        // 응답 출력 스트림 생성
        PrintWriter writer = resp.getWriter();
        // 응답 JSON 데이터 작성
        writer.print("{");
        writer.print("\"result\":" + result);
        writer.print("}");
        // 출력 스트림 닫기
		writer.close();

	}
}
