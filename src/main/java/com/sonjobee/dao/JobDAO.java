package com.sonjobee.dao;

import com.sonjobee.util.DBConnection;

import java.sql.PreparedStatement;
import com.sonjobee.model.Job;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;

public class JobDAO {
    // 전체 Job 목록 조회
    public List<Job> getAllJobs() {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Job> jobs = new ArrayList<>();

        try {
        	conn = DBConnection.getConnection();
        	pstmt = conn.prepareStatement("SELECT * FROM jobs");
        	rs = pstmt.executeQuery();
        
        	while (rs.next()) {
                Job job = new Job();
                job.setId(rs.getInt("id"));
                job.setCompanyId(rs.getInt("company_id"));
                job.setLocation(rs.getString("location"));
                job.setJobCategory(rs.getString("job_category"));
                job.setSalary(rs.getString("salary"));
                job.setSchedule(rs.getString("schedule"));
                job.setAdditionalInfo(rs.getString("additional_info"));
                job.setApplicationDeadline(rs.getDate("application_deadline"));
                job.setCreatedAt(rs.getTimestamp("created_at"));
                job.setUpdatedAt(rs.getTimestamp("updated_at"));
                jobs.add(job);
        	}
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new RuntimeException("Database error occurred while fetching DTOs", e);
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
        return jobs;
    }


    // get on job data
    public Job getOneJob(int jobId) {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Job job = null;
        
        try {
        	conn = DBConnection.getConnection();
        	pstmt = conn.prepareStatement("SELECT * FROM jobs WHERE id=?");
        	pstmt.setInt(1, jobId);
        	
        	rs = pstmt.executeQuery();
        	

        	while(rs.next()) {
        		Job jobs = new Job();
        		jobs.setId(rs.getInt("id"));
        		jobs.setCompanyId(rs.getInt("company_id"));
        		jobs.setLocation(rs.getString("location"));
        		jobs.setJobCategory(rs.getString("job_category"));
        		jobs.setSalary(rs.getString("salary"));

        	    String schedule = rs.getString("schedule");
        	    // schedule 값이 유효한지 체크하고 설정
        	    jobs.setSchedule(schedule);  // 유효성 검사를 `Job` 클래스의 setSchedule() 메서드에서 처리

        	    jobs.setAdditionalInfo(rs.getString("additional_info"));
        	    jobs.setApplicationDeadline(rs.getDate("application_deadline"));
        	    jobs.setCreatedAt(rs.getTimestamp("created_at"));
        	    jobs.setUpdatedAt(rs.getTimestamp("updated_at"));
        	    
        	}
    	} catch (SQLException e) {
    		e.printStackTrace();
            throw new RuntimeException("Database error occurred while fetching DTOs", e);
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
        return job;
    }
    
    // 공고 수정하기
    public static boolean updateJobInfo(int jobId, Job jobDto) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "UPDATE jobs SET location = ?, job_category = ?, salary = ?, schedule = ?, " +
                         "additional_info = ?, application_deadline = ?, updated_at = NOW() WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jobDto.getLocation());
            pstmt.setString(2, jobDto.getJobCategory());
            pstmt.setString(3, jobDto.getSalary());
            pstmt.setString(4, Job.isValidSchedule(jobDto.getSchedule()) ? jobDto.getSchedule() : "상관없음");	// 유효한 schedule 값이 아닌 경우 상관없음
            pstmt.setString(5, jobDto.getAdditionalInfo());
            pstmt.setDate(6, new Date(jobDto.getApplicationDeadline().getTime()));
            pstmt.setInt(7, jobId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  // 업데이트 성공 여부 반환

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database error occurred while updating Job info", e);
        } finally {
            DBConnection.close(conn, pstmt, null);
        }
    }
    
}


/*
 	id              INT AUTO_INCREMENT PRIMARY KEY, 
    company_id      INT NOT NULL,                  
    location        VARCHAR(255) NOT NULL,            
    job_category    VARCHAR(100) NOT NULL,            
    salary          VARCHAR(100),                     
    schedule        ENUM('평일', '주말', '상관없음') DEFAULT '상관없음',
    additional_info TEXT,                             
    application_deadline DATE,                        
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
    FOREIGN KEY (company_id) REFERENCES companies(id) ON DELETE CASCADE 
 */

