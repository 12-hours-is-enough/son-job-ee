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
		if (session.getAttribute("islogin") != null) {
			response.sendRedirect("job");
		} else {
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
    }
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String userType = request.getParameter("userType");
    	HttpSession session = request.getSession();
    	
    	if ("user".equals(userType)) {
    		User user = userDAO.getUserLoginInfo(request.getParameter("userId"), request.getParameter("userPw"));
    		if (user != null) {
    			session.setAttribute("usertype", "user");
    			session.setAttribute("id", user.getId());
    			session.setAttribute("islogin", "true");
    			session.setAttribute("name", user.getName());
    			response.sendRedirect("job");
    		} else {
    			request.getRequestDispatcher("/WEB-INF/views/loginFail.jsp").forward(request, response);
    		}
    	} else if ("company".equals(userType)) {
    		Company company = companyDAO.getCompanyLoginInfo(request.getParameter("userId"), request.getParameter("userPw"));
    		if (company != null) {
    			session.setAttribute("usertype", "company");
    			session.setAttribute("id", company.getId());    			
    			session.setAttribute("islogin", "true");
    			session.setAttribute("name",company.getName());
    			response.sendRedirect("board");
    		} else {
    			request.getRequestDispatcher("/WEB-INF/views/loginFail.jsp").forward(request, response);
    		}
    	} 
	}
}
