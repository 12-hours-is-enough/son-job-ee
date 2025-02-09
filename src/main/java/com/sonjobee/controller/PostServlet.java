package com.sonjobee.controller;

import java.io.IOException;

import com.sonjobee.dao.JobDAO;
import com.sonjobee.model.Job;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
	private JobDAO jobDAO;
	
    @Override
    public void init() throws ServletException {
        jobDAO = new JobDAO();
    }
    
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session.getAttribute("islogin") == null) {
			response.sendRedirect("login");
			return ;
		} 
		
		request.getRequestDispatcher("/WEB-INF/views/uploadPost.jsp").forward(request, response);
	}
}
