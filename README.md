<div align= "center">
    <img src="https://capsule-render.vercel.app/api?type=waving&color=ecf566&height=180&text=🤝%20손잡이&animation=&fontColor=ffffff&fontSize=50" />
    </div>
    
<br>

## 목차
1. [👥 팀 소개](#-team-12시간이-모자라)
2. [📋 프로젝트 개요](#-프로젝트-개요)
4. [🛠️ 기술스택](#%EF%B8%8F-기술스택)
5. [🏗️ 아키텍쳐](#%EF%B8%8F-아키텍쳐)
6. [💡 기능 소개](#-기능-소개)
7. [🖥️ 실행 화면](#%EF%B8%8F-실행-화면)
8. [🚀 트러블슈팅](#-트러블슈팅)
9. [📆 회고](#-회고)

<br>

## 👥 TEAM 12시간이 모자라
### 팀원 소개
<div align=center>


|<img src="https://avatars.githubusercontent.com/u/95984922?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/165532198?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/121565744?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/179544856?v=4" width="150" height="150"/>|
|:-:|:-:|:-:|:-:|
|나홍찬<br/>[@nahong_c](https://github.com/HongChan1412)|김소연<br/>[@ssoyeonni](https://github.com/ssoyeonni)|이은정<br/>[@eundeom](https://github.com/eundeom)|이은준<br/>[@2EunJun](https://github.com/2EunJun)|

</div>

<br><br>

## 📋 프로젝트 개요
**손잡이 (sonjobee)** 는 은퇴 후에도 활기차게 사회 활동에 참여하고자 하는 시니어들을 위한 맞춤형 플랫폼입니다. 이 웹사이트는 고령층이 쉽게 단기 일자리나 아르바이트를 찾을 수 있도록 돕고, 일손이 필요한 업체와 효율적으로 연결되는 것은 물론, 시니어 구직자들도 원하는 일자리를 보다 편리하게 검색하고 지원할 수 있도록 지원합니다.

<br>

### ◽ 목적
- 시니어들의 사회 참여 확대
- 시니어들에게 경제적 자립 기회 제공
- 단기 일자리 찾기 어려운 시니어들에게 실질적인 도움이 될 것.
- 시니어들이 근처에서 할 수 있는 간단한 아르바이트나 단기 일거리를 쉽게 찾을 수 있도록 지원

### ◽ 비즈니스 모델(수익화 전략)
- 매칭 서비스 기반 수수료
- 프리미엄 공고 (끌올 & 광고 기능)
- 기업 및 기관 대상 유료 채용 공고
- 배너 광고 및 후원 (B2B 및 공공기관 협력)
<br><br>

## 🛠️ 기술스택
<div align=center> 
    <img src="https://img.shields.io/badge/JSP-00205B?style=for-the-badge&logo=apache&logoColor=white"> 
    <img src="https://img.shields.io/badge/HTML-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
    <img src="https://img.shields.io/badge/CSS-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
    <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white"> 
    <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white"> 
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
    <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white"> 
    <img src="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white"> 
</div>

<br><br>

## 💡 기능 소개

구직자(User)
- 로그인
- 회원 가입
- 회원 정보 수정
- 회원 정보 삭제
- 공고 조회
- 공고 지원
- 공고 지원 현황 확인

구인자(Company)
- 로그인
- 회원 가입
- 회사 정보 수정
- 회사 정보 삭제
- 공고 등록
- 회사가 작성한 공고 조회
- 공고 삭제

<br><br>


## 🏗️ 아키텍쳐
```
web-project/
│── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/sonjobee/
│ │ │ ├── controller/ # 컨트롤러 (Servlet)
│ │ │ │ ├── BoardServlet.java
│ │ │ │ ├── CompanyServlet.java
│ │ │ │ ├── JobServlet.java
│ │ │ │ ├── LoginServlet.java
│ │ │ │ ├── LogoutServlet.java
│ │ │ │ ├── PostServlet.java
│ │ │ │ ├── SignUpServlet.java
│ │ │ │ ├── UserServlet.java
│ │ │ ├── dao/ # 데이터베이스 액세스 (DAO)
│ │ │ │ ├── CompanyDAO.java
│ │ │ │ ├── JobDAO.java
│ │ │ │ ├── UserDAO.java
│ │ │ ├── model/ # 데이터 모델 클래스
│ │ │ │ ├── Company.java
│ │ │ │ ├── Job.java
│ │ │ │ ├── User.java
│ │ │ ├── util/ # 유틸리티 (DB 연결, 환경 설정)
│ │ │ │ ├── DBConnection.java
│ │ │ │ ├── EnvConfig.java # 환경변수 로드 클래스
│ │ │ │ ├── TimestampConverter.java
│ │ ├── resources/
│ │ │ ├── config.properties # 환경설정 파일 (DB 정보 저장)
│ │ │ ├── .env # 환경변수 파일 (gitignore 대상)
│ │ ├── webapp/
│ │ │ ├── WEB-INF/
│ │ │ │ ├── web.xml
│ │ │ │ ├── views/ # JSP 파일 (화면)
│ │ │ │ │ ├── companyPage.jsp
│ │ │ │ │ ├── companySignup.jsp
│ │ │ │ │ ├── jobList.jsp
│ │ │ │ │ ├── login.jsp
│ │ │ │ │ ├── loginFail.jsp
│ │ │ │ │ ├── modifyPost.jsp
│ │ │ │ │ ├── myPosts.jsp
│ │ │ │ │ ├── myStatus.jsp
│ │ │ │ │ ├── uploadPost.jsp
│ │ │ │ │ ├── userPage.jsp
│ │ │ │ │ ├── userSignup.jsp
│ │ │ ├── assets/
│ │ │ │ ├── css/
│ │ │ │ │ ├── style.css
│ │ │ │ ├── js/
│ │ │ │ │ ├── script.js
│── .gitignore # .env 파일 제외
│── pom.xml # Maven 설정 파일
│── README.md
```

## 파일별 기능 소개
- ### controller
    - **BoardServlet** : 구직자(User)의 지원 내역 조회와 기업회원(Company)의 공고 현황 관리
    - **CompanyServlet** : 기업 정보 조회, 정보 수정, 계정 삭제
    - **JobServlet** : 공고 조회, 등록, 수정, 삭제
    - **LoginServlet** : 로그인 기능
    - **LogoutServlet** : 로그아웃 기능
    - **PostServlet** : 공고 등록 페이지로 이동
    - **SignUpServlet** : 회원 가입 처리
    - **UserServlet** : 정보 조회, 정보 수정, 지원 공고 업데이트, 계정 삭제

- ### dao
    - **CompanyDAO** : 기업 회원 관련 데이터베이스 작업 처리
    - **JobDAO** : 공고 관련 데이터베이스 작업 처리
    - **UserDAO** : 구직자 회원 관련 데이터베이스 작업 처리

- ### model
    - **Company** : 기업 정보를 저장하는 데이터 모델
    - **Job** : 공고 정보를 저장하는 데이터 모델
    - **User** : 구직자 정보를 저장하는 데이터 모델

- ### util
    - **DBConnection** : 데이터베이스 연결 생성 및 관리 유틸리티
    - **EnvConfig** : 환경변수 로드 클래스
    - **TimestampConverter** : 문자열을 timestamp 객체로 변환 유틸리티

- ### views(.jsp)
    - **companyPage** : 기업 회원 정보 조회
    - **companySignup** : 기업 회원 가입
    - **jobList** : 전체 공고 목록 조회
    - **login** : 로그인 화면
    - **loginFail** : 로그인 실패 화면
    - **modifyPost** : 기업 공고 수정
    - **myPosts** : 본인(기업)이 올린 공고 조회
    - **myStatus** : 본인(구직자)이 지원한 공고 조회
    - **uploadPost** : 공고 등록
    - **userPage** : 구직자 회원 정보 조회
    - **userSignup** : 구직자 회원 가입

      
<br><br>




## ✳ 추가할 기능(고도화)
- 로그인, 회원가입 비밀번호 암호화
- 지원 시 이메일 자동 전송

<br><br>

## 🚀 트러블슈팅
### Session에서 값을 Object로 전달해서 int와 String으로 받을 때 에러
방법 1: 명시적 형 변환 (Casting)
```
Integer intValue = (Integer) session.getAttribute("key");
String stringValue = (String) session.getAttribute("key");
```
방법 2: toString()을 사용하여 변환 후 처리
```
String stringValue = session.getAttribute("key").toString();
Integer intValue = Integer.parseInt(session.getAttribute("key").toString());
```
<br><br>

## 📆 회고
>이번 프로젝트를 통해 50~60대 시니어층을 대상으로 한 창업 아이템을 선정하고, 비즈니스 모델을 고민하며, 사용자 편의를 고려한 웹페이지 제작하였다. 이를 통해 특정 연령층을 고려한 기능 설계 및 창업과 서비스 설계의 중요성을 실감할 수 있었다.
