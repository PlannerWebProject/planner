package com.planiverse.event.data;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planiverse.event.model.EventDTO;
import com.planiverse.event.repository.EventDAO;

@WebServlet("/event/change.do")
public class Change extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		req.setCharacterEncoding("UTF-8");
		String eventSeq = req.getParameter("eventSeq");
		String allDay = req.getParameter("allDay");
		String title = req.getParameter("title");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		String colSeq = req.getParameter("color");
		String loc = req.getParameter("loc");
		String content = req.getParameter("content");
		
		EventDAO dao = new EventDAO();
		EventDTO dto = new EventDTO();
		
		dto.setEventSeq(eventSeq);
		dto.setTitle(title);
		dto.setColSeq(colSeq);
		dto.setLoc(loc);
		dto.setContent(content);
		
		if(allDay.equals("true")) {
			dto.setAllDay("y");
			dto.setStart(start.substring(0,10));
			dto.setEnd(end.substring(0,10));
		} else {
			dto.setAllDay("n");
			dto.setStart(start);
			
			if(!end.equals("Invalid date")) {
				dto.setEnd(end);
			} else {
				dto.setEnd("");
			}
		}
		
		System.out.println(dto);
		
		int result = dao.change(dto);

		if(result==1) {
			resp.sendRedirect("/plan/planiverse.do");
		} else {
			/*
			 * resp.setCharacterEncoding("UTF-8");
			 * 
			 * PrintWriter writer = resp.getWriter();
			 * 
			 * writer.close();
			 */
			System.out.println("에러");
		}

	}
}
