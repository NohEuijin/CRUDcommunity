
$("#userId").on('change', function (){
    console.log("change!!!")

    let userId = $(this).val();

    $.ajax({
       url : `/users/check`,
       type : 'get',
       data : {userId : userId},
        success : function (result){
           console.log(result);

           if(result == 1){
               $('.check-id').text("중복된 아이디입니다~");
           }else{
               $('.check-id').text("사용가능한 아이디입니다~");
           }
        }
    });

});



let $genderBox = $('.gender-box');

$genderBox.on('click', function (e) {
    let idx = $genderBox.index(this);
    console.log(idx);
    for (let i = 0; i < $genderBox.length; i++) {
        if (i == idx) {
            $genderBox.eq(i).addClass('checked');
        } else {
            $genderBox.eq(i).removeClass('checked');
        }
    }
});