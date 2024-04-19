function openTextInput() {
    // 새로운 textarea 엘리먼트를 생성
    var newTextInput = document.createElement('textarea');
    newTextInput.classList.add('textInput');
    newTextInput.style.display = 'block';

    // 기존의 textInput 엘리먼트 바로 위에 새로운 입력창 삽입
    var existingTextInput = document.getElementById('textInput');
    existingTextInput.parentNode.insertBefore(newTextInput, existingTextInput);

    // 새로 생성된 입력창에 이벤트 리스너 등록
    newTextInput.addEventListener('input', function() {
        // 입력된 텍스트의 실제 높이를 고려하여 입력란의 높이를 조절
        newTextInput.style.height = 'auto'; // 높이를 자동으로 설정하여 초기화
        var computedStyle = window.getComputedStyle(newTextInput);
        var heightOffset = parseInt(computedStyle.paddingTop) + parseInt(computedStyle.paddingBottom);
        newTextInput.style.height = (newTextInput.scrollHeight - heightOffset) + 'px'; // 텍스트의 실제 높이로 설정
    });

    newTextInput.addEventListener('keydown', function(event) {
        if (event.key === 'Enter') {
            // 엔터 키를 눌렀을 때 줄바꿈 추가
            newTextInput.value += '\n';
            // 입력란의 높이 조절
            newTextInput.style.height = 'auto'; // 높이를 자동으로 설정하여 초기화
            var computedStyle = window.getComputedStyle(newTextInput);
            var heightOffset = parseInt(computedStyle.paddingTop) + parseInt(computedStyle.paddingBottom);
            newTextInput.style.height = (newTextInput.scrollHeight - heightOffset) + 'px'; // 텍스트의 실제 높이로 설정
            // Enter 키의 기본 동작 방지 (새 줄 생성 방지)
            event.preventDefault();
        }
    });
}

function handleFileSelect(event) {
    var files = event.target.files; // 선택된 파일 가져오기
}