package com.planiverse.event.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.planiverse.event.model.EventDTO;
import com.planiverse.event.repository.EventDAO;


@WebServlet("/listevent.do")
public class ListEvent extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		EventDAO dao = new EventDAO();

		ArrayList<EventDTO> list = dao.list();

		JSONArray arr = new JSONArray();
		for (EventDTO dto : list) {
			JSONObject obj = new JSONObject();
			obj.put("eventSeq", dto.getEventSeq());
			obj.put("title", dto.getTitle());
			obj.put("allDay", dto.getAllDay());
			obj.put("start", dto.getStart());
			obj.put("end", dto.getEnd());
			obj.put("loc", dto.getLoc());
			obj.put("content", dto.getContent());
			obj.put("eDel", dto.getEDel());
			obj.put("googleCalendarId", dto.getGoogleCalendarId());
			obj.put("className", dto.getClassName());
			obj.put("colSeq", dto.getColSeq());
			obj.put("calSeq", dto.getCalSeq());

			arr.add(obj);
		}

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		PrintWriter writer = resp.getWriter();

		writer.print(arr);
		writer.close();

	}
}
