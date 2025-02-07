<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공고 수정</title>
    <style>
        body {
            display: flex;
            margin: 0;
            font-family: Arial, sans-serif;
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
        <h2>⚙ Son-jab-ee</h2>
        <a href="uploadPost.jsp" class="nav-item">공고 업로드</a>
        <a href="myPosts.jsp" class="nav-item active">올린 공고</a>
        <a href="companyPage.jsp" class="nav-item">마이 페이지</a>
        <a href="logout.jsp">로그아웃</a>
        <div class="user-info">구인자 🏢 해바라기 학원님</div>
    </div>

    <!-- 메인 컨텐츠 -->
    <div class="content">
        <h2 class="title">▶ 공고 수정</h2>

        <form action="modifyPostProcess.jsp" method="post" class="form-container">
            <!-- 공고 ID (수정 시 필요) -->
            <input type="hidden" name="postId" value="<%= request.getParameter("postId") %>">

            <div class="form-group">
                <label for="companyName">회사 이름</label>
                <input type="text" class="input-box" id="companyName" name="companyName" 
                       value="<%= request.getParameter("companyName") %>" required>
            </div>
            <div class="form-group">
                <label for="jobTitle">공고 제목</label>
                <input type="text" class="input-box" id="jobTitle" name="jobTitle" 
                       value="<%= request.getParameter("jobTitle") %>" required>
            </div>
            <div class="form-group">
                <label for="jobDescription">공고 내용</label>
                <input type="text" class="input-box" id="jobDescription" name="jobDescription" 
                       value="<%= request.getParameter("jobDescription") %>" required>
            </div>
            <div class="form-group">
                <label for="jobType">업무 유형</label>
                <input type="text" class="input-box" id="jobType" name="jobType" 
                       value="<%= request.getParameter("jobType") %>" required>
            </div>
            <div class="form-group">
                <label for="salary">급여</label>
                <input type="text" class="input-box" id="salary" name="salary" 
                       value="<%= request.getParameter("salary") %>" required>
            </div>
            <div class="form-group">
                <label for="location">지역</label>
                <input type="text" class="input-box" id="location" name="location" 
                       value="<%= request.getParameter("location") %>" required>
            </div>
            <div class="form-group">
                <label for="priority">우대사항</label>
                <input type="text" class="input-box" id="priority" name="priority" 
                       value="<%= request.getParameter("priority") %>">
            </div>

            <button type="submit" class="btn">공고 수정</button>
        </form>
    </div>

</body>
</html>
