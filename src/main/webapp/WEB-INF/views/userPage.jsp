<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.sonjobee.dao.UserDAO"%>
<%@page import="com.sonjobee.model.User"%>


<%
	//세션에서 로그인된 사용자의 ID 가져오기
	Integer userId = (Integer) session.getAttribute("id");
	String name = (String) session.getAttribute("name");
	User user = (User) request.getAttribute("user");
%>

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
        <h2 onclick="location.href='job'">⚙ Son-jab-ee</h2>
        <a href="job" class="nav-item">공고 리스트</a>
        <a href="board" class="nav-item">지원 현황</a>
        <a href="mypage" class="nav-item active">마이 페이지</a>
        <a href="logout">로그아웃</a>
        <div class="user-info">구직자 🟢 <%= name %>님</div>
    </div>

    <!-- 메인 컨텐츠 -->
    <div class="content">
        <h2 class="title">▶ 마이페이지</h2>
		
        <form action="user" method="post" class="form-container">
            <div class="form-group">
                <label for="userEmail">이메일</label>
                <input type="email" class="input-box" id="userEmail" name="email" 
                       placeholder="이메일" value="<%= (user != null) ? user.getEmail() : "" %>" required>
            </div>
            <div class="form-group">
                <label for="userPw">비밀번호</label>
                <input type="password" class="input-box" id="userPw" name="password" 
                       placeholder="비밀번호" value="<%= (user != null) ? user.getPassword() : "" %>" required>
            </div>
            <div class="form-group">
                <label for="userName">이름</label>
                <input type="text" class="input-box" id="userName" name="name" 
                       placeholder="이름" value="<%= (user != null) ? user.getName() : "" %>" required>
            </div>
            <div class="form-group">
                <label for="userPhone">전화번호</label>
                <input type="text" class="input-box" id="userPhone" name="phone" 
                       placeholder="전화번호" value="<%= (user != null) ? user.getPhone() : "" %>" required>
            </div>
            <div class="form-group">
                <label for="userBirth">생년월일</label>
                <input type="date" class="input-box" id="userBirth" name="birthDate" 
                       value="<%= (user != null) ? user.getBirthDate() : "" %>" required>
            </div>

            <div class="form-group"> 
                <label>성별</label>
                <div class="radio-group">
                    <label><input type="radio" name="gender" value="F" 
                                  <%= (user != null && "F".equals(user.getGender())) ? "checked" : "" %> > 여자</label>
                    <label><input type="radio" name="gender" value="M" 
                                  <%= (user != null && "M".equals(user.getGender())) ? "checked" : "" %> > 남자</label>
                </div>
            </div>
            
            
            <div class="form-group">
                <label for="preferredSchedule">선호 날짜 &nbsp;</label>
                <select class="input-box" name="preferredDate" required>
                    <option value="평일">평일</option>
                    <option value="주말">주말</option>
                    <option value="상관없음">상관없음</option>

                </select>
            </div>

            <div class="form-group">
                <label for="preferredJobCategory">선호 직종 &nbsp;</label>
                <select class="input-box" name="preferredJob" required>
                    <option value="경비">경비</option>
                    <option value="운전">운전</option>
                    <option value="배달">배달</option>
                    <option value="청소">청소</option>
                    <option value="강사">강사</option>
                </select>
            </div>

            <div class="form-group">
                <label for="preferredLocation">선호 지역 &nbsp;</label>
                <select class="input-box" name="preferredLocation" required>
                    <option value="서울">서울</option>
                    <option value="부산">부산</option>
                    <option value="대구">대구</option>
                    <option value="인천">인천</option>
                    <option value="광주">광주</option>
                </select>
            </div>
            

            <div class="form-group">
                <label for="experience">경력사항</label>
                <textarea class="input-box" id="userExperience" name="userExperience"
                          placeholder="경력사항"><%= (user != null) ? user.getExperience() : "" %></textarea>
            </div>

            <button type="submit" class="btn">수정</button>
        </form>
    </div>

</body>
</html>
