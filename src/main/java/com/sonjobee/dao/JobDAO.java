package com.sonjobee.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sonjobee.model.Job;
import com.sonjobee.model.JobDetail;
import com.sonjobee.model.JobSimple;
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

			while (rs.next()) {
				job = new Job();
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
	public static boolean createJob(Job job) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "INSERT INTO jobs (company_id, location, job_category, salary, schedule, additional_info, application_deadline, created_at, updated_at) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, job.getCompanyId());
			pstmt.setString(2, job.getLocation());
			pstmt.setString(3, job.getJobCategory());
			pstmt.setString(4, job.getSalary());
			pstmt.setString(5, Job.isValidSchedule(job.getSchedule()) ? job.getSchedule() : "상관없음"); // 유효성 검사
			pstmt.setString(6, job.getAdditionalInfo());
			pstmt.setDate(7, new Date(job.getApplicationDeadline().getTime()));

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
	public static boolean updateJobInfo(int jobId, Job job) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBConnection.getConnection();
			String sql = "UPDATE jobs SET location = ?, job_category = ?, salary = ?, schedule = ?, "
					+ "additional_info = ?, application_deadline = ?, updated_at = NOW() WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, job.getLocation());
			pstmt.setString(2, job.getJobCategory());
			pstmt.setString(3, job.getSalary());
			pstmt.setString(4, Job.isValidSchedule(job.getSchedule()) ? job.getSchedule() : "상관없음"); // 유효한 schedule 값이
																										// 아닌 경우 상관없음
			pstmt.setString(5, job.getAdditionalInfo());
			pstmt.setDate(6, new Date(job.getApplicationDeadline().getTime()));
			pstmt.setInt(7, jobId);

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
	public static boolean deleteJob(int jobId) {
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
	
	// test 코드
//	public static void main(String[] args) {
//		JobDAO jobDAO = new JobDAO();
//
//        // 1️⃣ 공고 생성 테스트
//        Job newJob = new Job();
//        newJob.setCompanyId(1); // 회사 ID 설정 (실제 존재하는 값이어야 함)
//        newJob.setLocation("서울 강남구");
//        newJob.setJobCategory("IT 개발");
//        newJob.setSalary("연봉 5,000만원");
//        newJob.setSchedule("주말");  // ✅ 유효한 값
//        newJob.setAdditionalInfo("재택근무 가능");
//        newJob.setApplicationDeadline(Date.valueOf("2025-03-01")); // 오늘 날짜 기준
//
//        boolean isCreated = JobDAO.createJob(newJob);
//        System.out.println("✅ 공고 생성 결과: " + (isCreated ? "성공" : "실패"));
//
//        // 2️⃣ 전체 공고 조회 테스트
//        List<Job> allJobs = jobDAO.getAllJobs();
//        System.out.println("\n📌 전체 공고 목록");
//        for (Job job : allJobs) {
//            System.out.println("ID: " + job.getId() + ", 회사: " + job.getCompanyId() + ", 위치: " + job.getLocation());
//        }
//
//        // 3️⃣ 특정 공고 조회 테스트
//        int jobIdToFetch = 10; // 실제 존재하는 공고 ID 사용
//        Job fetchedJob = jobDAO.getOneJob(jobIdToFetch);
//        if (fetchedJob != null) {
//            System.out.println("\n🔍 조회된 공고");
//            System.out.println("회사 ID: " + fetchedJob.getCompanyId());
//            System.out.println("위치: " + fetchedJob.getLocation());
//            System.out.println("카테고리: " + fetchedJob.getJobCategory());
//            System.out.println("급여: " + fetchedJob.getSalary());
//            System.out.println("근무 일정: " + fetchedJob.getSchedule());
//            System.out.println("추가 정보: " + fetchedJob.getAdditionalInfo());
//        } else {
//            System.out.println("\n❌ 공고 조회 실패: 존재하지 않는 ID");
//        }
//
//        // 4️⃣ 특정 회사의 공고 목록 조회
//        int companyId = 1; // 실제 존재하는 회사 ID 사용
//        List<Job> companyJobs = jobDAO.getCompanyJob(companyId);
//        System.out.println("\n🏢 특정 회사의 공고 목록");
//        for (Job job : companyJobs) {
//            System.out.println("공고 ID: " + job.getId() + ", 위치: " + job.getLocation());
//        }
//
//        // 5️⃣ 간단한 공고 정보 조회
//        List<JobSimple> simpleJobs = jobDAO.getSimpleJobInfo();
//        System.out.println("\n📋 공고 간단 정보");
//        for (JobSimple job : simpleJobs) {
//            System.out.println("ID: " + job.getId() + ", 회사: " + job.getCompanyId() + ", 직무: " + job.getJobCategory());
//        }
//
//        // 6️⃣ 특정 공고의 세부 정보 조회
//        JobDetail jobDetails = jobDAO.getDetailJobInfo(jobIdToFetch);
//        System.out.println("\n📄 공고 세부 정보");
//        if (jobDetails != null) {
//          System.out.println("\n🔍 조회된 공고");
//          System.out.println("추가 정보: " + jobDetails.getAdditionalInfo());
//          System.out.println("마감 기한: " + jobDetails.getApplicationDeadline());
//          System.out.println("생성 시간: " + jobDetails.getCreatedAt());
//          System.out.println("최종 수정 시간: " + jobDetails.getUpdatedAt());
//      } else {
//          System.out.println("\n❌ 공고 조회 실패: 해당 공고에 대한 세부 정보가 없습니다.");
//      }
//
//        // 7️⃣ 공고 수정 테스트
//        Job updateJob = new Job();
//        updateJob.setLocation("부산 해운대구");
//        updateJob.setJobCategory("프론트엔드 개발");
//        updateJob.setSalary("연봉 6,000만원");
//        updateJob.setSchedule("평일");
//        updateJob.setAdditionalInfo("풀타임 근무");
//        updateJob.setApplicationDeadline(Date.valueOf("2025-04-01"));
//
//        boolean isUpdated = JobDAO.updateJobInfo(11, updateJob);
//        System.out.println("\n✏ 공고 수정 결과: " + (isUpdated ? "성공" : "실패"));
//
//        
//        // 8️⃣ 공고 삭제 테스트
//        int jobIdToDelete = jobIdToFetch; // 삭제할 공고 ID
//        boolean isDeleted = JobDAO.deleteJob(jobIdToDelete);
//        System.out.println("\n🗑 공고 삭제 결과: " + (isDeleted ? "성공" : "실패"));
//    }

}

/*
 * id INT AUTO_INCREMENT PRIMARY KEY, company_id INT NOT NULL, location
 * VARCHAR(255) NOT NULL, job_category VARCHAR(100) NOT NULL, salary
 * VARCHAR(100), schedule ENUM('평일', '주말', '상관없음') DEFAULT '상관없음',
 * additional_info TEXT, application_deadline DATE, created_at TIMESTAMP DEFAULT
 * CURRENT_TIMESTAMP, updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE
 * CURRENT_TIMESTAMP, FOREIGN KEY (company_id) REFERENCES companies(id) ON
 * DELETE CASCADE
 * 
 */
