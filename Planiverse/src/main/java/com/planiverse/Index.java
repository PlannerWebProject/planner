package com.planiverse;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 메인 페이지를 처리하는 서블릿 클래스입니다.
 * GET 요청과 POST 요청을 모두 처리합니다.
 */
@WebServlet("/planiverse.do")
public class Index extends HttpServlet {
	/**
     * GET 요청을 처리합니다.
     * 메인 페이지로 포워딩합니다.
     * @param req 클라이언트의 요청 정보
     * @param resp 응답 정보
     * @throws ServletException Servlet 예외
     * @throws IOException I/O 예외
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 /**
	     * DB 연결을 반환합니다.
	     * @return DB 연결 객체, 실패 시 null
	     */
	req.setAttribute("path", "/plan");

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(req, resp);
	}
	 /**
     * POST 요청을 처리합니다.
     * 메인 페이지로 포워딩합니다.
     * @param req 클라이언트의 요청 정보
     * @param resp 응답 정보
     * @throws ServletException Servlet 예외
     * @throws IOException I/O 예외
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("path", "/plan");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(req, resp);
	}
}

