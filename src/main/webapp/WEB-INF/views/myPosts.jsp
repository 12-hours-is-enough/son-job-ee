<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <h2 onclick="location.href='uploadPost.jsp'">⚙ Son-jab-ee</h2>
        <a href="uploadPost.jsp" class="nav-item">공고 업로드</a>
        <a href="board" class="nav-item active">올린 공고</a>
        <a href="companyPage.jsp" class="nav-item">마이 페이지</a>
        <a href="logout">로그아웃</a>
        <div class="user-info">구인자 🏢 해바라기 학원님</div>
    </div>

    <!-- 메인 컨텐츠 -->
    <div class="content">
        <h2 class="title">▶ 올린 공고</h2>

        <!-- 공고 1 -->
        <div class="job-card">
            <div class="job-title">운전 기사 구합니다.</div>
            <div class="job-time">시간: 9:00 - 15:00</div>
            <div class="job-company">
                <span>해바라기 학원</span>
                <span class="toggle-btn" onclick="toggleDetail(this)">▼</span>
            </div>
            <div class="job-detail">
                <p>연락처: 02-1234-5678</p>
            </div>
            <div class="job-actions">
                <form action="modifyPost.jsp" method="GET">
                    <input type="hidden" name="postId" value="1">
                    <input type="hidden" name="companyName" value="해바라기 학원">
                    <input type="hidden" name="jobTitle" value="운전 기사 구합니다.">
                    <input type="hidden" name="jobDescription" value="운전 업무">
                    <input type="hidden" name="jobType" value="정규직">
                    <input type="hidden" name="salary" value="월 300만원">
                    <input type="hidden" name="location" value="서울">
                    <input type="hidden" name="priority" value="운전 면허 필수">
                    <button type="submit" class="edit-btn">수정</button>
                </form>
                <button class="delete-btn" onclick="deletePost(1)">삭제</button>
            </div>
        </div>

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

        function deletePost(postId) {
            let confirmDelete = confirm("정말로 이 공고를 삭제하시겠습니까?");
            if (confirmDelete) {
                window.location.href = "deletePost.jsp?postId=" + postId;
            }
        }
    </script>

</body>
</html>
