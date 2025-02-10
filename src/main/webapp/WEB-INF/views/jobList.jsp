<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공고 리스트</title>
    <style>
        body {
            display: flex;
        }
        .sidebar {
            width: 200px;
            background: #f8f8f8;
            padding: 20px;
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
        }
        .content {
            flex: 1;
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="sidebar">
<<<<<<< Updated upstream
        <a href="jobList.jsp" class="nav-item active">공고 리스트</a>
        <a href="myStatus.jsp" class="nav-item">지원 현황</a>
        <a href="userPage.jsp" class="nav-item">마이 페이지</a>
        <a href="logout.jsp">로그아웃</a>
=======
        <h2 onclick="location.href='job'">⚙ Son-jab-ee</h2>
        <a href="job" class="nav-item active">공고 리스트</a>
        <a href="board" class="nav-item">지원 현황</a>
        <a href="mypage" class="nav-item">마이 페이지</a>
        <a href="logout">로그아웃</a>
		<div class="user-info">구직자 🟢 <%= name %>님</div>
>>>>>>> Stashed changes
    </div>
    <div class="content">
        <h2>공고 리스트</h2>
        <div class="job">
            <p><b>운전 기사 구합니다.</b></p>
            <p>시간: 9:00 - 15:00</p>
            <p>해바라기 학원 | 02-1234-5678</p>
        </div>
        <div class="job">
            <p><b>부업 구합니다.</b></p>
            <p>시간: 9:00 - 17:00</p>
            <p>안경 공장 | 02-1232-0934</p>
        </div>
    </div>

    <script>
        const navItems = document.querySelectorAll('.nav-item');
        navItems.forEach(item => {
            item.addEventListener('click', function() {
                navItems.forEach(nav => nav.classList.remove('active'));
                this.classList.add('active');
            });
        });
    </script>
</body>
</html>
