//에스프레소 메뉴창 기본값 설정
var def = document.getElementById("default");
if(def){
  def.click();
}

//메뉴 둘러보기 기능
function openMenu(tabId, button) {
      var i, tabcontent, tablinks;
      //모든 컨텐츠 숨기기
      tabcontent = document.getElementsByClassName("content");
      for (i = 0; i < tabcontent.length; i++) {
          tabcontent[i].style.display = "none";
      }

      //모든 버튼에서 배경색 제거
      tablinks = document.getElementsByClassName("tab");
      for (i = 0; i < tablinks.length; i++) {
          tablinks[i].style.backgroundColor = 'rgb(218, 198, 185';
      }

      //해당 탭 메뉴창 출력 + 버튼 배경색 변경
      document.getElementById(tabId).style.display = "block";
      button.style.backgroundColor = "#f7f4eb";
  }

  //장바구니 기능

  function addCart(product, price){
    var item ={
      name : product,
      price : price
    };
    // 장바구니추가
    var cart = document.querySelector('.cart');
    var itemEl = document.createElement('div');
    itemEl.textContent = item.name + '  ' + item.price + '원';
    cart.appendChild(itemEl);

    //최종금액 추가 
    var total = 0;
    var items = cart.querySelectorAll('div');
    items.forEach(function(item){
      var itemText = item.textContent;
      var itemPrice = parseInt(itemText.substring(itemText.lastIndexOf(' ') + 1, itemText.lastIndexOf('원')));
      total += itemPrice;
      });
    var pay = document.querySelector("#total");
    pay.textContent = " " + total + "원";
  }

  //장바구니 & 총 결제금액 삭제 기능
  function del(){
    var cart = document.querySelector('.cart');
    var total = document.querySelector('#total');

    while(cart.firstChild){ //장바구니 삭제
      cart.removeChild(cart.firstChild);
    } if(total.lastChild){ //총 결제금액 삭제
      total.removeChild(total.lastChild);
    }
  }

  console.log(isNaN(total));

  //결제처리 모달창
  function pay(){
    var totalElement = document.getElementById("total");
    var totalText = totalElement.textContent.replace("원", "");
    var total = Number(totalText);

    Swal.fire({
      title: '결제하시겠습니까?',
      text: '총 결제금액: ' + total + '원',
      icon: 'question',
      
      showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
      confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
      cancelButtonColor: 'rgb(255, 83, 83)', // cancel 버튼 색깔 지정
      confirmButtonText: '승인', // confirm 버튼 텍스트 지정
      cancelButtonText: '취소', // cancel 버튼 텍스트 지정
      reverseButtons: true, // 버튼 순서 거꾸로
      
    }).then(result => {
      // 만약 Promise리턴을 받으면,
      if (result.isConfirmed) {
        Swal.fire('결제가 완료되었습니다.').then(() => {
            window.location.reload(); //페이지 초기화
        });
    }
});
};
