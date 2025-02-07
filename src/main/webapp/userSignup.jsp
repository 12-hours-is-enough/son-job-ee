<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=42dot+Sans:wght@300..800&family=Noto+Sans+KR:wght@100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
	
    <title>구직자 회원가입</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f8f8f8;
		    font-family: "Noto Sans KR", serif;
		    font-optical-sizing: auto;
		    font-weight: 500;
		    font-style: normal;
        }
        .signup-container {
            text-align: center;
        }
        h2 {
            font-size: 24px;
            margin-bottom: 20px;
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
        .form-group {
            display: flex;
            flex-direction: row;
            align-items: center;
            margin-bottom: 10px;
            width: 100%;
            max-width: 350px; /* 폼 전체 너비 */
        }
        
		.form-group label {
		    font-size: 16px;
		    font-weight: bold;
		    margin-right: 10px; /* 오른쪽 여백 줄임 */
		    white-space: nowrap; /* 텍스트가 줄 바뀌지 않도록 설정 */
		}
    </style>
</head>
<body>
    <div class="signup-container">
        <h2>구직자 회원가입</h2>
        <form action="signup?usertype=user" method="post">
        
            <div class="form-group">
           		<label for="userId">아이디 &nbsp;</label>
            	<input type="text" class="input-box" name="userId" placeholder="아이디" required>
           	</div>
           	
            <div class="form-group">
           		<label for="userId">비밀번호 &nbsp;</label>
            	<input type="password" class="input-box" name="userPw" placeholder="비밀번호" required>
            </div>
            
            
            <div class="form-group">
           		<label for="userId">이름 &nbsp;</label>
            	<input type="text" class="input-box" name="userName" placeholder="이름" required>
            </div>
            
            
            <div class="form-group">
           		<label for="userId">이메일 &nbsp;</label>
            	<input type="email" class="input-box" name="userEmail" placeholder="이메일" required>
            </div>
            
            
            <div class="form-group">
           		<label for="userId">전화번호 &nbsp;</label>
            	<input type="text" class="input-box" name="userPhone" placeholder="전화번호" required>
            </div>
            
            
            <div class="form-group">
           		<label for="userId">생년월일 &nbsp;</label>
            	<input type="date" class="input-box" name="userBirth" required>
            </div>
            
            
            <div class="radio-group">
                <label><input type="radio" name="userGender" value="female"> 여자</label>
                <label><input type="radio" name="userGender" value="male"> 남자</label>
            </div>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
            
            
            <textarea class="input-box" name="userExperience" placeholder="경력사항"></textarea>
            
            
            <button type="submit" class="btn">회원가입</button>
        </form>
    </div>
</body>
</html>
