import * as reply from '../module/reply.js';
//모듈 경로는 상대경로로 접근해야한다.
// 파일명 뒤에 반드시 확장자를 작성한다!!!!!!!!!
let boardNumber = $('.board-num').val();


//리플 작성 완료 처리
$('.btn-reply').on('click', function () {
   let content = $('#reply-content').val();

   if(!(content && loginNumber)){
       alert('입력을 해라!');
       return;
   }

   let replyObj = {
       replyContent : content,
       boardNumber : boardNumber,
       userNumber : loginNumber
   };

   reply.add(replyObj, function(){
       reply.getList(boardNumber, showReply);
   });

    $('#reply-content').val('');
});



// reply.getList(boardNumber, showReply);
let page = 1;

reply.getListPage({boardNumber:boardNumber, page : page}, appendReply);


function appendReply(map){
    console.log(map);

    let text = '';

    map.replyList.forEach( r => {
        text += `
            <div class="reply" data-num="${r.replyNumber}">
              <div class="reply-box">
                <div class="reply-box__writer">${r.userId}</div>
                <div class="reply-box__content">${r.replyContent}</div>
              </div>
              
              <div class="reply-time">
                ${reply.timeForToday(r.replyUpdateDate) + (r.replyRegisterDate == r.replyUpdateDate ? ' 작성' : ' 수정')}
              </div>      
              
              <div class="reply-btn-box">
              `;

        if(r.userNumber == loginNumber) {
            text += `<span class="reply-btns"></span>
                <div class="reply-btns__box none">
                  <div class="reply-remove-btn">삭제</div>
                  <div class="reply-modify-btn">수정</div>
                </div>`;
        }

        text +=`</div>
            </div>`;
    } );

    $('.reply-list-wrap').append(text);
}


//무한 스크롤 페이징
$(window).on('scroll', function (){

   // 현재 브라우저의 스크롤 위치를 의미함
   console.log(`scrollTop : ${ $(window).scrollTop() }`);
   // 문서 전체의 높이를 구함
   console.log(`document : ${ $(document).height() }`);
   //브라우저 화면의 높이를 구함
   console.log(`window : ${ $(window).height() }`);

   if(Math.round($(window).scrollTop()) == $(document).height() - $(window).height()){
       console.log(++page);
       reply.getListPage({boardNumber:boardNumber, page : page}, appendReply);
   }

});






/**
 * 리플 목록을 만들어주는 콜백 함수
 *
 * @param result 리플 정보를 가진 배열객체
 */
function showReply(result){
    console.log(result);

    let text = '';

    result.forEach( r => {
        text += `
            <div class="reply" data-num="${r.replyNumber}">
              <div class="reply-box">
                <div class="reply-box__writer">${r.userId}</div>
                <div class="reply-box__content">${r.replyContent}</div>
              </div>
              
              <div class="reply-time">
                ${reply.timeForToday(r.replyUpdateDate) + (r.replyRegisterDate == r.replyUpdateDate ? ' 작성' : ' 수정')}
              </div>      
              
              <div class="reply-btn-box">
              `;

            if(r.userNumber == loginNumber) {
             text += `<span class="reply-btns"></span>
                <div class="reply-btns__box none">
                  <div class="reply-remove-btn">삭제</div>
                  <div class="reply-modify-btn">수정</div>
                </div>`;
            }
                
        text +=`</div>
            </div>`;
    } );

    $('.reply-list-wrap').html(text);
}


$('.reply-list-wrap').on('click', '.reply-btns', function () {
    let $replyBtnBox = $(this).closest('.reply-btn-box').find('.reply-btns__box');

    $('.reply-btns__box').addClass('none');

    $replyBtnBox.toggleClass('none');

});

$('body').click(function (e) {
    if ($(e.target).hasClass('reply-btns')) {
        //console.log('aa');
        return;
    }
    if (!$('.reply-btns__box').has(e.target).length) {
        $('.reply-btns__box').addClass('none');
    }
});

// 뒤로가기 버튼
$('.btn-back').on('click', function (){
    window.location.href = '/board/list';
})

//삭제 버튼
$('.btn-remove').on('click', function (){
    let boardNumber = $(this).data('number');
    window.location.href = '/board/remove?boardNumber=' + boardNumber;

    // let f = document.createElement('form');
    // f.setAttribute("method", 'post');
    // f.setAttribute('action', '/board/remove');
    //
    // let input = document.createElement('input');
    // input.setAttribute('name', 'boardNumber');
    // input.setAttribute('value', boardNumber);
    // input.setAttribute('type', 'hidden');
    //
    // f.appendChild(input);
    //
    // document.body.appendChild(f);
    // f.submit();
});

// 수정 버튼
$('.btn-modify').on('click', function (){
    let boardNumber = $(this).data('number');
    window.location.href = '/board/modify?boardNumber=' + boardNumber;
});

// 리플 작성 완료 처리
$('.btn-reply').on('click', function (){

});

// 리플 삭제 버튼 처리
$('.reply-list-wrap').on('click', '.reply-remove-btn', function () {
    $('.reply-btns__box').addClass('none');

    let replyNumber = $(this).closest('.reply').data('num');

    reply.remove(replyNumber, function (){
        reply.getList(boardNumber, showReply);
    });
});

// 리플 수정 버튼 처리
$('.reply-list-wrap').on('click', '.reply-modify-btn', function () {
    let $content = $(this).closest('.reply').find('.reply-box__content');
    $content.replaceWith(`
  <div class='modify-box'>
    <textarea class='modify-content'>${$content.text()}</textarea>
    <button type='button' class='modify-content-btn'>수정 완료</button>
  </div>
  `);
    $('.reply-btns__box').addClass('none');
});

// 리플 수정 완료 처리
$('.reply-list-wrap').on('click', '.modify-content-btn', function () {
    console.log('modify!!!');
    let replyNumber = $(this).closest('.reply').data('num');
    let replyContent = $(this).closest('.modify-box').find('.modify-content').val();
    // console.log(replyContent);
    let replyObj = {replyContent : replyContent};

    reply.modify(replyNumber, replyObj, function (){
        reply.getList(boardNumber, showReply);
    });
});


//이미지 띄우기 처리
displayAjax();

function displayAjax(){
    let boardNumber = $('.board-num').val();

    $.ajax({
        url : '/files/imgList',
        type : 'get',
        data : {boardNumber : boardNumber},
        success : function (fileList) {
            let text = '';

            fileList.forEach(file => {
                // console.log(file);
                let fileName = file.fileUploadPath + '/' + file.fileUuid + '_' + file.fileName;

                text += `<img src="/files/display?fileName=${fileName}" data-number=${file.fileNumber} />`;

            });

            $('.post-images').html(text);
        }
    });
}












