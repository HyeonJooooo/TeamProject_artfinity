     // 폼 요소를 가져오기
    var loginForm = document.querySelector('form');

    // 폼 제출 이벤트를 가로채기
    loginForm.addEventListener('submit', async (event) => {
        event.preventDefault(); // 기본 제출 동작 방지

        // 이메일과 비밀번호 가져오기
        var email = loginForm.email.value;
        var password = loginForm.password.value;

        // 간단한 유효성 검사 (이메일이나 비밀번호가 비어 있는지 확인)
        if (!email || !password) {
            alert("이메일과 비밀번호를 모두 입력하세요.");
            return;
        }

        // 서버에 로그인 요청을 보냄 (Ajax를 사용)
        try {
            var response = await fetch('login_endpoint', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ email, password }),
            });

            var data = await response.json();

            // 서버로부터의 응답을 확인하여 로그인 성공/실패 처리
            if (response.ok) {
                // 로그인이 성공하면 홈페이지로 이동하거나 다른 조치를 취함
                window.location.href = 'home.html';
            } else {
                // 로그인 실패 시 에러 메시지 표시
                alert(data.message);
            }
        } catch (error) {
            console.error('로그인 요청 중 오류 발생:', error);
            alert('로그인 중 오류가 발생했습니다. 다음에 다시 시도하세요.');
        }
    });
   
   