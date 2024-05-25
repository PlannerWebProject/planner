package com.planiverse.event.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planiverse.DBUtil;
import com.planiverse.event.repository.CalDAO;

//CalAdd.java
@WebServlet("/calendar/add.do")
public class CalAdd extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		int calSeq = -1;
		String token = "";
		if (id != null) {
			String name = req.getParameter("name");
			int listSeq = Integer.parseInt(req.getParameter("listSeq"));

			CalDAO dao = new CalDAO();

			try {
				calSeq = dao.newCal(name, listSeq);

				if (calSeq != -1) {
					token = generateUniqueToken(calSeq, name);
					dao.inShare(id, calSeq, token);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.print("{");
		writer.print("\"calSeq\":" + calSeq + ",");
		writer.print("\"token\":\"" + token + "\"");
		writer.print("}");
		writer.close();
	}

	public static String generateUniqueToken(int calSeq, String name) throws NoSuchAlgorithmException {
		// Step 1: Input Validation
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or empty");
		}

		// Step 3: Sanitizing the Name
		String sanitizedName = name.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

		// Step 4: Combining the Number Sequence and Name
		String combinedStr = calSeq + sanitizedName;

		// Step 5: Generating a Hash
		String uniqueToken = hashString(combinedStr);

		// Step 6: Final Token Generation
		return uniqueToken;
	}

	private static String hashString(String input) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashBytes = digest.digest(input.getBytes());
		StringBuilder hexString = new StringBuilder(2 * hashBytes.length);
		for (byte b : hashBytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
