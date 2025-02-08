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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private UserDAO userDAO;
	private CompanyDAO companyDAO;
	
    @Override
    public void init() throws ServletException {
        // DB 연결을 가져오는 부분을 jobDAO 객체에 전달
        userDAO = new UserDAO();
        companyDAO = new CompanyDAO();
    }
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("islogin") != null && session.getAttribute("islogin") == "true") {
			request.getRequestDispatcher("/WEB-INF/views/jobList.jsp").forward(request, response);
		}
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String userType = request.getParameter("userType");
    	HttpSession session = request.getSession();
    	String url = "job";
    	System.out.println(userType);
    	System.out.println(request.getParameter("userPw"));
    	if (userType == "user") {
    		User user = UserDAO.getUserLoginInfo(request.getParameter("email"), request.getParameter("password"));
    		if (user.getId() != null) {
    			session.setAttribute("usertype", "user");
    			session.setAttribute("id", user.getId());
    			session.setAttribute("islogin", "true");
    		} else {
    			url = "/WEB-INF/views/loginFail.jsp";
    		}
    	} else if (userType == "company") {
    		Company company = CompanyDAO.getCompanyLoginInfo(request.getParameter("email"), request.getParameter("password"));
    		if (company.getId() != null) {
    			session.setAttribute("usertype", "company");
    			session.setAttribute("id", company.getId());    			
    			session.setAttribute("islogin", "true");
    		} else {
    			url = "/WEB-INF/views/loginFail.jsp";
    		}
    	} 
		response.sendRedirect(url);
	}
}
