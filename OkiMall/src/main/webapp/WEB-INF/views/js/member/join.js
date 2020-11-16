$(document).ready(function(){
	
	var form = $("#frmJoin");

	// 아이디중복체크 기능 사용여부확인 변수
	var checkid = " false";

	// 인증확인 변수
	var CheckEmail = "false";

	/* 아이디 중복체크 클릭시 */
	$("#btn_id_ck").on("click", function(){
		if($("#mb_id").val()==""||$("#mb_id").val()==null){
			$("#id_ck").html("아이디를 입력해주세요");
			return;
		}

		var mb_id = $("#mb_id").val();

		//ajax방식
		$.ajax({
				url:'/member/idcheck',
				type: 'post',
				dataType: 'text',
				data: {mb_id : mb_id},
				success: function(data){
					if(data == 'SUCCESS'){
						//사용 가능한 아이디
						$("#id_ck").css("color","blue");
						$("#id_ck").html("사용가능한 아이디입니다.");

						// 아이디 중복체크하고 나서 
						$("#mb_id").attr("readonly",true);
						$("#btn_id_ck").attr("disabled",true);

						checkid = "true";

					}else{
						//사용 불가능 - 아이디 존재
						$("#id_ck").html("이미존재하는 아이디입니다. 다시 시도해주세요");
					}
				}

		});

	});

	// 이메일인증버튼 클릭시
	$("#btn_sendcode").on("click",function(){
		var receiveMail = $("#mb_email").val();

		if($("#mb_email").val()==""|| $("#mb_email").val()==null){
			// 메시지를 임의의 위치에 출력해줌.
			$("#authcode").html("이메일을 먼저 입력해주세요");
			return;
		}

		// 현재 작업이 진행중이 메시지를 보여주는 구문
		$("#authcode").css("color","grey");
		$("#authcode").html("인증코드 전송중입니다. 잠시만 기다려주세요.");

		$.ajax({
			url: '/email/send',
			type: 'post',
			dataType: 'text',
			data: {receiveMail:receiveMail},
			success: function(data){
				$("#authcode").css("color", "grey");
				$("#authcode").html("전송되었습니다. 인증코드 확인 후 입력해주세요.");
			}
			
		});
	});

	// 인증코드 확인버튼 클릭시
	$("#btn_code_ck").on("click", function(){
		
		var code = $("#mb_authcode").val();

		$.ajax({
			url: '/member/checkAutocode',
			type: 'post',
			dataType: 'text',
			data: {code:code},
			success: function(data){
				if(data=='SUCCESS'){
					$("#authcode").css("color","blue");
					$("#authcode").html("인증성공");
					CheckEmail = "true";
					return;
				}else{
					$("#authcode").css("color", "red");
					$("#authcode").html("인증실패 다시 시도해주세요");
					return;
				}
			}
		});

	});
	
	/*회원가입 버튼 클릭시*/
	$("#btn_Join").on("click", function(){

		alert("유효성검사");
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

		//유효성 검사구문
		
		form.submit();
		
	});


	/* 취소버튼클릭시*/
	$("#btn_cancle").on("click",function(){

		var result = confirm("가입을 취소하시겠습니까?\n메인화면으로 이동하시겠습니까?");
	
		if(result){
			location.href="/";
		}else{}

 	});

});
