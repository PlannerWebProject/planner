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

import com.planiverse.event.model.EventDTO;
import com.planiverse.event.repository.EventDAO;
import com.planiverse.event.repository.EventDAOImpl;

@WebServlet("/event/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		if (id != null) {
			EventDAO dao = new EventDAOImpl();

			ArrayList<EventDTO> list = dao.list(id);
			System.out.println(id);

			JSONArray arr = new JSONArray();
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

			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			PrintWriter writer = resp.getWriter();

			writer.print(arr);
			writer.close();
		}
	}
}
