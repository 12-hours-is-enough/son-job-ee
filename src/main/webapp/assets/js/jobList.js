function applyJob(jobId) {
	let formData = new URLSearchParams();
	formData.append("action", "updateAppliedJob");
	formData.append("jobId", jobId);
    
    fetch('/son-job-ee/user', {
        method: 'POST', // POST 요청 전송
        headers: {
        	'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formData.toString() // URL 인코딩된 데이터 전송
    })
    .then(response => response.text()) // JSON 대신 TEXT로 받음
    .then(data => {
        if(!data) {
        	alert("지원이 완료되었습니다."); // 응답 메시지 출력
        }
        else {
        	alert("지원에 실패하였습니다."); // 응답 메시지 출력
       	}
        window.location.href = "board"; // 지원 현황 페이지(myStatus.jsp)로 이동
    })
    .catch(error => console.error('Error:', error));
}

// 아코디언 토글 기능
function toggleDetail(btn) {
    let detail = btn.parentNode.nextElementSibling; // job-detail 요소 가져오기
    if (detail.style.display === "block") {
        detail.style.display = "none";
        btn.textContent = "▼";
    } else {
        detail.style.display = "block";
        btn.textContent = "▲";
    }
}