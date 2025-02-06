package com.sonjobee.dao;

import java.io.IOException;
import java.sql.Connection;
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

	// User 전체 정보 가져오기 - user 수정 시 + 삭제 시?
	public static List<User> getUserInfo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM users");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				User user = new User();
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

				// 디버깅을 위한 출력문 추가
				System.out.println("User ID: " + user.getId());
				System.out.println("Preferred Location: " + user.getPreferredLocation());
				System.out.println("Preferred Schedule: " + user.getPreferredSchedule());
				System.out.println("Preferred Job Category: " + user.getPreferredJobCategory());
				System.out.println("Applied Job IDs: " + user.getAppliedJobIds());
				System.out.println("------------------------------------");

				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while fetching getUserInfo", e);
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return users;
	}

	// User 정보 가져오기 - 로그인 시 회원 정보 확인 + 삭제시 확인?
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
	public static boolean userSign() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;

		try {
			conn = DBConnection.getConnection();
			user = new User();

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

			String sql = "INSERT INTO users (name, phone, birth_date, email, password, gender, experience, preferred_location, preferred_schedule, preferred_job_category, applied_job_ids, additional_info, created_at, updated_at) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPhone());
			pstmt.setDate(3, user.getBirthDate());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPassword()); // 🔴 비밀번호 평문 저장 (보안상 권장되지 않음)
			pstmt.setString(6, user.getGender());
			pstmt.setString(7, user.getExperience());
			pstmt.setString(8, new ObjectMapper().writeValueAsString(user.getPreferredLocation()));
			pstmt.setString(9, new ObjectMapper().writeValueAsString(user.getPreferredSchedule()));
			pstmt.setString(10, new ObjectMapper().writeValueAsString(user.getPreferredJobCategory()));
			pstmt.setString(11, new ObjectMapper().writeValueAsString(user.getAppliedJobIds()));
			pstmt.setString(12, user.getAdditionalInfo());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0; // 성공 시 true 반환

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
            String sql = "UPDATE users SET name = ?, phone = ?, birth_date = ?, gender = ?, experience = ?, " +
                         "preferred_location = ?, preferred_schedule = ?, preferred_job_category = ?, additional_info = ?, updated_at = NOW() " +
                         "WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userDto.getName());
            pstmt.setString(2, userDto.getPhone());
            pstmt.setDate(3, userDto.getBirthDate());
            pstmt.setString(4, userDto.getGender());
            pstmt.setString(5, userDto.getExperience());
            pstmt.setString(6, objectMapper.writeValueAsString(userDto.getPreferredLocation())); // JSON 변환
            pstmt.setString(7, objectMapper.writeValueAsString(userDto.getPreferredSchedule()));
            pstmt.setString(8, objectMapper.writeValueAsString(userDto.getPreferredJobCategory()));
            pstmt.setString(9, userDto.getAdditionalInfo());
            pstmt.setInt(10, userId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  // 성공 시 true 반환

        } catch (SQLException | com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error occurred while updating User info", e);
        } finally {
            DBConnection.close(conn, pstmt, null);
        }
    }
	
	
	// user 삭제
	public static boolean deleteUser(int email) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "DELETE FROM users WHERE email = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, email);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // 성공적으로 삭제되면 true 반환

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error occurred while deleting User", e);
        } finally {
            DBConnection.close(conn, pstmt, null);
        }
    }
	
	
	public static void main(String[] args) {
		
	}
}
