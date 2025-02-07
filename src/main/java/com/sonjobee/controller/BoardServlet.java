package com.sonjobee.controller;

import java.io.IOException;
import java.util.List;

import com.sonjobee.dao.JobDAO;
import com.sonjobee.dao.UserDAO;
import com.sonjobee.model.Job;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/* 지원 현황
 * 공고 현황
 */
@WebServlet("/board")
public class BoardServlet extends HttpServlet {

	private JobDAO jobDAO;
	private UserDAO userDAO;
	
	
	@Override
	public void init() throws ServletException {
		jobDAO = new JobDAO();
		userDAO = new UserDAO();
	}

	// 지원 현황 누르면 id 값 받아와서 userDAO로 전달 
	// userDAO에서 받아온 Job리스트를 myStatus.jsp로 전달,
	// 공고 현황 공고 현황 누르면 companyID 값 받아와서 companyDAO로 전달 
	// company DAO에서 받아온 Job리스트를 mystatus로 전달
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		// id 값
		int id = (Integer)session.getAttribute("id");
		// 유저 타입
		String userType = (String) session.getAttribute("usertype");
		
		if(userType == "user") {
			id = Integer.parseInt(request.getParameter("userId"));

			// jobDAO에서 해당 jobId 리스트로 Job 전체 정보 가져오기
			List<Job> jobList = jobDAO.getAppliedJobs(id);

			request.setAttribute("jobList", jobList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("myStatus.jsp");
			dispatcher.forward(request, response);
		}
		else if (userType == "company") {
			// jobDAO에서 해당 jobId 리스트로 Job 전체 정보 가져오기
			List<Job> jobList = jobDAO.getCompanyJob(id);

			request.setAttribute("jobList", jobList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("myPosts.jsp");
			dispatcher.forward(request, response);
		}
		
	}


}
