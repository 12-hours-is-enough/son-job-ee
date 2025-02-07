<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>지원 현황</title>
    <style>
        body {
            display: flex;
            margin: 0;
            font-family: Arial, sans-serif;
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
        <h2 onclick="location.href='?'">⚙ Son-jab-ee</h2>
        <a href="jobList.jsp" class="nav-item">공고 리스트</a>
        <a href="myStatus.jsp" class="nav-item active">지원 현황</a>
        <a href="userPage.jsp" class="nav-item">마이 페이지</a>
        <a href="logout.jsp">로그아웃</a>
        <div class="user-info">구직자 🟢 홍길동님</div>
    </div>

    <div class="content">
        <h2 class="title">▶ 지원 현황</h2>

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
        </div>

        <div class="job-card">
            <div class="job-title">부업 구합니다.</div>
            <div class="job-time">시간: 9:00 - 17:00</div>
            <div class="job-company">
                <span>안경 공장</span>
                <span class="toggle-btn" onclick="toggleDetail(this)">▼</span>
            </div>
            <div class="job-detail">
                <p>경력: 무관</p>
                <p>위치: 대구</p>
                <p>하는 일: 포장 작업</p>
                <p>연락처: 02-1232-0934</p>
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
    </script>

</body>
</html>
