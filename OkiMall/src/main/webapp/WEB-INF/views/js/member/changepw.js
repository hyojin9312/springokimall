$(document).ready(function(){
    
    var form = $("#frmchangePw");

    var mb_pw = $("#mb_pw");
    var mb_pw_change = $("#mb_pw_change");
    var mb_pw_ck = $("#mb_pw_ck");

    // 확인 버튼 클릭시
    $("#btn_submit").on("click",function(){
        
        // 유효성 검사구문


        // 현재 비밀번호 확인 검사
        var mb_pw_val = mb_pw.val();

        $.ajax({
            url: "/member/checkPwAjax",
            type: "post",
            datatype: "text",
            data: {mb_pw:mb_pw_val},
            success: function(data){
                if(data == 'SUCCESS'){
                    form.submit();
                }else{
                    alert("현재 비밀번호가 다릅니다.");
                    mb_pw.val("");
                    mb_pw.focus();
                }
            }
        });

    });

});