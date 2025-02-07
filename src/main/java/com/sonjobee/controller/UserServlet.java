package com.sonjobee.controller;

import com.sonjobee.util.TimestampConverter;
import java.io.IOException;

import com.mysql.cj.util.Util;
import com.sonjobee.dao.JobDAO;
import com.sonjobee.dao.UserDAO;
import com.sonjobee.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        // DB 연결을 가져오는 부분을 jobDAO 객체에 전달
        userDAO = new UserDAO();
    }
    
    
    // user 수정
    @Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	User user = new User();
    	user.setName(request.getParameter("name"));
    	user.setPhone(request.getParameter("phone"));
    	user.setBirthDate(TimestampConverter.convertStringToTimestamp(request.getParameter("birthDate")));
    	user.setPassword(request.getParameter("password"));
    	user.setGender(request.getParameter("gender"));
    	user.setExperience(request.getParameter("experience"));
    	user.setPreferredLocation(User.convertJsonToList(request.getParameter("preferredLocation")));
    	user.setPreferredSchedule(User.convertJsonToList(request.getParameter("preferredSchedule")));
    	user.setPreferredJobCategory(User.convertJsonToList(request.getParameter("preferredJobCategory")));
    	user.setAdditionalInfo(request.getParameter("additionalInfo"));
    	
    	try {
    		UserDAO.updateUserInfo(Integer.parseInt(request.getParameter("id")), user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/jobList.jsp");
            dispatcher.forward(request, response);
    	} catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while fetching job data.");
    	}
    	
    }
    
    
    // user 생성
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	User user = new User();
    	user.setName(request.getParameter("name"));
    	user.setPhone(request.getParameter("phone"));
    	user.setBirthDate(TimestampConverter.convertStringToTimestamp(request.getParameter("birthDate")));
    	user.setEmail(request.getParameter("email"));
    	user.setPassword(request.getParameter("password"));
    	user.setGender(request.getParameter("gender"));
    	user.setExperience(request.getParameter("experience"));
    	user.setPreferredLocation(User.convertJsonToList(request.getParameter("preferredLocation")));
    	user.setPreferredSchedule(User.convertJsonToList(request.getParameter("preferredSchedule")));
    	user.setPreferredJobCategory(User.convertJsonToList(request.getParameter("preferredJobCategory")));
    	user.setAppliedJobIds(User.convertJsonToIntegerList(request.getParameter("appliedJobIds")));
    	user.setAdditionalInfo(request.getParameter("additionalInfo"));
    	
    	try {
    		UserDAO.userSign(user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/jobList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while fetching job data.");
        }
    }
    
    // user 삭제
    @Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String email = request.getParameter("email");
    	
    	try {
    		UserDAO.deleteUser(request.getParameter("email"));
    	} catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error occurred while fetching job data.");
    	}
    }
}
