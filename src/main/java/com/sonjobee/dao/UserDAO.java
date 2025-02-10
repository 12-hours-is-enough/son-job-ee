package com.sonjobee.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sonjobee.model.User;
import com.sonjobee.util.DBConnection;

public class UserDAO {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	// 특정 User 정보 전체 정보 가져오기 - 마이페이지 (session에 있는 id로 가져옴)
	public User getUserInfo(int userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
			pstmt.setInt(1, userId);
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
				user.setPreferredLocation(rs.getString("preferred_location"));
				user.setPreferredSchedule(rs.getString("preferred_schedule"));
				user.setPreferredJobCategory(rs.getString("preferred_job_category"));
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
	public User getUserLoginInfo(String email, String inputPassword) {
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
	public boolean userSign(User user) { // ✅ 매개변수 추가
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

			String sql = "INSERT INTO users (name, phone, birth_date, email, password, gender, experience, "
					+ "preferred_location, preferred_schedule, preferred_job_category, applied_job_ids, additional_info, created_at, updated_at) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPhone());
			pstmt.setDate(3, new Date(user.getBirthDate().getTime()));
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPassword());
			pstmt.setString(6, user.getGender());
			pstmt.setString(7, user.getExperience());
			pstmt.setString(8, user.getPreferredLocation());
			pstmt.setString(9, user.getPreferredSchedule());
			pstmt.setString(10, user.getPreferredJobCategory());
			pstmt.setString(11, new ObjectMapper().writeValueAsString(user.getAppliedJobIds()));
			pstmt.setString(12, user.getAdditionalInfo());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0; // 회원가입 성공 시 true 반환

		} catch (SQLException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while fetching userSign", e);
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
	}

	// User 정보 수정
	public boolean updateUserInfo(int userId, User userDto) {
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
			pstmt.setString(7, userDto.getPreferredLocation()); 
			pstmt.setString(8, userDto.getPreferredSchedule());
			pstmt.setString(9, userDto.getPreferredJobCategory());
			pstmt.setString(10, userDto.getAdditionalInfo());
			pstmt.setInt(11, userId);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0; // 성공 시 true 반환

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while updating User info", e);
		} finally {
			DBConnection.close(conn, pstmt, null);
		}
	}

	// User의 applied 목록 수정
	public boolean updateUserAppliedList(int userId, int jobId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBConnection.getConnection();
			 // 1. 기존 applied_job_ids 가져오기
            String selectSql = "SELECT applied_job_ids FROM users WHERE id = ?";
            pstmt = conn.prepareStatement(selectSql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();

            List<Integer> jobIds = new ArrayList<>();
            if (rs.next()) {
                String json = rs.getString("applied_job_ids");
                if (json != null && !json.isEmpty()) {
                    jobIds = objectMapper.readValue(json, new TypeReference<List<Integer>>() {});
                }
            }

            // 2. 새로운 jobId 추가 (중복 방지)
            if (!jobIds.contains(jobId)) {
                jobIds.add(jobId);
            }

            // 3. JSON 문자열로 변환
            String updatedJson = objectMapper.writeValueAsString(jobIds);

            // 4. DB에 업데이트
            String updateSql = "UPDATE users SET applied_job_ids = ? WHERE id = ?";
            pstmt = conn.prepareStatement(updateSql);
            pstmt.setString(1, updatedJson);
            pstmt.setInt(2, userId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // 업데이트 성공 여부 반환

		} catch (SQLException | com.fasterxml.jackson.core.JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while updating User applied info", e);
		} finally {
			DBConnection.close(conn, pstmt, null);
		}
	}

	// user 삭제
	public boolean deleteUser(int userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "DELETE FROM users WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0; // 성공적으로 삭제되면 true 반환

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while deleting User", e);
		} finally {
			DBConnection.close(conn, pstmt, null);
		}
	}
}
