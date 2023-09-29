// reply.js는 모듈을 만들어 두는 파일이다.
// 자바스크립트의 함수, 클래스를 모듈화 시켜 저장하는 공간이다.
// 우리는 함수를 부품처럼 만들어두고 다른 파일에서 사용할 것이다.
// 이 모듈들을 밖에서 사용할 수 있도록 내보내는 키워드가 export이다.

// jquery ajax 프로퍼티 종류
// url : 요청보내는 경로
// type : method (요청 방식)
// data : 요청 보낼때 전송할 데이터
// dataType : 받는 데이터의 타입 -> 'json'
// contentType : 전송할 데이터의 타입 -> 'application/json; charset=utf-8'
// success : 성공했을 때 실행할 함수
// error :  실패 했을 때 실행할 함수

// JSON.stringify()
//    js 객체 -> json
// JSON.parse()
//    json -> js 객체

export function add(reply, callback){
    $.ajax({
        url: "/replies",
        type : "post",
        data : JSON.stringify(reply),
        contentType : 'application/json; charset=utf-8',
        success : function (){
            if(callback){
                callback();
            }
        },
        error : function (a,b,c) {
            console.error(c);
        }
    });
}

export function getList(boardNumber, callback){
    $.ajax({
        url : `/replies/list/${boardNumber}`,
        type : 'get',
        dataType : 'json',
        success : function (result) {
            if(callback){
                callback(result);
            }
        },
        error : function(a, b, c) {
            console.error(c);
        }
    });
}

export function getDetails(replyNumber, callback){
    $.ajax({
        url : `/replies/${replyNumber}`,
        type : 'get',
        dataType : 'json',
        success : function (result){
            if(callback){
                callback(result);
            }
        },
        error : function (a, b, c){
            console.error(c);
        }
    });
}

export function modify(replyNumber, reply, callback){
    $.ajax({
        url : `/replies/${replyNumber}`,
        type : 'patch',
        data : JSON.stringify(reply),
        contentType : 'application/json; charset=utf-8',
        success : function (){
            if(callback){
                callback();
            }
        },
        error : function (a, b, c){
            console.error(c);
        }
    });
}
export function remove(replyNumber, callback){
    $.ajax({
       url : `/replies/${replyNumber}`,
       type : 'delete',
       success : function (){
           if(callback){
               callback();
           }
       },
        error : function (a,b,c){
           console.error(c);
        }
    });
}


export function getListPage(pageInfo, callback){
    $.ajax({
        url: `/replies/list/${pageInfo.boardNumber}/${pageInfo.page}`,
        type : 'get',
        dataType : 'json',
        success : function (result){
            if(callback){
                callback(result);
            }
        },
        error : function (a,b,c){
            console.error(c);
        }
    });
}



export function timeForToday(value){
    const today = new Date(); //현재 날짜와 시간을 가진 객체
    const timeValue = new Date(value);

    // getTime() 1970년 1/1일을 기준으로 지금까지 몇 ms가 지났는지 알려준다.
    //Math.floor() 는 소수점을 버림 처리 해준다.
    const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);

    if(betweenTime < 1) { return "방금 전"; }

    if(betweenTime < 60) { return `${betweenTime}분 전`; }

    const betweenTimeHour = Math.floor(betweenTime/60);
    if(betweenTimeHour < 24) { return `${betweenTimeHour}시간 전`; }

    const betweenTimeDay = Math.floor(betweenTimeHour/ 24);
    if(betweenTimeDay < 365) { return `${betweenTimeDay}일 전`; }

    return `${Math.floor(betweenTimeDay / 365)}년 전`;
}







































