<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sonjobee.model.Job" %>
<%@ page import="java.util.List" %>
<% 
	List<Job> jobList = (List<Job>) request.getAttribute("jobList"); 
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
    <title>올린 공고</title>
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
            transition: all 0.3s ease-in-out;
            position: relative;
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

        .job-actions {
            display: flex;
            justify-content: flex-end;
            margin-top: 10px;
        }

        .edit-btn, .delete-btn {
            padding: 8px 12px;
            font-size: 14px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-left: 10px;
        }

        .edit-btn {
            background-color: #4CAF50;
            color: white;
        }

        .delete-btn {
            background-color: #f44336;
            color: white;
        }
    </style>
</head>
<body>

    <!-- 사이드바 -->
    <div class="sidebar">
        <h2 onclick="location.href='board'">⚙ Son-jab-ee</h2>
        <a href="post" class="nav-item">공고 업로드</a>
        <a href="board" class="nav-item active">올린 공고</a>
        <a href="user" class="nav-item">마이 페이지</a>
        <a href="logout">로그아웃</a>
        <div class="user-info">구인자 🏢 <%= name %>님</div>
    </div>

    <!-- 메인 컨텐츠 -->
    <div class="content">
        <h2 class="title">▶ 올린 공고</h2>

        <%-- jobList가 null이 아닌 경우 반복문을 돌려 공고 출력 --%>
        <% if (jobList != null) { %>
            <% for (Job job : jobList) { %>
                <div class="job-card">
                    <div class="job-title"><%= job.getJobTitle() %></div>
                    <div class="job-time">근무 일정: <%= job.getSchedule() %></div>
                    <div class="job-company">
                        <span class="toggle-btn" onclick="toggleDetail(this)">▼</span>
                    </div>
                    <div class="job-detail">
                        <p>공고 내용: <%= job.getJobContent() %></p>
                        <p>급여: <%= job.getSalary() %></p>
                        <p>위치: <%= job.getLocation() %></p>
                        <p>기타 정보: <%= job.getAdditionalInfo() %></p>
                        <p>기한: <%= job.getApplicationDeadline() %></p>
                    </div>
                    <div class="job-actions">
    					<button class="edit-btn" onclick="window.location.href='board/<%= job.getId() %>'">수정</button>
                        <button class="delete-btn" onclick="deletePost(<%= job.getId() %>)">삭제</button>
                    </div>
                </div>
            <% } %>
        <% } else { %>
            <p>올린 공고가 없습니다.</p>
        <% } %>

    </div>

	<script src="assets/js/mypost.js"></script>  

</body>
</html>
