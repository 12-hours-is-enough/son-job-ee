package com.sonjobee.dao;

import com.sonjobee.util.DBConnection;

import java.sql.PreparedStatement;
import com.sonjobee.model.Job;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
}