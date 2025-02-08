// 회원가입 버튼 클릭 시 userType 값이 선택되지 않았으면 경고
var signupLink = document.querySelector('.signup-link');
signupLink.addEventListener('click', function(event) {
    event.preventDefault(); // 기본 동작 막기
    var userTypeValue = document.querySelector('input[name="userType"]:checked');
    if (userTypeValue) {
        document.getElementById('userTypeHidden').value = userTypeValue.value;
        document.getElementById('signupForm').submit();
    } else {
        alert('회원가입 전에 구인자 또는 구직자 유형을 선택해주세요.');
    }
});
