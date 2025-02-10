package com.sonjobee.controller;

<<<<<<< Updated upstream
import jakarta.servlet.ServletConfig;
=======
import java.io.IOException;
import java.sql.SQLException;

import com.sonjobee.dao.CompanyDAO;
import com.sonjobee.dao.UserDAO;
import com.sonjobee.model.Company;
import com.sonjobee.model.User;
import com.sonjobee.util.TimestampConverter;

import jakarta.servlet.RequestDispatcher;
>>>>>>> Stashed changes
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

<<<<<<< Updated upstream
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
=======
	// user 수정
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// action=updateUserInfo(유저 정보 수정) or action=updateAppliedJob(공고 지원)
		String action = request.getParameter("action");

		try {
			if ("updateUserInfo".equals(action)) {
				// 사용자 전체 정보 수정
				updateUserInfo(request, response);
			} else if ("updateAppliedJob".equals(action)) {
				// 지원 공고 업데이트
				updateAppliedJob(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server error occurred.");
		}
	}
	
	
	// user 생성
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
		    HttpSession session = request.getSession();
		    Integer userId = (Integer) session.getAttribute("id");

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
			
			System.out.println(user);
		    // DB 업데이트
		    boolean success = userDAO.updateUserInfo(userId, user);

		    if (success) {
		        response.sendRedirect("userPage.jsp"); // 새 요청 전송 (데이터 유지 X)
		    } else {
		        response.sendRedirect("userPage.jsp");
		    }
		} catch (Exception e) {
		    e.printStackTrace();  // 콘솔에 에러 로그 출력
		    response.sendRedirect("userPage.jsp?message=데이터베이스 오류 발생");
		}
		
>>>>>>> Stashed changes
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
