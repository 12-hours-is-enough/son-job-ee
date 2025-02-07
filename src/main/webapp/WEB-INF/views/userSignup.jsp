<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    
    <title>구직자 회원가입</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f8f8f8;
            font-family: "Noto Sans KR", sans-serif;
        }

        .signup-container {
            text-align: center;
        }

        h2 {
            font-size: 24px;
            margin-bottom: 20px;
        }

        .form-group {
            text-align: left;
            margin-bottom: 10px;
        }

        .form-group label {
            display: block;
            font-size: 14px;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .input-box {
            display: block;
            width: 270px;
            padding: 10px;
            margin: 5px auto;
            border: none;
            border-radius: 5px;
            background-color: #f0f0f0;
            font-size: 16px;
        }

        .btn {
            display: block;
            width: 290px;
            padding: 10px;
            margin: 10px auto;
            background-color: #d3d3d3;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            font-size: 16px;
        }

        .radio-group {
            display: flex;
            justify-content: center;
            margin-top: 10px;
        }

        .radio-group label {
            margin: 0 10px;
            font-size: 14px;
            color: #666;
        }

        .radio-group input {
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <div class="signup-container">
        <h2>구직자 회원가입</h2>
        <form action="userSignupProcess.jsp" method="post">
            <div class="form-group">
                <label for="userId">아이디</label>
                <input type="text" id="userId" name="userId" class="input-box" placeholder="아이디" required>
            </div>
            <div class="form-group">
                <label for="userPw">비밀번호</label>
                <input type="password" id="userPw" name="userPw" class="input-box" placeholder="비밀번호" required>
            </div>
            <div class="form-group">
                <label for="userName">이름</label>
                <input type="text" id="userName" name="userName" class="input-box" placeholder="이름" required>
            </div>
            <div class="form-group">
                <label for="userEmail">이메일</label>
                <input type="email" id="userEmail" name="userEmail" class="input-box" placeholder="이메일" required>
            </div>
            <div class="form-group">
                <label for="userPhone">전화번호</label>
                <input type="text" id="userPhone" name="userPhone" class="input-box" placeholder="전화번호" required>
            </div>
            <div class="form-group">
                <label for="userBirth">생년월일</label>
                <input type="date" id="userBirth" name="userBirth" class="input-box" required>
            </div>
            <div class="form-group">
                <label>성별</label>
                <div class="radio-group">
                    <label><input type="radio" name="userGender" value="female"> 여자</label>
                    <label><input type="radio" name="userGender" value="male"> 남자</label>
                </div>
            </div>
            <div class="form-group">
                <label for="userExperience">경력사항</label>
                <textarea id="userExperience" name="userExperience" class="input-box" placeholder="경력사항"></textarea>
            </div>
            <button type="submit" class="btn">회원가입</button>
        </form>
    </div>
</body>
</html>
