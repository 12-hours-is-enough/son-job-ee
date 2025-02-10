function toggleDetail(btn) {
    let detail = btn.parentNode.nextElementSibling;
    if (detail.style.display === "block") {
        detail.style.display = "none";
        btn.textContent = "▼";
    } else {
        detail.style.display = "block";
        btn.textContent = "▲";
    }
}

function deletePost(jobId) {
    if (confirm("정말로 이 공고를 삭제하시겠습니까?")) {
        fetch('/son-job-ee/job/' + jobId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'action=delete'  // 본문에 action 파라미터로 delete 요청
        })
        .then(response => {
            if (response.ok) {
                alert("공고가 삭제되었습니다.");
                location.reload();  // 삭제 후 페이지 리로드
            } else {
                alert("삭제에 실패했습니다.");
            }
        })
        .catch(error => {
            console.error("삭제 요청 중 오류 발생:", error);
            alert("삭제 요청 중 오류가 발생했습니다.");
        });
    }
}