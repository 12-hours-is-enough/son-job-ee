package com.sonjobee.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.sonjobee.dao.CompanyDAO;
import com.sonjobee.dao.JobDAO;
import com.sonjobee.model.Company;
import com.sonjobee.model.Job;
import com.sonjobee.util.TimestampConverter;


@WebServlet("/company/*")
public class CompanyServlet extends HttpServlet {
	
	private CompanyDAO companyDAO;
    
   
	public void init() throws ServletException {
		companyDAO = new CompanyDAO();
	}

	
	// company 가져오기 all or one
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		try {
			if (pathInfo == null || pathInfo.equals("/")) {
	            List<Company> companies = companyDAO.getAllCompanies();
	            request.setAttribute("companies", companies);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/jobList.jsp");
	            dispatcher.forward(request, response);
			}else {
				int companyId = Integer.parseInt(pathInfo.substring(1));
				
				Company company = companyDAO.getOneCompany(companyId);
				request.setAttribute("company", company);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/jobList.jsp");
	            dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while fetching job data.");
		}
		
		
	}
	//수정
		protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Company coms = new Company();
			coms.setId(Integer.parseInt(request.getParameter("id")));
    		coms.setName(request.getParameter("name"));
    		coms.setEmail(request.getParameter("email"));
    		coms.setPhone(request.getParameter("phone"));
    		coms.setPassword(request.getParameter("password"));
	    
	    	try {
				companyDAO.updateCompany(coms); // 수정된 객체만 전달 //????
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/jobList.jsp");//여기랑
	            dispatcher.forward(request, response);
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while fetching job data.");//여기도?
	        }
		}
	
	
	//생성	
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Company coms = new Company();
    		coms.setName(request.getParameter("name"));
    		coms.setEmail(request.getParameter("email"));
    		coms.setPhone(request.getParameter("phone"));
    		coms.setPassword(request.getParameter("password"));
	        
	        // job 생성 추가
    		//
    		try {
    			companyDAO.signUpCompany(coms); // 수정된 객체만 전달 //????
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/jobList.jsp");//여기랑
	            dispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while fetching job data.");//여기도?
			}
		}
	

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	            int companyId = Integer.parseInt(request.getParameter("id"));
	            String password = request.getParameter("password");

	            boolean isDeleted = CompanyDAO.deleteCompany(companyId, password);
	            
	            if (isDeleted) {
	                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	            } else {
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to delete company. Invalid ID or password.");
	            }
	        } catch (NumberFormatException e) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid company ID format.");
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while deleting company data.");
	        }
	    }
	}


