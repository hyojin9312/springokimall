$(document).ready(function(){

    var form = $("#frmModify");

    // 인증확인 변수
    var checkEmail = "true";

    // 이메일변경시 이메일 인증 활성화
    $("#mb_email").on("click", function(){
        $("#btn_sendcode").show();
        checkEmail = "false";
    });

    // 이메일 인증 클릭시
    $("#btn_sendcode").on("click", function(){

        var receiveMail = $("#mb_email").val();

        if($("#mb_email").val()==""|| $("#mb_email").val()== null){
            $("#authcode").html("이메일을 먼저 입력해주세요");
            return;
        }

        $("#authcode").css("color", "grey");
        $("#authcode").html("전송중입니다. 잠시만 기다려주세요...");

        $.ajax({
            url: '/email/send',
            type: 'post',
            dataType: 'text',
            data: {receiveMail: receiveMail},
            success: function(date){
                $("#email_authcode").show();
                $("#authcode").css("color", "grey");
                $("#authcode").html("전송되었습니다. 입력하신 이메일주소에서 인증코드 확인후 입력해주세요.");
            }
        });

    });

    // 인증코드 입력 후 확인버튼 클릭시
    $("#btn_code_ck").on("click", function(){

        var code = $("#mb_authcode").val();

        $.ajax({
            url: '/member/checkAutocode',
            type: 'post',
            dataType: {code:code},
            success: function(data){
                if(data=='SUCCESS'){
                    $("#email_authcode").hide();
                    $("#authcode").css("color","blue");
                    $("#authcode").html("인증성공");
                    $("#mb_email").attr("readonly","true");
                    checkEmail = "true";
                    return;

                }else{
                    $("#email_authcode").hide();
                    $("#authcode").css("color","red");
                    $("#authcode").html("인증실패. 다시 시도해주세요.");
                    return;
                }
            }
        });
    });

    // 회원수정버튼 클릭시
    $("#btn_modify").on("click", function(){

        var mb_id = $("#mb_id");
		var mb_pw = $("#mb_pw");
		var mb_pw_ck = $("#mb_pw_ck");
		var mb_name = $("#mb_name");
		var mb_nick = $("#mb_nick");
		var mb_email = $("#mb_email");
		var mb_authcode = $("#mb_authcode");
		var mb_phone = $("#mb_phone");
		var mb_epost = $("input[name='mb_epost']");
		var mb_addr = $("input[name='mb_addr']");
        var mb_daddr = $("input[name='mb_daddr']");
        var mb_age = $("input[name='mb_age']:checked").val();

        //현재 비밀번호 확인검사
        var mb_pw_val = mb_pw.val();

        $.ajax({
        
        });
    });

    //취소 버튼 클릭시
    $("#btn_cancle").on("click", function(){
        
        var result = confirm("회원정보 수정을 취소하시겠습니까?");
        
        if(result){
            location.href="/";
        }else{}
    });

});