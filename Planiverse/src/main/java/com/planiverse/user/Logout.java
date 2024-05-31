package com.planiverse.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user/logout.do")
public class Logout extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 현재 세션이 존재하는 경우에만 세션을 가져옵니다
        HttpSession session = req.getSession(false);
        // 세션이 존재하는 경우
        if (session != null) {
            // 세션에서 사용자 ID 속성 제거
            session.removeAttribute("id");
            // 세션 무효화
            session.invalidate();
        }

        // 메인 페이지로 리다이렉트
        resp.sendRedirect("/plan/planiverse.do");
    }
}