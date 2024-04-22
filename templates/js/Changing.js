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

  $(document).ready(function (){

    $('#btnchange').click(function (event){
        //이벤트 중복제어
        event.preventDefault();

        if ($('#usernmae').val() == "") {
            alert('이름을 입력하시오.');
            return;
        }
        if ($('#userEmail').val() == "") {
            alert('이메일을 입력하시오.');
            return;
        }
        if ($('#userphone').val() == "") {
            alert('전화번호를 입력하시오.');
            return;
        }
        if ($('#userps').val() == "") {
            alert('비밀번호를 입력하시오.');
            return;
        }
        if ($('#userps').val() == $('#userpscheck')) {
            alert('비밀번호가 일치하지 않습니다.')
            return;
        }
    })
})
