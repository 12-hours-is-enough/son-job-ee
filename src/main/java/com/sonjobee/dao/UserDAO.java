package com.sonjobee.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sonjobee.model.User;
import com.sonjobee.util.DBConnection;

public class UserDAO {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	// 특정 User 정보 전체 정보 가져오기 - 마이페이지
	public static User getUserInfo(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				user.setBirthDate(rs.getDate("birth_date"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getString("gender"));
				user.setExperience(rs.getString("experience"));
				user.setPreferredLocation(User.convertJsonToList(rs.getString("preferred_location")));
				user.setPreferredSchedule(User.convertJsonToList(rs.getString("preferred_schedule")));
				user.setPreferredJobCategory(User.convertJsonToList(rs.getString("preferred_job_category")));
				user.setAppliedJobIds(User.convertJsonToIntegerList(rs.getString("applied_job_ids")));
				user.setAdditionalInfo(rs.getString("additional_info"));
				user.setCreatedAt(rs.getTimestamp("created_at"));
				user.setUpdatedAt(rs.getTimestamp("updated_at"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while fetching getUserInfo", e);
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return user;
	}

	// 로그인을 위한 email, 비밀번호 확인 
	public static User getUserLoginInfo(String email, String inputPassword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String storedPassword = rs.getString("password"); // DB에 저장된 비밀번호

				// 입력된 비밀번호와 DB 비밀번호 비교
				if (storedPassword.equals(inputPassword)) {
					user = new User();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setPhone(rs.getString("phone"));
					user.setBirthDate(rs.getDate("birth_date"));
					user.setEmail(rs.getString("email"));
					user.setGender(rs.getString("gender"));
					user.setExperience(rs.getString("experience"));
					user.setPreferredLocation(User.convertJsonToList(rs.getString("preferred_location")));
					user.setPreferredSchedule(User.convertJsonToList(rs.getString("preferred_schedule")));
					user.setPreferredJobCategory(User.convertJsonToList(rs.getString("preferred_job_category")));
					user.setAppliedJobIds(User.convertJsonToIntegerList(rs.getString("applied_job_ids")));
					user.setAdditionalInfo(rs.getString("additional_info"));
					user.setCreatedAt(rs.getTimestamp("created_at"));
					user.setUpdatedAt(rs.getTimestamp("updated_at"));
					System.out.println("⭕ 로그인 성공");
				} else {
					System.out.println("❌ 비밀번호가 일치하지 않습니다.");
				}
			} else {
				System.out.println("❌ 이메일이 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while fetching getUserLoginInfo", e);
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return user;
	}

	// User 회원가입 - 회원 가입 시 동일한 유저 있는지 확인
	public static boolean userSign(User user) {  // ✅ 매개변수 추가
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DBConnection.getConnection();

	        // 이메일 중복 체크
	        pstmt = conn.prepareStatement("SELECT COUNT(*) FROM users WHERE email = ?");
	        pstmt.setString(1, user.getEmail());
	        rs = pstmt.executeQuery();

	        if (rs.next() && rs.getInt(1) > 0) {
	            System.out.println("⚠ 이미 존재하는 이메일입니다.");
	            return false;
	        }
	        rs.close();
	        pstmt.close();

	        String sql = "INSERT INTO users (name, phone, birth_date, email, password, gender, experience, " +
	                     "preferred_location, preferred_schedule, preferred_job_category, applied_job_ids, additional_info, created_at, updated_at) " +
	                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, user.getName());
	        pstmt.setString(2, user.getPhone());
	        pstmt.setDate(3, new Date(user.getBirthDate().getTime()));
	        pstmt.setString(4, user.getEmail());
	        pstmt.setString(5, user.getPassword());
	        pstmt.setString(6, user.getGender());
	        pstmt.setString(7, user.getExperience());
	        pstmt.setString(8, new ObjectMapper().writeValueAsString(user.getPreferredLocation()));
	        pstmt.setString(9, new ObjectMapper().writeValueAsString(user.getPreferredSchedule()));
	        pstmt.setString(10, new ObjectMapper().writeValueAsString(user.getPreferredJobCategory()));
	        pstmt.setString(11, new ObjectMapper().writeValueAsString(user.getAppliedJobIds()));
	        pstmt.setString(12, user.getAdditionalInfo());

	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;  // 회원가입 성공 시 true 반환

	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Database error occurred while fetching userSign", e);
	    } finally {
	        DBConnection.close(conn, pstmt, rs);
	    }
	}

	// User 정보 수정
	public static boolean updateUserInfo(int userId, User userDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE users SET name = ?, phone = ?, birth_date = ?, password = ?, gender = ?, experience = ?, "
					+ "preferred_location = ?, preferred_schedule = ?, preferred_job_category = ?, additional_info = ?, updated_at = NOW() "
					+ "WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getName());
			pstmt.setString(2, userDto.getPhone());
			pstmt.setDate(3, new Date(userDto.getBirthDate().getTime()));
			pstmt.setString(4, userDto.getPassword());
			pstmt.setString(5, userDto.getGender());
			pstmt.setString(6, userDto.getExperience());
			pstmt.setString(7, objectMapper.writeValueAsString(userDto.getPreferredLocation())); // JSON 변환
			pstmt.setString(8, objectMapper.writeValueAsString(userDto.getPreferredSchedule()));
			pstmt.setString(9, objectMapper.writeValueAsString(userDto.getPreferredJobCategory()));
			pstmt.setString(10, userDto.getAdditionalInfo());
			pstmt.setInt(11, userId);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0; // 성공 시 true 반환

		} catch (SQLException | com.fasterxml.jackson.core.JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while updating User info", e);
		} finally {
			DBConnection.close(conn, pstmt, null);
		}
	}

	// user 삭제
	public static boolean deleteUser(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "DELETE FROM users WHERE email = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0; // 성공적으로 삭제되면 true 반환

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while deleting User", e);
		} finally {
			DBConnection.close(conn, pstmt, null);
		}
	}

	/*
	public static void main(String[] args) {
		// 1️⃣ 회원가입 테스트
		// 1️⃣ 회원가입 테스트
		User newUser = User.builder().name("홍길동").phone("010-1234-5678").birthDate(Date.valueOf("1990-05-20"))
				.email("test@example.com") // ✅ 동일한 이메일이 있으면 가입 실패 (중복 체크)
				.password("securepassword123") // ✅ 비밀번호 추가
				.gender("M").experience("3년").preferredLocation(Arrays.asList("서울", "경기"))
				.preferredSchedule(Arrays.asList("주말", "야간")).preferredJobCategory(Arrays.asList("배달", "청소"))
				.appliedJobIds(Arrays.asList(1, 2, 3)).additionalInfo("성실하게 일하겠습니다!").build();

		boolean isSignedUp = UserDAO.userSign(newUser);
		System.out.println("✅ 회원가입 결과: " + (isSignedUp ? "성공" : "실패"));

		// 2️⃣ 회원 정보 조회 (비밀번호 추가하여 올바른 호출)
		User fetchedUser = UserDAO.getUserLoginInfo("test@example.com", "securepassword123");

		if (fetchedUser != null) {
			System.out.println("🔍 회원 정보 조회 성공: " + fetchedUser.getName() + " / " + fetchedUser.getEmail());
		} else {
			System.out.println("❌ 회원이 존재하지 않습니다.");
		}

		// 3️⃣ 회원 삭제 테스트 (이메일 기반 ID 조회 후 삭제)
		User fetchedUserForDelete = UserDAO.getUserLoginInfo("test@example.com", "securepassword123");

		if (fetchedUserForDelete != null) {
			boolean isDeleted = UserDAO.deleteUser(fetchedUserForDelete.getEmail()); // ✅ ID로 삭제
			System.out.println("🗑 회원 삭제 결과: " + (isDeleted ? "성공" : "실패"));
		} else {
			System.out.println("❌ 삭제할 회원이 존재하지 않습니다.");
		}

		// 4️⃣ 삭제 후 회원 조회 확인
		User afterDeleteUser = UserDAO.getUserLoginInfo("test@example.com", "securepassword123");

		if (afterDeleteUser == null) {
			System.out.println("✅ 회원 삭제 확인 완료: 해당 회원이 더 이상 존재하지 않습니다.");
		} else {
			System.out.println("❌ 회원 삭제 실패: 여전히 회원이 존재합니다.");
		}
	}
	*/

}
