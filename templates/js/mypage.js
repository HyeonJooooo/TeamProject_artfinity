  //기본탭설정
  window.onload = function() {
    var defaultTabButton = document.getElementById("default");
    Menu('tab1', defaultTabButton);
  };
  function Menu(tabId, button) {
    var i, changetab, tablinks;
    
    // 모든 탭 콘텐츠를 숨김
    changetab = document.getElementsByClassName("content");
    for (i = 0; i < changetab.length; i++) {
      changetab[i].style.display = "none";
    }
    
    // 모든 탭 버튼에서 활성화 클래스 제거
    tablinks = document.getElementsByClassName("tab");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].classList.remove("active");
    }
    
    // 현재 클릭된 탭 콘텐츠를 표시하고 해당 탭 버튼에 활성화 클래스 추가
    document.getElementById(tabId).style.display = "block";
    button.classList.add("active");
  }

  // //메뉴바 팝업

  //무드보드


document.querySelectorAll(".grid-nav").forEach(function(nav) {
  nav.addEventListener("click", function(event) {
      var contentGroup = event.target.closest(".gridgroup");
      var menu = contentGroup.querySelector(".menu");

      if (menu.style.display === "block") {
          menu.style.display = "none";
      } else {
          // 모든 메뉴를 닫은 후 현재 그룹의 메뉴를 엽니다.
          document.querySelectorAll(".menu").forEach(function(m) {
              m.style.display = "none";
          });
          menu.style.display = "block";
      }

      event.stopPropagation(); // 클릭 이벤트 전파 중지
  });
});

// 문서의 다른 곳을 클릭할 때 모든 메뉴를 닫습니다.
document.addEventListener("click", function() {
  document.querySelectorAll(".menu1").forEach(function(menu) {
      menu.style.display = "none";
  });
});



  function deleteboard() {
    // 삭제 기능 구현
    alert("삭제되었습니다.");
  }
  
  function editboard() {
    // 수정 기능 구현
    alert("수정할 수 있습니다.");
  }


  
  // 업로드프로젝트
document.querySelectorAll(".content-nav").forEach(function(nav) {
  nav.addEventListener("click", function(event) {
      var contentGroup = event.target.closest(".content-group");
      var menu = contentGroup.querySelector(".menu1");

      if (menu.style.display === "block") {
          menu.style.display = "none";
      } else {
          // 모든 메뉴를 닫은 후 현재 그룹의 메뉴를 엽니다.
          document.querySelectorAll(".menu1").forEach(function(m) {
              m.style.display = "none";
          });
          menu.style.display = "block";
      }

      event.stopPropagation(); // 클릭 이벤트 전파 중지
  });
});

// 문서의 다른 곳을 클릭할 때 모든 메뉴를 닫습니다.
document.addEventListener("click", function() {
  document.querySelectorAll(".menu1").forEach(function(menu) {
      menu.style.display = "none";
  });
});



  function deletecontent() {
    // 삭제 기능 구현
    alert("삭제되었습니다.");
  }
  function editcontent() {
    // 수정 기능 구현
    alert("수정할 수 있습니다.");
  }
  function inboard() {
    window.location.href = "다른 HTML 주소"; // 다른 HTML 주소로 이동
  }