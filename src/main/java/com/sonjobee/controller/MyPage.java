package com.sonjobee.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class MyPage
 */
@WebServlet("/mypage")
public class MyPage extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("islogin") == null) {
			response.sendRedirect("login");
			return ;
		} 
		
		
		if(session.getAttribute("usertype").equals("user")) {
			request.getRequestDispatcher("/WEB-INF/views/userPage.jsp").forward(request, response);

		} else {
			request.getRequestDispatcher("/WEB-INF/views/companyPage.jsp").forward(request, response);

		}
		
	}
	

}
