package com.sonjobee.controller;

import java.io.IOException;

import com.sonjobee.dao.CompanyDAO;
import com.sonjobee.dao.UserDAO;
import com.sonjobee.model.Company;
import com.sonjobee.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MyPage
 */
@WebServlet("/mypage")
public class MyPage extends HttpServlet {
	private CompanyDAO companyDAO;
	private UserDAO userDAO;
	   
	public void init() throws ServletException {
		companyDAO = new CompanyDAO();
		userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session.getAttribute("islogin") == null) {
			response.sendRedirect("login");
			return ;
		} 
		
		if("user".equals(session.getAttribute("usertype"))) {
			int id = (Integer) session.getAttribute("id");
			
			User user = userDAO.getUserInfo(id);
			request.setAttribute("user", user);

			request.getRequestDispatcher("/WEB-INF/views/userPage.jsp").forward(request, response);
		} else {
			int id = (Integer) session.getAttribute("id");
			
			Company company = companyDAO.getOneCompany(id);
			request.setAttribute("company", company);
			
			request.getRequestDispatcher("/WEB-INF/views/companyPage.jsp").forward(request, response);
		}
	}
}