package com.sonjobee.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.sonjobee.dao.CompanyDAO;
import com.sonjobee.dao.UserDAO;
import com.sonjobee.model.Company;
import com.sonjobee.model.User;
import com.sonjobee.util.TimestampConverter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	
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
		
		if (session.getAttribute("islogin") == null) {
			response.sendRedirect("login");
			return ;
		} 
		
		try {
			if (session.getAttribute("usertype").equals("user")) {
				User user = userDAO.getUserInfo(Integer.parseInt(request.getParameter("id")));
				
				request.setAttribute("userInfo", user);
				request.getRequestDispatcher("/WEB-INF/views/userPage.jsp").forward(request, response);
			} else if (session.getAttribute("usertype").equals("company")) {
				Company company = companyDAO.getOneCompany(Integer.parseInt(request.getParameter("id")));
				
				request.setAttribute("companyInfo", company);
				request.getRequestDispatcher("/WEB-INF/views/companyPage.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	// user 수정
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		    HttpSession session = request.getSession();
		    Integer userId = (Integer) session.getAttribute("id");

			User user = new User();
			user.setName(request.getParameter("name"));
			user.setPhone(request.getParameter("phone"));
			user.setBirthDate(TimestampConverter.convertStringToDate(request.getParameter("birthDate")));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setGender(request.getParameter("gender"));
			user.setExperience(request.getParameter("experience"));
			user.setPreferredLocation(request.getParameter("preferredLocation"));
			user.setPreferredSchedule(request.getParameter("preferredSchedule"));
			user.setPreferredJobCategory(request.getParameter("preferredJobCategory"));
			user.setAdditionalInfo(request.getParameter("additionalInfo"));
			
			System.out.println(user);
		    // DB 업데이트
		    boolean success = userDAO.updateUserInfo(userId, user);

		    if (success) {
		        response.sendRedirect("mypage"); // 새 요청 전송 (데이터 유지 X)
		    } else {
		    	System.out.println("fail update");
		    }
		} catch (Exception e) {
		    e.printStackTrace();  // 콘솔에 에러 로그 출력
		    System.out.println("뭐지");
		}
	}

//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		try {
//		    HttpSession session = request.getSession();
//		    Integer userId = (Integer) session.getAttribute("id");
//
//			User user = new User();
//			user.setName(request.getParameter("name"));
//			user.setPhone(request.getParameter("phone"));
//			user.setBirthDate(TimestampConverter.convertStringToTimestamp(request.getParameter("birthDate")));
//			user.setEmail(request.getParameter("email"));
//			user.setPassword(request.getParameter("password"));
//			user.setGender(request.getParameter("gender"));
//			user.setExperience(request.getParameter("experience"));
//			user.setPreferredLocation(request.getParameter("preferredLocation"));
//			user.setPreferredSchedule(request.getParameter("preferredSchedule"));
//			user.setPreferredJobCategory(request.getParameter("preferredJobCategory"));
//			user.setAppliedJobIds(User.convertJsonToIntegerList(request.getParameter("appliedJobIds")));
//			user.setAdditionalInfo(request.getParameter("additionalInfo"));
//			
//			System.out.println(user);
//		    // DB 업데이트
//		    boolean success = userDAO.updateUserInfo(userId, user);
//
//		    if (success) {
//		        response.sendRedirect("mypage"); // 새 요청 전송 (데이터 유지 X)
//		    } else {
//		        response.sendRedirect("mypage");
//		    }
//		} catch (Exception e) {
//		    e.printStackTrace();  // 콘솔에 에러 로그 출력
//		}
//		
//	}

	// user 삭제
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String email = request.getParameter("email"); 기존 코드
		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute("id");

		try {
			userDAO.deleteUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"Database error occurred while fetching job data.");
		}
	}
	
	
	// user info 수정
//	private void updateUserInfo(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		Integer userId = (Integer) session.getAttribute("id");
//
//		// 사용자 정보 가져오기
//		User user = new User();
//		user.setName(request.getParameter("name"));
//		user.setPhone(request.getParameter("phone"));
//		user.setBirthDate(TimestampConverter.convertStringToTimestamp(request.getParameter("birthDate")));
//		user.setPassword(request.getParameter("password"));
//		user.setGender(request.getParameter("gender"));
//		user.setExperience(request.getParameter("experience"));
//		user.setPreferredLocation(request.getParameter("preferredLocation"));
//		user.setPreferredSchedule(request.getParameter("preferredSchedule"));
//		user.setPreferredJobCategory(request.getParameter("preferredJobCategory"));
//		user.setAdditionalInfo(request.getParameter("additionalInfo"));
//
//		// DB 업데이트
//		boolean success = userDAO.updateUserInfo(userId, user);
//
//		if (success) {
//			response.sendRedirect("userPage.jsp?message=정보가 성공적으로 업데이트되었습니다.");
//		} else {
//			response.sendRedirect("userPage.jsp?message=정보 업데이트에 실패했습니다.");
//		}
//	}

	// user applied job 수정
	private void updateAppliedJob(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute("id");
		String jobIdParam = request.getParameter("jobId");

		if (jobIdParam == null || jobIdParam.isEmpty()) {
			response.sendRedirect("error.jsp?message=잘못된 요청입니다.");
			return;
		}

		int jobId = Integer.parseInt(jobIdParam);
		
		boolean success = userDAO.updateUserAppliedList(userId, jobId);

		if (success) {
			response.sendRedirect("jobList.jsp?message=지원이 완료되었습니다.");
		} else {
			response.sendRedirect("jobList.jsp?message=이미 지원한 공고입니다.");
		}
	}

}
