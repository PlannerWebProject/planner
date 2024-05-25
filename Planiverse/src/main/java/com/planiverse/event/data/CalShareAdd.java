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

@WebServlet("/calendar/shareadd.do")
public class CalShareAdd extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		String token = "";
		int calSeq = -1;
		CalDTO dto = new CalDTO();
		if (id != null) {
			token = req.getParameter("token");

			CalDAO dao = new CalDAO();

			try {
				dto = dao.getShare(id, token);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		CalDAO calDao = new CalDAO();
		ArrayList<CalDTO> shareList = calDao.shareList(id);
		session.setAttribute("shareCalDTO", shareList);

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
	    writer.close();
	}
	
}
