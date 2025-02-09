package com.sonjobee.dao;

import com.sonjobee.util.DBConnection;

import java.sql.PreparedStatement;
import com.sonjobee.model.Company;
import com.sonjobee.model.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompanyDAO {
	/*
	 * company DAO - 회사 회원가입 - insert - 회사 수정, 삭제 - update, delete - 회사 정보 가져오기 -
	 * select - 한 회사 정보 가져오기 - password 확인용
	 */

	// select all company info
	public List<Company> getAllCompanies() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Company> companies = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM companies");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Company coms = new Company();
				coms.setId(rs.getInt("id"));
				coms.setName(rs.getString("name"));
				coms.setPhone(rs.getString("phone"));
				coms.setPassword(rs.getString("password"));
				coms.setCreatedAt(rs.getTimestamp("created_at"));
				coms.setUpdatedAt(rs.getTimestamp("updated_at"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while fetching DTOs", e);
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return companies;
	}

	// select one company info - verifying password & Company page
	public Company getOneCompany(int companyId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Company company = null;

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM companies WHERE id=?");
			pstmt.setInt(1, companyId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setPhone(rs.getString("phone"));
				company.setPassword(rs.getString("password"));
				company.setCreatedAt(rs.getTimestamp("created_at"));
				company.setUpdatedAt(rs.getTimestamp("updated_at"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while fetching DTOs", e);
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return company;
	}

	// register company (insert company info)
	public boolean signUpCompany(Company co) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(
					"INSERT INTO companies (name, phone, email, password, created_at, updated_at) VALUES (?, ?, ?, ?, NOW(), NOW())");

			pstmt.setString(1, co.getName());
			pstmt.setString(2, co.getPhone());
			pstmt.setString(3, co.getEmail());
			pstmt.setString(4, co.getPassword());

			if (pstmt.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBConnection.close(conn, pstmt);
		}
		return false;
	}

	// modify company info
	public boolean updateCompany(Company co) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(
					"UPDATE companies SET name=?, phone=?, email=?, password=?, updated_at=NOW() WHERE id=?");

			pstmt.setString(1, co.getName());
			pstmt.setString(2, co.getPhone());
			pstmt.setString(3, co.getEmail());
			pstmt.setString(4, co.getPassword());
			pstmt.setInt(6, co.getId());

			if (pstmt.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBConnection.close(conn, pstmt);
		}
		return false;
	}

	// 회사 수정, 삭제 - update, delete
	// delete company info
	public static boolean deleteCompany(int cid, String password) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM companies WHERE id=? AND password=?");

			pstmt.setInt(1, cid);
			pstmt.setString(2, password);

			if (pstmt.executeUpdate() != 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBConnection.close(conn, pstmt);
		}
		return result;
	}

	// 로그인을 위한 email, 비밀번호 확인
	public Company getCompanyLoginInfo(String email, String inputPassword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Company company = null;

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM companies WHERE email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String storedPassword = rs.getString("password"); // DB에 저장된 비밀번호

				// 입력된 비밀번호와 DB 비밀번호 비교
				if (storedPassword.equals(inputPassword)) {
					company = new Company();
					company.setId(rs.getInt("id"));
					company.setName(rs.getString("name"));
					
					System.out.println("⭕ 로그인 성공");
				} else {
					System.out.println("❌ 비밀번호가 일치하지 않습니다.");
				}
			} else {
				System.out.println("❌ 이메일이 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while fetching getCompanyLoginInfo", e);
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return company;
	}

}

/*
 * id INT AUTO_INCREMENT PRIMARY KEY, -- 기본 키 (자동 증가) name VARCHAR(100) NOT
 * NULL, -- 회사 이름 phone VARCHAR(15) NOT NULL UNIQUE, -- 회사 전화번호 (중복 방지) email
 * VARCHAR(100) NOT NULL UNIQUE, -- 이메일 (로그인 ID) password VARCHAR(255) NOT NULL,
 * -- 비밀번호 (해싱 필요) created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 날짜
 * updated_at
 * 
 */
