$(document).ready(function(){
    
    var form = $("#frmdel");

    // 확인버튼 클릭시
    $("#btn_del").on("click", function(){
        form.submit();
    });

    // 취소버튼 클릭시
    $("#btn_cancle").on("click", function(){
        var result = confirm("회원탈퇴를 취소하시겠습니까?");
        if(result){
            location.href="/";
        } else{}
    });
});