package com.planiverse.event.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.planiverse.event.model.CalDTO;
import com.planiverse.event.model.EventDTO;
import com.planiverse.event.repository.EventDAO;
import com.planiverse.event.repository.EventDAOImpl;
//이 서블릿은 "/event/list.do" 경로로 요청이 들어오면 실행됨
@WebServlet("/event/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  // 현재 세션을 가져옴
        HttpSession session = req.getSession();
        // 세션에서 사용자 ID를 가져옴
        String id = (String) session.getAttribute("id");

        // 사용자 ID가 null이 아닌 경우에만 실행
		if (id != null) {
			 // EventDAO 객체 생성
			EventDAO dao = new EventDAOImpl();
			// 사용자의 이벤트 목록 가져오기
			ArrayList<EventDTO> list = dao.list(id);
			System.out.println(id);
			 // JSON 배열 생성
			JSONArray arr = new JSONArray();
			// 사용자의 이벤트 목록을 JSON 객체로 변환하여 배열에 추가
			for (EventDTO dto : list) {
				JSONObject obj = new JSONObject();
				String start = "20" + dto.getStart().replace("/", "-");
				String end = "20" + dto.getEnd().replace("/", "-");

				obj.put("eventSeq", dto.getEventSeq());
				obj.put("title", dto.getTitle());
				obj.put("allDay", dto.getAllDay());
				obj.put("start", start);
				obj.put("end", end);
				obj.put("loc", dto.getLoc());
				obj.put("content", dto.getContent());
				obj.put("color", dto.getColor());
				obj.put("calSeq", dto.getCalSeq());

				arr.add(obj);
			}
			// 세션에서 공유받은 캘린더 목록 가져오기
			ArrayList<CalDTO> sharelist = (ArrayList<CalDTO>) session.getAttribute("shareCalDTO");
			 // 공유받은 캘린더의 이벤트 목록도 JSON 객체로 변환하여 배열에 추가
			for (CalDTO calDto : sharelist) {
				list = dao.shareList(calDto.getCalSeq());
				for (EventDTO dto : list) {
					JSONObject obj = new JSONObject();
					String start = "20" + dto.getStart().replace("/", "-");
					String end = "20" + dto.getEnd().replace("/", "-");

					obj.put("eventSeq", dto.getEventSeq());
					obj.put("title", dto.getTitle());
					obj.put("allDay", dto.getAllDay());
					obj.put("start", start);
					obj.put("end", end);
					obj.put("loc", dto.getLoc());
					obj.put("content", dto.getContent());
					obj.put("color", dto.getColor());
					obj.put("calSeq", dto.getCalSeq());

					arr.add(obj);
				}
			}
	        // 응답 컨텐츠 타입을 application/json으로 설정
            resp.setContentType("application/json");
            // 응답 문자 인코딩을 UTF-8로 설정
            resp.setCharacterEncoding("UTF-8");
            // 응답 출력 스트림 생성
            PrintWriter writer = resp.getWriter();
            // 응답 JSON 데이터 작성
            writer.print(arr);
            // 출력 스트림 닫기
			writer.close();
		}
	}
}
