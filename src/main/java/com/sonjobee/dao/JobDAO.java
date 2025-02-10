package com.sonjobee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sonjobee.model.Job;
import com.sonjobee.model.User;
import com.sonjobee.util.DBConnection;

public class JobDAO {
	/*
	 * - 공고 전체 데이터 가져오기 
	 * - 공고 세부정보 가져오기 
	 * - 한 개 공고 등록하기
	 * - 한 개 공고 수정하기 
	 * - 한 개 공고 삭제하기 
	 */

	// get all job data
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
				job.setJobTitle(rs.getString("job_title"));
				job.setJobContent(rs.getString("job_content"));
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

    // 특정 사용자가 지원한 공고 리스트 가져오기
	public List<Job> getAppliedJobs(int userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
        List<Job> appliedJobs = new ArrayList<>();

        List<Integer> jobIds = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			
			// step1
			// 지원한 job_id 목록 가져오기
			pstmt = conn.prepareStatement("SELECT applied_job_ids FROM users WHERE id = ?");
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				List<Integer> tempList = User.convertJsonToIntegerList(rs.getString("applied_job_ids"));
				if (tempList != null) {
			        jobIds.addAll(tempList); // 리스트 전체 추가
			    }
			}
			
			// step2 - 가져온 jobIds로 jobs 테이블에서 데이터 조회
			 if (!jobIds.isEmpty()) {
		            // 🔹 SQL IN 절을 사용하여 한 번에 조회
		            String sql = "SELECT * FROM jobs WHERE id IN (" + 
		                         jobIds.stream().map(id -> "?").collect(Collectors.joining(",")) + ")";

		            pstmt = conn.prepareStatement(sql);
		            int index = 1;
		            for (int jobId : jobIds) {
		                pstmt.setInt(index++, jobId);
		            }

		            rs = pstmt.executeQuery();
		            while (rs.next()) {
		                Job job = new Job();
		                job.setId(rs.getInt("id"));
		                job.setCompanyId(rs.getInt("company_id"));
						job.setJobTitle(rs.getString("job_title"));
						job.setJobContent(rs.getString("job_content"));
		                job.setLocation(rs.getString("location"));
		                job.setJobCategory(rs.getString("job_category"));
		                job.setSalary(rs.getString("salary"));
		                job.setSchedule(rs.getString("schedule"));
		                job.setAdditionalInfo(rs.getString("additional_info"));
		                job.setApplicationDeadline(rs.getDate("application_deadline"));
		                job.setCreatedAt(rs.getTimestamp("created_at"));
		                job.setUpdatedAt(rs.getTimestamp("updated_at"));
		                appliedJobs.add(job);
		            }
		        }

			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while fetching appliedJobs", e);

		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return appliedJobs;
	}


	// get on job data - 공고 수정 시 필요
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

			if (rs.next()) {
				job = new Job();
				job.setId(rs.getInt("id"));
				job.setCompanyId(rs.getInt("company_id"));
				job.setJobTitle(rs.getString("job_title"));
				job.setJobContent(rs.getString("job_content"));
				job.setLocation(rs.getString("location"));
				job.setJobCategory(rs.getString("job_category"));
				job.setSalary(rs.getString("salary"));
				job.setSchedule(rs.getString("schedule"));
				job.setAdditionalInfo(rs.getString("additional_info"));
				job.setApplicationDeadline(rs.getDate("application_deadline"));
				job.setCreatedAt(rs.getTimestamp("created_at"));
				job.setUpdatedAt(rs.getTimestamp("updated_at"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while fetching DTOs", e);
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return job;
	}

	// get Company jobs 회사에서 올린 공고 리스트 확인
	public List<Job> getCompanyJob(int companyId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Job> jobs = new ArrayList<>();

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM jobs WHERE company_id=?");
			pstmt.setInt(1, companyId);

			rs = pstmt.executeQuery();
 
			while (rs.next()) {
				Job job = new Job();
				job.setId(rs.getInt("id"));
				job.setCompanyId(rs.getInt("company_id"));
				job.setJobTitle(rs.getString("job_title"));
				job.setJobContent(rs.getString("job_content"));
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
			throw new RuntimeException("Database error occurred while fetching company jobs", e);
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return jobs;
	}
	
	// 공고 생성하기
	public boolean createJob(Job job) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "INSERT INTO jobs (company_id, job_title, job_content, location, job_category, salary, schedule, additional_info, application_deadline, created_at, updated_at) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, job.getCompanyId());
			pstmt.setString(2, job.getJobTitle());
			pstmt.setString(3, job.getJobContent());;
			pstmt.setString(4, job.getLocation());
			pstmt.setString(5, job.getJobCategory());
			pstmt.setString(6, job.getSalary());
			pstmt.setString(7, Job.isValidSchedule(job.getSchedule()) ? job.getSchedule() : "상관없음"); // 유효성 검사
			pstmt.setString(8, job.getAdditionalInfo());
			pstmt.setDate(9, new Date(job.getApplicationDeadline().getTime()));

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0; // 성공적으로 삽입되면 true 반환

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while creating Job", e);
		} finally {
			DBConnection.close(conn, pstmt, null);
		}
	}

	// 공고 수정하기
	public boolean updateJobInfo(int jobId, Job job) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE jobs SET job_title = ?, job_content = ?, location = ?, job_category = ?, salary = ?, schedule = ?, "
					+ "additional_info = ?, application_deadline = ?, updated_at = NOW() WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, job.getJobTitle());
			pstmt.setString(2, job.getJobContent());
			pstmt.setString(3, job.getLocation());
			pstmt.setString(4, job.getJobCategory());
			pstmt.setString(5, job.getSalary());
			pstmt.setString(6, Job.isValidSchedule(job.getSchedule()) ? job.getSchedule() : "상관없음"); // 유효한 schedule 값이
																										// 아닌 경우 상관없음
			pstmt.setString(7, job.getAdditionalInfo());
			pstmt.setDate(8, new Date(job.getApplicationDeadline().getTime()));
			pstmt.setInt(9, jobId);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0; // 업데이트 성공 여부 반환

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while updating Job info", e);
		} finally {
			DBConnection.close(conn, pstmt, null);
		}
	}

	// 공고 삭제하기
	public boolean deleteJob(int jobId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "DELETE FROM jobs WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, jobId);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0; // 삭제 성공 시 true 반환

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Database error occurred while deleting Job", e);
		} finally {
			DBConnection.close(conn, pstmt, null);
		}
	}
}


