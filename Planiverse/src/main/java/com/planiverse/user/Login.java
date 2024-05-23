package com.planiverse.user;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planiverse.user.repository.UserDAO;

@WebServlet("/event/login.do")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginId = req.getParameter("loginId");
        String loginPw = req.getParameter("loginPw");

        System.out.println(loginId);
        System.out.println(loginPw);

        UserDAO dao = new UserDAO();
        boolean result = dao.login(loginId, loginPw);
        System.out.println(result);

        PrintWriter writer = resp.getWriter();
        if (result == true) {
            System.out.println("로그인 성공");
            HttpSession session = req.getSession();
            System.out.println(session);

            session.setAttribute("id", loginId); // 인증 티켓
            System.out.println(session);
            writer.print(1);
          
        } else {
            writer.print(0);
            System.out.println("로그인 실패");
        }
        writer.close();
    }
}
