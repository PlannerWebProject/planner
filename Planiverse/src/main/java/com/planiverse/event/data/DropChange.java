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

@WebServlet("/event/dropchange.do")
public class DropChange extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		int result = 0;
		if (id != null) {

			String eventSeq = req.getParameter("eventSeq");
			String allDay = req.getParameter("allDay");
			String start = req.getParameter("start");
			String end = req.getParameter("end");

			EventDAO dao = new EventDAO();
			EventDTO dto = new EventDTO();

			dto.setEventSeq(eventSeq);
			System.out.println(allDay);
			if (allDay.equals("true")) {
				dto.setAllDay("y");
				dto.setStart(start.substring(0, 10));
				if (!end.equals("Invalid date")) {
					dto.setEnd(end.substring(0, 10));
				} else {
					dto.setEnd("");
				}
			} else {
				dto.setAllDay("n");
				dto.setStart(start);

				if (!end.equals("Invalid date")) {
					dto.setEnd(end);
				} else {
					dto.setEnd("");
				}
			}
			System.out.println(dto);// ---------------------나중에 지우기

			result = dao.dropchange(dto);
		}
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.print("{");
		writer.print("\"result\":" + result);
		writer.print("}");
		writer.close();

	}
}
