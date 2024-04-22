function changeimg(event) {
    const fileInput = event.target; // 이벤트가 발생한 요소
    const previewImg = document.getElementById("previewImg");
  
    const file = fileInput.files[0]; // 선택한 파일
    const reader = new FileReader();
  
    reader.onload = function(event) {
      previewImg.src = event.target.result; // 선택한 파일을 미리보기 이미지로 설정
    };
  
    if (file) {
      reader.readAsDataURL(file); // 파일을 읽어 데이터 URL로 변환
    }
  }