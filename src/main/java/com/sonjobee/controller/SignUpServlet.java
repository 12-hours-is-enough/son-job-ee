package com.sonjobee.controller;

import java.io.IOException;

import com.sonjobee.dao.CompanyDAO;
import com.sonjobee.dao.UserDAO;
import com.sonjobee.model.Company;
import com.sonjobee.model.User;
import com.sonjobee.util.TimestampConverter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userType = request.getParameter("userType");
		
    	if (userType.equals("user")) {
    		request.getRequestDispatcher("/WEB-INF/views/userSignup.jsp").forward(request, response);
    	} else if (userType.equals("company")) {
    		request.getRequestDispatcher("/WEB-INF/views/companySignup.jsp").forward(request, response);
    	}
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();

		String userType = request.getParameter("usertype");
    	String url = "login";

    	if (userType.equals("user")) {
    		User user = new User();
    		
    		user.setEmail(request.getParameter("userEmail"));
    		user.setPassword(request.getParameter("userPw"));
    		user.setName(request.getParameter("userName"));
    		user.setPhone(request.getParameter("userPhone"));
    		user.setBirthDate(TimestampConverter.convertStringToDate(request.getParameter("userBirth")));
    		user.setGender(request.getParameter("userGender"));
    		user.setExperience(request.getParameter("userExperience"));
    	
    		try {
    			UserDAO.userSign(user);
    	    	response.sendRedirect(url);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	} else if (userType.equals("company")) {
    		Company company = new Company();
    		
    		company.setName(request.getParameter("companyName"));
    		company.setPhone(request.getParameter("companyPhone"));
    		company.setEmail(request.getParameter("companyEmail"));
    		company.setPassword(request.getParameter("companyPw"));
    		
    		try {
    			CompanyDAO.signUpCompany(company);
    	    	response.sendRedirect(url);
    		} catch (Exception e) {
                e.printStackTrace();
    		}
    	}
	}
}
