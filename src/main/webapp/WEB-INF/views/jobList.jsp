<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.sonjobee.model.Job"%>
<%@page import="com.sonjobee.dao.JobDAO"%>
<%@ page import="java.util.List" %>

<%-- jobDAO 에서 데이터 받아오기 --%>
<%
	List<Job> jobs = (List<Job>) request.getAttribute("jobs");
	String name = (String) session.getAttribute("name");
%>

<!DOCTYPE html>
	<html lang="ko">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link rel="preconnect" href="https://fonts.googleapis.com">
	    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
	    
	    <title>공고 리스트</title>
	    <style>
	        body {
	            display: flex;
	            margin: 0;
	            font-family: "Noto Sans KR", sans-serif;
	            background-color: #f8f8f8;
	        }
	
	        /* 사이드바 스타일 */
	        .sidebar {
	            width: 200px;
	            background: #f8f8f8;
	            padding: 20px;
	            height: 100vh;
	            box-shadow: 2px 0px 5px rgba(0, 0, 0, 0.1);
	        }
	
	        .sidebar h2 {
	            font-size: 18px;
	            font-weight: bold;
	            margin-bottom: 20px;
	            cursor: pointer;
	        }
	
	        .sidebar a {
	            display: block;
	            padding: 10px;
	            margin-bottom: 5px;
	            text-decoration: none;
	            color: black;
	            border-radius: 5px;
	        }
	
	        .sidebar a.active {
	            background-color: lightblue;
	            font-weight: bold;
	        }
	
	        .user-info {
	            margin-top: 30px;
	            font-size: 14px;
	            color: #333;
	            font-weight: bold;
	        }
	
	        /* 메인 컨텐츠 스타일 */
	        .content {
	            flex: 1;
	            padding: 30px;
	        }
	
	        .title {
	            font-size: 20px;
	            font-weight: bold;
	            padding: 10px;
	            background-color: #d9e6fc;
	            border-radius: 10px;
	            display: inline-block;
	        }
	
	        /* 공고 카드 스타일 */
	        .job-card {
	            background: #eaeaea;
	            border-radius: 10px;
	            padding: 15px;
	            margin: 15px 0;
	            cursor: pointer;
	            transition: all 0.3s ease-in-out;
	        }
	
	        .job-card:hover {
	            background: #d5d5d5;
	        }
	
	        .job-title {
	            font-weight: bold;
	            font-size: 18px;
	        }
	
	        .job-time {
	            font-size: 14px;
	            margin-top: 5px;
	            color: #555;
	        }
	
	        .job-company {
	            display: flex;
	            justify-content: space-between;
	            margin-top: 10px;
	            font-size: 14px;
	            color: #666;
	        }
	
	        .toggle-btn {
	            font-size: 16px;
	            cursor: pointer;
	        }
	
	        /* 아코디언 스타일 */
	        .job-detail {
	            display: none;
	            padding: 10px;
	            margin-top: 10px;
	            border-top: 1px solid #ccc;
	            font-size: 14px;
	        }
	
	        /* 지원하기 버튼 스타일 */
	        .apply-btn {
	            display: block;
	            width: 100%;
	            max-width: 150px;
	            padding: 8px;
	            margin-top: 10px;
	            background-color: #4CAF50;
	            color: white;
	            border: none;
	            border-radius: 5px;
	            font-size: 14px;
	            font-weight: bold;
	            text-align: center;
	            cursor: pointer;
	            transition: 0.3s ease-in-out;
	        }
	
	        .apply-btn:hover {
	            background-color: #45a049;
	        }
	
	    </style>
	</head>
	<body>

    <!-- 사이드바 -->
    <div class="sidebar">
        <h2 onclick="location.href='job'">⚙ Son-jab-ee</h2>
        <a href="job" class="nav-item active">공고 리스트</a>
        <a href="board" class="nav-item">지원 현황</a>
        <a href="user" class="nav-item">마이 페이지</a>
        <a href="logout">로그아웃</a>
		<div class="user-info">구직자 🟢 <%= name %>님</div>
    </div>

    <!-- 메인 컨텐츠 -->
    <div class="content">
        <div class="content">
		    <h2 class="title">▶ 공고 리스트</h2>
		
		    <% for (Job job : jobs) { %>
		        <div class="job-card">
		            <div class="job-title"><%= job.getJobCategory() %></div>
		            <div class="job-time">시간: <%= job.getSchedule() %></div>
		            <div class="job-company">
		                <span><%= job.getLocation() %></span>
		                <span class="toggle-btn" onclick="toggleDetail(this)">▼</span>
		            </div>
		            <div class="job-detail">
		                <p>회사 ID: <%= job.getCompanyId() %></p>
		                <p>급여: <%= job.getSalary() %></p>
		                <p>추가 정보: <%= job.getAdditionalInfo() %></p>
		                <p>지원 마감일: <%= job.getApplicationDeadline() %></p>
		                <p>연락처: 02-1234-5678</p>
		                
		                <!-- jobId를 포함하여 지원 요청 -->
		                <button class="apply-btn" onclick="applyJob(<%= job.getId() %>)">지원하기</button>
		            </div>
		        </div>
		    <% } %>
		</div>

        <script>
		    function applyJob(jobId) {
		    	alert(1);
		    	
		    	let formData = new URLSearchParams();
		    	formData.append("action", "updateAppliedJob");
		    	formData.append("jobId", jobId);
		        console.log("지원하기 버튼 클릭됨! jobId:", jobId, "action: updateAppliedJob");
		        
		        fetch('/son-job-ee/user', {
		            method: 'POST', // POST 요청 전송
		            headers: {
		            	'Content-Type': 'application/x-www-form-urlencoded'
		            },
		            body: formData.toString() // URL 인코딩된 데이터 전송
		        })
		        .then(response => response.text()) // JSON 대신 TEXT로 받음
		        .then(data => {
		            console.log("서버 응답:", data);
		            if(!data) {
		            	alert("지원이 완료되었습니다."); // 응답 메시지 출력
		            }
		            else {
		            	alert("지원에 실패하였습니다."); // 응답 메시지 출력
		           	}
		            
		            window.location.href = "board"; // 지원 현황 페이지(myStatus.jsp)로 이동
		        })
		        .catch(error => console.error('Error:', error));
		    }
		</script>

    </div>

    <script>
        // 아코디언 토글 기능
        function toggleDetail(btn) {
            let detail = btn.parentNode.nextElementSibling; // job-detail 요소 가져오기
            if (detail.style.display === "block") {
                detail.style.display = "none";
                btn.textContent = "▼";
            } else {
                detail.style.display = "block";
                btn.textContent = "▲";
            }
        }
    </script>

</body>
</html>
