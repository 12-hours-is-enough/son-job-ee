<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

    <title>로그인</title>
    <style>
        /* 전체 페이지 중앙 정렬 */
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f8f8f8;
            font-family: "Noto Sans KR", sans-serif;
        }

        /* 로그인 컨테이너 */
        .login-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
        }

        h2 {
            font-size: 24px;
            margin-bottom: 20px;
            font-weight: bold;
        }

        /* 라디오 버튼 중앙 정렬 */
        .radio-group {
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 10px;
            margin-bottom: 20px;
        }

        .radio-group label {
            font-size: 14px;
            color: #666;
        }

        .radio-group input {
            margin-right: 5px;
        }

        /* 입력 필드 */
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
		
		.input-box {
		    flex: 1; /* 입력 필드가 남은 공간을 차지하도록 설정 */
		    padding: 12px;
		    border: none;
		    border-radius: 8px;
		    background-color: #f0f0f0;
		    font-size: 16px;
		    width: 100%; /* 부모 요소 크기에 맞춰 확장 */
		    max-width: 100%; /* 폼 너비에 맞춰 자동 확장 */
		}

        /* 로그인 버튼 */
        .login-btn {
            width: 300px;
            padding: 15px;
            font-size: 16px;
            font-weight: bold;
            background-color: #d3d3d3;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            margin-top: 10px;
        }

        .login-btn:hover {
            background-color: #c0c0c0;
        }

        /* 회원가입 링크 */
        .signup-link { 
            display: block;
            font-size: 14px;
            color: black;
            text-decoration: none;
            margin-top: 10px;
        }

        .signup-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>로그인</h2>
        
        <!-- login button 누르면 email, pw, usertype 넘기기 (/login 에 post로 넘길거임) -->
        
        <form action="login" method="post">
            <div class="radio-group">
                <label><input type="radio" name="userType" value="user" required> 구인자</label>
                <label><input type="radio" name="userType" value="company" required> 구직자</label>
            </div>
            <div class="form-group">
                <label for="userId">ID &nbsp;</label>
                <input type="text" id="userId" name="userId" class="input-box" required>
            </div>
            <div class="form-group">
                <label for="userPw">PW</label>
                <input type="password" id="userPw" name="userPw" class="input-box" required>
            </div>
            
            <button type="submit" class="login-btn">log in</button> 
        </form>
        <a href="signup" class="signup-link">회원가입</a>
        <a href="jobList.jsp" class="signup-link">job list</a>
    </div>
</body>
</html>
