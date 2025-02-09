<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.sonjobee.model.Job"%>
<%@page import="com.sonjobee.dao.JobDAO"%>
<%@ page import="java.util.List" %>
<%@ page import= "jakarta.servlet.http.HttpSession"%>

<%-- 지원 현황 
		내가 지원한 공고 jobDAO에서 데이터 불러오기
 --%>
<%
	List<Job> appliedJobs = (List<Job>) request.getAttribute("appliedJobs");
	String name = (String) session.getAttribute("name");
%>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=42dot+Sans:wght@300..800&family=Noto+Sans+KR:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
	
    <title>지원 현황</title>
    <style>
        body {
            display: flex;
            margin: 0;
		    font-family: "Noto Sans KR", serif;
		    font-optical-sizing: auto;
		    font-weight: 500;
		    font-style: normal;
            background-color: #f8f8f8;
        }

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

        .job-detail {
            display: none;
            padding: 10px;
            margin-top: 10px;
            border-top: 1px solid #ccc;
            font-size: 14px;
        }

    </style>
</head>
<body>

    <div class="sidebar">
        <h2 onclick="location.href='job'">⚙ Son-jab-ee</h2>
        <a href="job" class="nav-item">공고 리스트</a>
        <a href="board" class="nav-item active">지원 현황</a>
        <a href="user" class="nav-item">마이 페이지</a>
        <a href="logout">로그아웃</a>
		<div class="user-info">구직자 🟢 <%= name %>님</div>
    </div>

    <div class="content">
        <h2 class="title">▶ 지원 현황</h2>

        <% if (appliedJobs.isEmpty()) { %>
            <p>지원한 공고가 없습니다.</p>
        <% } else { %>
            <% for (Job job : appliedJobs) { %>
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
                    </div>
                </div>
            <% } %>
        <% } %>

    </div>
    
     <script>
        function toggleDetail(btn) {
            let detail = btn.parentNode.nextElementSibling;
            if (detail.style.display === "block") {
                detail.style.display = "none";
                btn.textContent = "▼";
            } else {
                detail.style.display = "block";
                btn.textContent = "▲";
            }
        }
    </script>

    <script>
        function toggleDetail(btn) {
            let detail = btn.parentNode.nextElementSibling;
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
