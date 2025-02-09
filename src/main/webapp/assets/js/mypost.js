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

function deletePost(postId) {
    let confirmDelete = confirm("정말로 이 공고를 삭제하시겠습니까?");
    if (confirmDelete) {
        window.location.href = "job?postId=" + postId;
    }
}
