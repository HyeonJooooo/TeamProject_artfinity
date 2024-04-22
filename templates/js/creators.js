

// 가상 데이터 생성
let creators = [
    { name: "크리에이터1", image: "images/dog.png", likes: 100, views: 100, date: '2024-03-01', },
    { name: "크리에이터2", image: "images/dog.png", likes: 150, views: 120, date: '2024-04-10' },
    { name: "크리에이터3", image: "images/dog.png", likes: 80, views: 90, date: '2024-03-21' },
    { name: "크리에이터4", image: "images/dog.png", likes: 120, views: 110, date: '2024-04-01' }
];

// 가상 데이터를 기반으로 HTML 생성
creators.forEach(creator => {
    let gridGroup = document.createElement('div');
    gridGroup.classList.add('gridgroup');

    let titleDiv = document.createElement('div');
    titleDiv.classList.add('mboardtitle');
    titleDiv.innerHTML = '<p>' + creator.name + '</p>';
    gridGroup.appendChild(titleDiv);

    let imgDiv = document.createElement('div');
    imgDiv.classList.add('creatorimg');
    imgDiv.innerHTML = '<img src="' + creator.image + '" alt="">';
    gridGroup.appendChild(imgDiv);

    let inboardDiv = document.createElement('div');
    inboardDiv.setAttribute('id', 'inboard');
    inboardDiv.classList.add('increator');
    inboardDiv.setAttribute('onclick', 'inboard()');
    inboardDiv.innerHTML = '<img src="images/picture1.png" alt=""><img src="images/dog.png" alt=""><img src="images/project_img2.png" alt=""><img src="images/cute.png" alt="">';
    gridGroup.appendChild(inboardDiv);

    let detailDiv = document.createElement('div');
    detailDiv.classList.add('detail');
    let detailContentDiv = document.createElement('div');
    detailContentDiv.classList.add('detail_content');
    detailContentDiv.innerHTML = '<div class="icon-view"><img src="icons/white_eye.png" alt=""><span>' + creator.views + '</span></div><div class="icon-heart"><img src="icons/white_heart.png" alt=""><span>' + creator.likes + '</span></div>';
    detailDiv.appendChild(detailContentDiv);
    gridGroup.appendChild(detailDiv);

    document.querySelector('.grid-container').appendChild(gridGroup);
});

// 최신순 정렬 함수
function sortByDate() {
    data.sort((a, b) => new Date(b.creators) - new Date(a.creators));
    // 정렬된 데이터를 화면에 표시하는 함수 호출 (여기서는 생략)
}

// 인기순 정렬 함수
function sortByLikes() {
    data.sort((a, b) => b.likes - a.likes);
    // 정렬된 데이터를 화면에 표시하는 함수 호출 (여기서는 생략)
}

// 버튼 클릭 이벤트 핸들러
function sort(type, button) {
    // 모든 탭 버튼 비활성화
    let tabs = document.querySelectorAll('.tab');
    tabs.forEach(tab => tab.classList.remove('active'));
    
    // 클릭된 탭 버튼 활성화
    button.classList.add('active');

    // 정렬 실행
    if (type === 'tab1') {
        sortByDate(); // 최신순
    } else if (type === 'tab2') {
        sortByLikes(); // 인기순
    }
}
