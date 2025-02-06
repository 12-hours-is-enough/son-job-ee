package com.sonjobee.dao;

import com.sonjobee.util.DBConnection;

import java.sql.PreparedStatement;
import com.sonjobee.model.Company;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompanyDAO {
	/* company DAO
    -  회사 회원가입 - insert
    -  회사 수정, 삭제 - update, delete
    -  회사 정보 가져오기 - select
    -  한 회사 정보 가져오기 - password 확인용
    */
	
	// select all company info
	public List<Company> getAllCompanies(){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Company> companies = new ArrayList<>();
        
        try {
        	conn = DBConnection.getConnection();
        	pstmt = conn.prepareStatement("SELECT * FROM companies");
        	rs = pstmt.executeQuery();
        	
        	while(rs.next()) {
        		Company coms = new Company();
        		coms.setId(rs.getInt("id"));
        		coms.setName(rs.getString("name"));
        		coms.setPhone(rs.getString("phone"));
        		coms.setPassword(rs.getString("password"));
        		coms.setCreatedAt(rs.getTimestamp("created_at"));
        		coms.setUpdatedAt(rs.getTimestamp("updated_at"));
        	}
        }  catch (SQLException e) {
        	e.printStackTrace();
            throw new RuntimeException("Database error occurred while fetching DTOs", e);
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return companies;
	}
	
	// select one company info - verifying password
	public Company getOneCompany(int companyId){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Company company = null;
        
        try {
        	conn = DBConnection.getConnection();
        	pstmt = conn.prepareStatement("SELECT * FROM companies WHERE id=?");
			pstmt.setInt(1, companyId);  
			
        	rs = pstmt.executeQuery();
        	
        	while(rs.next()) {
        		Company coms = new Company();
        		coms.setId(rs.getInt("id"));
        		coms.setName(rs.getString("name"));
        		coms.setPhone(rs.getString("phone"));
        		coms.setPassword(rs.getString("password"));
        		coms.setCreatedAt(rs.getTimestamp("created_at"));
        		coms.setUpdatedAt(rs.getTimestamp("updated_at"));
        	}
        }  catch (SQLException e) {
        	e.printStackTrace();
            throw new RuntimeException("Database error occurred while fetching DTOs", e);
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return company;
	}
	
	// register company (insert company info)
	public static boolean signUpCompany(Company co) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt = null;
		
		try {
        	conn = DBConnection.getConnection();
        	pstmt = conn.prepareStatement("INSERT INTO  (name, phone, email, password, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?)");
			
        	pstmt.setString(1, co.getName());
        	pstmt.setString(2, co.getPhone());
        	pstmt.setString(3, co.getEmail());
        	pstmt.setString(4, co.getPassword()); 	
        	
        	// getTime() 메서드를 사용하여 java.util.Date 객체의 시간을 밀리초 단위로 얻고 이를 java.sql.Date 객체로 변환
        	java.sql.Date sqlCreatedAt = new java.sql.Date(co.getCreatedAt().getTime());
    	    pstmt.setDate(5, sqlCreatedAt);

    	    java.sql.Date sqlUpdatedAt = new java.sql.Date(co.getUpdatedAt().getTime());
    	    pstmt.setDate(6, sqlUpdatedAt);  
    	    
    	    if(pstmt.executeUpdate() != 0) {
    	    	return true;
    	    }
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBConnection.close(conn, pstmt);
		}
		return false;
	}
	
	// modify company info
	public static boolean updateCompany(Company co) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("UPDATE company SET name=?, phone=?, email=?, password=?, updated_at=? WHERE id=?");

			pstmt.setString(1, co.getName());   
		    pstmt.setString(2, co.getPhone());   
		    pstmt.setString(3, co.getEmail());  
		    pstmt.setString(4, co.getPassword()); 
		    pstmt.setDate(5, new java.sql.Date(co.getUpdatedAt().getTime())); 
		    pstmt.setInt(6, co.getId());   
		    

    	    if(pstmt.executeUpdate() != 0) {
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
	public static boolean deleteCompany(int cid, String password) throws SQLException{
		Connection conn = null;	
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("DELETE FROM companies WHERE id=? AND password=?");
			
			pstmt.setInt(1, cid);
			pstmt.setString(2, password);
			
			if(pstmt.executeUpdate() != 0) {
				result = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBConnection.close(conn, pstmt);
		}
		return result;
	}
	
}


/*
 	id              INT AUTO_INCREMENT PRIMARY KEY,  -- 기본 키 (자동 증가)
    name            VARCHAR(100) NOT NULL,          -- 회사 이름
    phone           VARCHAR(15) NOT NULL UNIQUE,    -- 회사 전화번호 (중복 방지)
    email           VARCHAR(100) NOT NULL UNIQUE,   -- 이메일 (로그인 ID)
    password        VARCHAR(255) NOT NULL,          -- 비밀번호 (해싱 필요)
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 날짜
    updated_at

*/
 
