package com.sonjobee.controller;

import java.io.IOException;
import java.util.List;

import com.sonjobee.dao.JobDAO;
import com.sonjobee.model.Job;
import com.sonjobee.util.TimestampConverter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/job/*")
public class JobServlet extends HttpServlet {	
	private JobDAO jobDAO;
	
    @Override
    public void init() throws ServletException {
        jobDAO = new JobDAO();
    }
    
    // job 가져오기
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String pathInfo = request.getPathInfo();
    	try {
    		// /job 요청, 모든 job 반환
            // JobDAO의 getAllJobs 메서드 호출하여 전체 job 목록을 가져옴
            List<Job> jobs = jobDAO.getAllJobs();
            // request에 jobs 목록을 추가하여 JSP로 전달
            request.setAttribute("jobs", jobs);
            // jobList.jsp로 forward하여 결과 표시
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/jobList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while fetching job data.");
        }
    }
    
    // job 생성 및 수정
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        String pathInfo = request.getPathInfo();

		if (session.getAttribute("islogin") == null) {
			response.sendRedirect("login");
			return ;
		}
		try {
	        if (pathInfo == null || pathInfo.equals("/")) {
	    		int id = (Integer) session.getAttribute("id");
	        	Job job = new Job();
	            job.setCompanyId(id);
	        	job.setJobTitle(request.getParameter("jobTitle"));
	        	job.setJobContent(request.getParameter("jobContent"));
	            job.setLocation(request.getParameter("location"));
	            job.setJobCategory(request.getParameter("jobCategory"));
	            job.setSalary(request.getParameter("salary"));
	            job.setSchedule(request.getParameter("schedule"));
	            job.setAdditionalInfo(request.getParameter("additionalInfo"));
	            job.setApplicationDeadline(TimestampConverter.convertStringToDate(request.getParameter("applicationDeadline")));
	            jobDAO.createJob(job);
	            response.sendRedirect("board");
	        } else {
	        	int jobId = Integer.parseInt(pathInfo.substring(1));
	        	String action = request.getParameter("action");
	        	if ("delete".equals(action)) {
	        		jobDAO.deleteJob(jobId);
	        	}
	        	else {
		        	Job job = new Job();
		        	job.setJobTitle(request.getParameter("jobTitle"));
		        	job.setJobContent(request.getParameter("jobContent"));
		            job.setLocation(request.getParameter("location"));
		            job.setJobCategory(request.getParameter("jobCategory"));
		            job.setSalary(request.getParameter("salary"));
		            job.setSchedule(request.getParameter("schedule"));
		            job.setAdditionalInfo(request.getParameter("additionalInfo"));
		            job.setApplicationDeadline(TimestampConverter.convertStringToDate(request.getParameter("applicationDeadline")));
		            jobDAO.updateJobInfo(jobId, job);
	        	}
	            response.sendRedirect("/son-job-ee/board");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}