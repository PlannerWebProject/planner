package com.planiverse.event.data;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planiverse.event.model.EventDTO;
import com.planiverse.event.repository.EventDAO;

@WebServlet("/event/dropchange.do")
public class DropChange extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String eventSeq = req.getParameter("eventSeq");
		String allDay = req.getParameter("allDay");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		
		EventDAO dao = new EventDAO();
		EventDTO dto = new EventDTO();
		
		dto.setEventSeq(eventSeq);
		dto.setAllDay(allDay);
		
		if(allDay.equals("true")) {
			dto.setStart(start.substring(0,10));
			dto.setEnd(end.substring(0,10));
		} else {
			dto.setStart(start);
			
			if(!end.equals("Invalid date")) {
				dto.setEnd(end);
			} else {
				dto.setEnd("");
			}
		}
		System.out.println(dto);
		
		int result = dao.dropchange(dto);

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
