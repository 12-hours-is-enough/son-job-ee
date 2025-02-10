package com.sonjobee.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.sonjobee.dao.CompanyDAO;
import com.sonjobee.model.Company;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/company/*")
public class CompanyServlet extends HttpServlet {
	
	private CompanyDAO companyDAO;
    
   
	public void init() throws ServletException {
		companyDAO = new CompanyDAO();
	}
	
	// company 가져오기 all or one
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            List<Company> companies = companyDAO.getAllCompanies();
            request.setAttribute("companies", companies);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/jobList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while fetching job data.");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    HttpSession session = request.getSession();
		    Integer companyId = (Integer) session.getAttribute("id");

		    Company coms = new Company();
		    coms.setName(request.getParameter("companyName"));
		    coms.setEmail(request.getParameter("companyEmail"));
		    coms.setPhone(request.getParameter("companyPhone"));
		    coms.setPassword(request.getParameter("companyPw"));
		    
		    // DB 업데이트
		    boolean success = companyDAO.updateCompany(companyId, coms);

		    if (success) {
		        response.sendRedirect("mypage");
		    } 
		} catch (SQLException e) {
		    e.printStackTrace();  // 콘솔에 에러 로그 출력
		    response.sendRedirect("companyPage.jsp");
		}
	}
}