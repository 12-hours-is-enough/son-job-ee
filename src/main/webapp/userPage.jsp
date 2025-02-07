<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=42dot+Sans:wght@300..800&family=Noto+Sans+KR:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800&display=swap" rel="stylesheet">
    
    <title>마이 페이지</title>
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
            position: fixed;
            left: 0;
            top: 0;
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
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            margin-left: 220px;
        }

        .title {
            font-size: 20px;
            font-weight: bold;
            padding: 10px;
            background-color: #d9e6fc;
            border-radius: 10px;
            display: inline-block;
            margin-bottom: 20px;
        }

        /* 입력 폼 스타일 */
        .form-container {
            width: 100%;
            max-width: 400px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            margin-bottom: 10px;
            width: 100%;
        }

        .form-group label {
            font-size: 14px;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .input-box {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            background-color: #f0f0f0;
            text-align: center;
        }

        .btn {
            width: 100%;
            padding: 12px;
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
        <h2>⚙ Son-jab-ee</h2>
        <a href="jobList.jsp" class="nav-item">공고 리스트</a>
        <a href="myStatus.jsp" class="nav-item">지원 현황</a>
        <a href="userPage.jsp" class="nav-item active">마이 페이지</a>
        <a href="logout.jsp">로그아웃</a>
        <div class="user-info">구직자 🟢 홍길동님</div>
    </div>

    <!-- 메인 컨텐츠 -->
    <div class="content">
        <h2 class="title">▶ 마이페이지</h2>

        <form action="userUpdateProcess.jsp" method="post" class="form-container">
            <div class="form-group">
                <label for="userId">아이디</label>
                <input type="text" class="input-box" id="userId" name="userId" placeholder="사용자 아이디" value="hong123" required>
            </div>
            <div class="form-group">
                <label for="userPw">비밀번호</label>
                <input type="password" class="input-box" id="userPw" name="userPw" placeholder="비밀번호" value="password" required>
            </div>
            <div class="form-group">
                <label for="userName">이름</label>
                <input type="text" class="input-box" id="userName" name="userName" placeholder="이름" value="홍길동" required>
            </div>
            <div class="form-group">
                <label for="userEmail">이메일</label>
                <input type="email" class="input-box" id="userEmail" name="userEmail" placeholder="이메일" value="hong@email.com" required>
            </div>
            <div class="form-group">
                <label for="userPhone">전화번호</label>
                <input type="text" class="input-box" id="userPhone" name="userPhone" placeholder="전화번호" value="010-1234-5678" required>
            </div>
            <div class="form-group">
                <label for="userBirth">생년월일</label>
                <input type="date" class="input-box" id="userBirth" name="userBirth" value="1990-01-01" required>
            </div>

            <div class="form-group">
                <label>성별</label>
                <div class="radio-group">
                    <label><input type="radio" name="userGender" value="female"> 여자</label>
                    <label><input type="radio" name="userGender" value="male" checked> 남자</label>
                </div>
            </div>

            <div class="form-group">
                <label for="userExperience">경력사항</label>
                <textarea class="input-box" id="userExperience" name="userExperience" placeholder="경력사항">5년 경력 개발자</textarea>
            </div>

            <button type="submit" class="btn">수정</button>
        </form>
    </div>

</body>
</html>
