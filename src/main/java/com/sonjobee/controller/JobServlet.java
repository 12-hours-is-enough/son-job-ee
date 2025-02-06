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


@WebServlet("/job/*")
public class JobServlet extends HttpServlet {

    private JobDAO jobDAO;

    @Override
    public void init() throws ServletException {
        // DB 연결을 가져오는 부분을 jobDAO 객체에 전달
        jobDAO = new JobDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String pathInfo = request.getPathInfo();
    	try {
	    	if (pathInfo == null || pathInfo.equals("/")) {
	    		// /job 요청, 모든 job 반환
	            // JobDAO의 getAllJobs 메서드 호출하여 전체 job 목록을 가져옴
	            List<Job> jobs = jobDAO.getAllJobs();
	            // request에 jobs 목록을 추가하여 JSP로 전달
	            request.setAttribute("jobs", jobs);
	            // jobList.jsp로 forward하여 결과 표시
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/jobList.jsp");
	            dispatcher.forward(request, response);
	    	} else {
	    		// job/{id}요청, 특정 job 반환
	    		int jobId = Integer.parseInt(pathInfo.substring(1));
	    		
	    		// job 반환
	    	}
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while fetching job data.");
        }
    			
    }
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Job job = new Job();
        job.setCompanyId(Integer.parseInt(request.getParameter("companyId")));
        job.setLocation(request.getParameter("location"));
        job.setJobCategory(request.getParameter("jobCategory"));
        job.setSalary(request.getParameter("salary"));
        job.setSchedule(request.getParameter("schedule"));
        job.setAdditionalInfo(request.getParameter("additionalInfo"));
        job.setApplicationDeadline(TimestampConverter.convertStringToTimestamp(request.getParameter("applicationDeadline")));
    	
        JobDAO jobDAO = new JobDAO();
        // job add
	}    

}
