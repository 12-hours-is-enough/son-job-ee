<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=42dot+Sans:wght@300..800&family=Noto+Sans+KR:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800&display=swap" rel="stylesheet">
    
    <title>구인자 회원가입</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f8f8f8;
            font-family: "Noto Sans KR", sans-serif;
        }
        .signup-container {
            text-align: center;
        }
        .input-box {
            display: block;
            width: 250px;
            padding: 10px;
            margin: 10px auto;
            border: none;
            border-radius: 5px;
            background-color: #f0f0f0;
        }
        .btn {
            display: block;
            width: 270px;
            padding: 10px;
            margin: 10px auto;
            background-color: #d3d3d3;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <div class="signup-container">
        <h2>구인자 회원가입</h2>
        <form action="signup?usertype=company" method="post">
        	<input type="email" class="input-box" name="companyEmail" placeholder="이메일 (아이디)" required>	
            <input type="text" class="input-box" name="companyName" placeholder="회사 이름" required>
            <input type="text" class="input-box" name="companyPhone" placeholder="회사 전화번호" required>
            <input type="password" class="input-box" name="companyPw" placeholder="비밀번호" required>
            <button type="submit" class="btn">회원가입</button>
        </form>
    </div>
</body>
</html>
