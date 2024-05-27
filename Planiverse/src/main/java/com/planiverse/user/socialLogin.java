package com.planiverse.user;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planiverse.event.data.CalAdd;
import com.planiverse.event.model.CalDTO;
import com.planiverse.event.repository.CalDAO;
import com.planiverse.user.repository.UserDAO;

@WebServlet("/user/socialLogin.do")
public class socialLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("name");

		System.out.println(email);
		System.out.println(name);

		UserDAO dao = new UserDAO();
		int result = dao.socialLogin(email, name);
		System.out.println("socialLogin: "+result);
		
		

		if(result == 1) {
			HttpSession session = req.getSession();

			session.setAttribute("id", email);
			CalDAO calDao = new CalDAO();

			ArrayList<CalDTO> list = calDao.list(email);
			session.setAttribute("calDTO", list);
			
			ArrayList<CalDTO> shareList = calDao.shareList(email);
			session.setAttribute("shareCalDTO", shareList);

			resp.sendRedirect("/plan/planiverse.do");
		}else if (result == 0) {
			HttpSession session = req.getSession();
			int addprofile = dao.addprofile(email, name);
			System.out.println("addprofile: "+addprofile);
			if(addprofile == 1) {

				session.setAttribute("id", email);
			}
			CalDAO calDao = new CalDAO();
			int calSeq = calDao.newCal("기본", calDao.newCalList(email));
			if (calSeq != -1) {
				String token;
				try {
					token = CalAdd.generateUniqueToken(calSeq, name);
					calDao.inShare(email, calSeq, token);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
			
			ArrayList<CalDTO> list = calDao.list(email);
			session.setAttribute("calDTO", list);
			
			ArrayList<CalDTO> shareList = calDao.shareList(email);
			session.setAttribute("shareCalDTO", shareList);
			
			resp.sendRedirect("/plan/planiverse.do");
		} else {
			req.setAttribute("result", 0);
			System.out.println("로그인 실패");
		}
	}
}
