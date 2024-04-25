function toggleHeart(userId) {
    var image1 = document.getElementById('heartIcon');
    var image2 = document.getElementById('heartIcon_view');
    var isLike = image1.src.includes("empty_icon");

    // 이미지 상태를 업데이트합니다.
    var newSrc = isLike ? '/static/icons/heart_filled_icon.png' : '/static/icons/heart_empty_icon.png';
    image1.src = newSrc;
    image2.src = newSrc;

    // AJAX 요청으로 서버에 좋아요 상태 변경을 전송합니다.
    var action = isLike ? 'add' : 'remove'; // 좋아요 상태에 따라 액션 결정
    fetch('/userlike/' + action, { // URL을 좋아요 추가/삭제에 따라 동적으로 변경
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            // 추가 헤더가 필요하면 여기에 추가하세요. 예를 들어 CSRF 토큰 등
        },
        body: JSON.stringify({
            userId: userId // 이 예제에서는 projectId는 제외합니다.
        })
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            console.log('Like ' + action + 'ed: ', data);
            // 필요하면 여기에 추가 로직을 구현할 수 있습니다.
        })
        .catch(error => {
            console.error('Failed to ' + action + ' like:', error);
        });
}
