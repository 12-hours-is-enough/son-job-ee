<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.sonjobee.dao.CompanyDAO"%>
<%@page import="com.sonjobee.model.Company"%>

<%
	//세션에서 로그인된 사용자의 ID 가져오기
	// Integer companyId = (Integer) session.getAttribute("id");
	Integer companyId = 1;
	//DAO 인스턴스 생성
	CompanyDAO companyDAO = new CompanyDAO();
	Company company = null;
	
	// 회사 정보 가져오기
	if (companyId != null) {
	    company = companyDAO.getOneCompany(companyId);
	}
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=42dot+Sans:wght@300..800&family=Noto+Sans+KR:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
	
    <title>마이 페이지</title>
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
            display: flex;
            flex-direction: column;
            align-items: flex-start; /* 제목을 왼쪽 정렬 */
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
            width: 350px;
            display: flex;
            flex-direction: column;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            margin-bottom: 10px;
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
        <h2 onclick="location.href='uploadPost.jsp'">⚙ Son-jab-ee</h2>
        <a href="uploadPost.jsp" class="nav-item">공고 업로드</a>
        <a href="myPosts.jsp" class="nav-item">올린 공고</a>
        <a href="companyPage.jsp" class="nav-item active">마이 페이지</a>
        <a href="logout.jsp">로그아웃</a>
        <div class="user-info">구인자 🏢 해바라기 학원님</div>
    </div>

    <!-- 메인 컨텐츠 -->
    <div class="content">
        <h2 class="title">▶ 마이페이지</h2>

        <form action="companyUpdateProcess.jsp" method="post" class="form-container">
            <div class="form-group">
                <label for="companyId">아이디</label>
                <input type="text" class="input-box" id="companyId" name="companyId" 
                       placeholder="회사 아이디" value="<%= (company != null) ? company.getId() : "" %>" readonly>
            </div>
            <div class="form-group">
                <label for="companyPw">비밀번호</label>
                <input type="password" class="input-box" id="companyPw" name="companyPw" 
                       placeholder="비밀번호" value="<%= (company != null) ? company.getPassword() : "" %>" required>
            </div>
            <div class="form-group">
                <label for="companyName">회사 이름</label>
                <input type="text" class="input-box" id="companyName" name="companyName" 
                       placeholder="회사 이름" value="<%= (company != null) ? company.getName() : "" %>" required>
            </div>
            <div class="form-group">
                <label for="companyEmail">이메일</label>
                <input type="email" class="input-box" id="companyEmail" name="companyEmail" 
                       placeholder="회사 이메일" value="<%= (company != null) ? company.getEmail() : "" %>" required>
            </div>
            <div class="form-group">
                <label for="companyPhone">전화번호</label>
                <input type="text" class="input-box" id="companyPhone" name="companyPhone" 
                       placeholder="회사 전화번호" value="<%= (company != null) ? company.getPhone() : "" %>" required>
            </div>
            <button type="submit" class="btn">수정</button>
        </form>
    </div>

</body>
</html>
