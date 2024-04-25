function deleteComment(commentId) {
    console.log("Deleting comment with ID:", commentId); // 로그 추가
    if (!confirm('댓글을 삭제하시겠습니까?')) {
        return;
    }
    var xhr = new XMLHttpRequest();
    xhr.open("POST", `/delete-comment?commentId=${commentId}`, true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            window.location.reload(); // 성공 시 페이지 새로고침
        } else {
            alert("댓글 삭제 실패");
        }
    };
    xhr.send();
}
