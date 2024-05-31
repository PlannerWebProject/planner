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
//이 서블릿은 "/calendar/add.do" 경로로 요청이 들어오면 실행됨
@WebServlet("/calendar/add.do")
public class CalAdd extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 현재 세션을 가져옴 
		HttpSession session = req.getSession();
		// 세션에서 사용자 ID를 가져옴
		String id = (String) session.getAttribute("id");
		 // 캘린더 시퀀스 번호를 초기화
        int calSeq = -1;
        // 토큰을 초기화
		String token = "";
		// 사용자 ID가 null이 아닌 경우에만 실행
		if (id != null) {
			 // 요청 파라미터에서 값을 가져옴
			String name = req.getParameter("name");
			int listSeq = Integer.parseInt(req.getParameter("listSeq"));
			// CalDAO 객체 생성
			CalDAO dao = new CalDAO();

			try {
				System.out.println("newCal");
				System.out.println("name : " + name);
				System.out.println("listSeq : " + listSeq);
				// CalDAO를 사용하여 새로운 캘린더 추가
				calSeq = dao.newCal(name, listSeq);
				 // 캘린더 추가에 성공한 경우
				if (calSeq != -1) {
					// 고유 토큰 생성
					token = generateUniqueToken(calSeq, name);
					// 사용자와 캘린더 공유 정보 추가
					dao.inShare(id, calSeq, token);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 응답 컨텐츠 타입을 application/json으로 설정
        resp.setContentType("application/json");
        // 응답 문자 인코딩을 UTF-8로 설정
        resp.setCharacterEncoding("UTF-8");
        // 응답 출력 스트림 생성
		PrintWriter writer = resp.getWriter();
		 // 응답 JSON 데이터 작성
		writer.print("{");
		writer.print("\"calSeq\":" + calSeq + ",");
		writer.print("\"token\":\"" + token + "\"");
		writer.print("}");
		 // 출력 스트림 닫기
		writer.close();
	}
	// 고유 토큰을 생성하는 메서드
	public static String generateUniqueToken(int calSeq, String name) throws NoSuchAlgorithmException {
		// Step 1: Input Validation
		 // 이름이 null이거나 비어있는 경우 예외 발생
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or empty");
		}

		// Step 3: Sanitizing the Name
		// 이름에서 특수문자 제거하고 소문자로 변환
		String sanitizedName = name.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

		// Step 4: Combining the Number Sequence and Name
		// 캘린더 시퀀스 번호와 이름을 결합
		String combinedStr = calSeq + sanitizedName;

		// Step 5: Generating a Hash
		// SHA-256 해시 알고리즘을 사용하여 문자열 해싱
		String uniqueToken = hashString(combinedStr);

		// Step 6: Final Token Generation
		 // 생성된 토큰 반환
		return uniqueToken;
	}
	// 문자열을 SHA-256 해시로 변환하는 메서드
	private static String hashString(String input) throws NoSuchAlgorithmException {
		// SHA-256 해시 알고리즘으로 입력 문자열 해싱
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashBytes = digest.digest(input.getBytes());
		 // 해시 값을 16진수 문자열로 변환
		StringBuilder hexString = new StringBuilder(2 * hashBytes.length);
		for (byte b : hashBytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		// 변환된 16진수 문자열 반환
		return hexString.toString();
	}
}
