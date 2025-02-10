<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // 세션에서 구인자 이름 가져오기
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
	
    <title>공고 업로드</title>
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
            text-align: center;
        }

        .title {
            font-size: 20px;
            font-weight: bold;
            padding: 10px;
            background-color: #d9e6fc;
            border-radius: 10px;
            display: inline-block;
        }

        /* 입력 폼 스타일 */
        .form-container {
            width: 350px;
            margin: 20px auto;
        }

        .form-group {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 10px;
        }

        .form-group label {
            font-size: 16px;
            font-weight: bold;
            text-align: right;
            width: 100px;
        }

        .input-box {
            width: 220px;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #f0f0f0;
        }

        .btn {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #d3d3d3;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 15px;
        }
    </style>
</head>
<body>

    <!-- 사이드바 -->
    <div class="sidebar">
        <h2 onclick="location.href='board'">⚙ Son-jab-ee</h2>
        <a href="post" class="nav-item active">공고 업로드</a>
        <a href="board" class="nav-item">올린 공고</a>
        <a href="mypage" class="nav-item">마이 페이지</a>
        <a href="logout">로그아웃</a>
        <div class="user-info">구인자 🏢 <%= name %>님</div>
    </div>

    <!-- 메인 컨텐츠 -->
    <div class="content">
        <h2 class="title">▶ 공고업로드</h2>

        <form action="job" method="post" class="form-container">
            <div class="form-group">
                <label for="jobTitle">공고 제목</label>
                <input type="text" class="input-box" id="jobTitle" name="jobTitle" required>
            </div>
            <div class="form-group">
                <label for="jobContent">공고 내용</label>
                <input type="text" class="input-box" id="jobContent" name="jobContent" required>
            </div>
            <div class="form-group">
                <label for="jobCategory">업무 유형</label>
                <input type="text" class="input-box" id="jobCategory" name="jobCategory" required>
            </div>
            <div class="form-group">
                <label for="salary">급여</label>
                <input type="text" class="input-box" id="salary" name="salary" required>
            </div>
            <div class="form-group">
                <label for="schedule">근무일정</label>
                <input type="text" class="input-box" id="schedule" name="schedule" required>
            </div>
            <div class="form-group">
                <label for="location">지역</label>
                <input type="text" class="input-box" id="location" name="location" required>
            </div>
            <div class="form-group">
                <label for="additionalInfo">기타정보</label>
                <input type="text" class="input-box" id="additionalInfo" name="additionalInfo">
            </div>
            
			<div class="form-group">
           		<label for="applicationDeadline">기한날짜 &nbsp;</label>
            	<input type="date" class="input-box" name="applicationDeadline" required>
            </div>

            <button type="submit" class="btn">공고 올리기</button>
        </form>
    </div>

</body>
</html>
