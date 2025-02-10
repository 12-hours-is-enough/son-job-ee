package com.sonjobee.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("islogin") == null) {
			response.sendRedirect("login");
			return;
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


	// POST
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		Integer userId = (Integer) request.getSession().getAttribute("id"); // 세션에서 userId 가져오기

		if ("signup".equals(action)) {
			// 회원가입 처리
			userSign(request, response);
		} else if ("updateUserInfo".equals(action)) {
			// 사용자 전체 정보 수정
			updateUserInfo(request, response);
		} else if ("updateAppliedJob".equals(action)) {
			// 지원 공고 업데이트
			String jobIdParam = request.getParameter("jobId");
			int jobId = Integer.parseInt(jobIdParam);
			updateAppliedJob(request, response, userId, jobId);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
		}

	}

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

	// user 생성
	private void userSign(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			userDAO.userSign(user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/jobList.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "회원가입 중 오류가 발생했습니다.");
		}
	}

	// user info 수정
	private void updateUserInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("id");

		// pw가 맞는지 확인 후 실행
		User existUser = userDAO.getUserInfo(userId);
		String inputPw = request.getParameter("pw");

		// pw 검증
		if (inputPw == null || !existUser.getPassword().equals(inputPw)) {
			response.sendRedirect("userPage.jsp?message=비밀번호가 일치하지 않습니다.");
			return;
		}

		// 사용자 정보 가져오기
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setPhone(request.getParameter("phone"));
		user.setBirthDate(TimestampConverter.convertStringToTimestamp(request.getParameter("birthDate")));

		// 새 비밀번호 입력 시 업데이트
		String newPassword = request.getParameter("password");
		user.setPassword(newPassword != null && !newPassword.isEmpty() ? newPassword : existUser.getPassword());
//		user.setPassword(request.getParameter("password"));
		user.setGender(request.getParameter("gender"));
		user.setExperience(request.getParameter("experience"));
		user.setPreferredLocation(User.convertJsonToList(request.getParameter("preferredLocation")));
		user.setPreferredSchedule(User.convertJsonToList(request.getParameter("preferredSchedule")));
		user.setPreferredJobCategory(User.convertJsonToList(request.getParameter("preferredJobCategory")));
		user.setAdditionalInfo(request.getParameter("additionalInfo"));

		// DB 업데이트
		boolean success = userDAO.updateUserInfo(userId, user);

		if (success) {
			response.sendRedirect("userPage.jsp?message=정보가 성공적으로 업데이트되었습니다.");
		} else {
			response.sendRedirect("userPage.jsp?message=정보 업데이트에 실패했습니다.");
		}
	}

	// user applied job 수정
	private void updateAppliedJob(HttpServletRequest request, HttpServletResponse response, int userId, int jobId)
			throws ServletException, IOException {

		userDAO.updateUserAppliedList(userId, jobId);

		 
	}

}
