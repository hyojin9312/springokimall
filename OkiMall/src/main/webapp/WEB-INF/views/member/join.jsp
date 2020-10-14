<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Oki</title>

  <!-- Bootstrap core JavaScript -->
  <%@include file="/WEB-INF/views/include/bootjs.jsp" %>
  
   <!-- 회원가입 유효성 검사 -->
  <script type="text/javascript" src="/js/join.js"></script>

  <!-- Bootstrap core CSS -->
  <!-- Custom styles for this template -->
  <%@include file="/WEB-INF/views/include/bootcss.jsp" %>

 
</head>

<body>

  <!-- Navigation -->
 <%@include file="/WEB-INF/views/include/top.jsp" %>

  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">
	   
	   <!-- category -->
      <%@ include file="/WEB-INF/views/include/category.jsp" %>

      </div>
      	<div class="col-lg-9">
       <div class="row">
      <!-- 회원가입  -->
		<div class="col-10"> 
    	<h2 class="my-4">회원가입</h2>
    	<form id="frmJoin" action="joinOk" method="post">
        <table class="table table-boardered">
            <tr>
                <th>아이디</th>
                <td><input type="text" class="form-control-1" id="mb_id" name="mb_id" placeholder="아이디를 입력해주세요">
                	<button id="btn_id_ck" type="button">중복확인</button>
                	<p id="id_ck" style="color: red;"></p>
                </td>	    
            </tr>
            <tr>
                <th>비밀번호</th>
                <td><input type="password" class="form-control" id="mb_pw" name="mb_pw" placeholder="비밀번호는 영문,숫자만 넣어주세요"></td>      
            </tr>
             
            <tr>
                <th>비밀번호확인</th>
                <td><input type="password" class="form-control" id="mb_pw_ck" placeholder="비밀번호 확인"></td>        
            </tr>
            
            <tr>
                <th>이름</th>
                <td><input type="text" class="form-control" id="mb_name" name="mb_name" placeholder="이름을 입력해주세요"></td>       
            </tr>
             
            <tr>
                <th>닉네임</th>
                <td><input type="text" class="form-control" id="mb_nick" name="mb_nick" placeholder="사용할 닉네임을 입력해주세요"></td>       
            </tr> 
             
            <tr>
                <th>이메일</th>
                <td><input type="email" style="width:70%;" id="mb_email" name="mb_email" placeholder="이메일 주소를 입력해주세요">
                	<button id="btn_sendcode" type="button">이메일 인증</button>
                	<p id="authcode" style="color: red;"></p>
                </td>       
            </tr>
            <tr>
            	<th>이메일 인증코드</th>
            	<td><input type="text" style="width: 50%;" id="mb_authcode" placeholder="인증코드를 입력해주세요">
            		<button id="btn_code_ck" type="button">확인</button>
            	</td>
            </tr>
            <tr>
                <th>휴대폰번호</th>
                <td><input type="tel" class="form-control" id="mb_phone" name="mb_phone" placeholder="휴대폰번호를 입력해주세요"></td>       
            </tr>
            <tr>
            	<th>주소</th>
            	<td><input type="text" id="sample2_postcode" name="mb_epost" style="width:50%" placeholder="우편번호">
			    	<input type="button" onclick="sample2_execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" id="sample2_address" name="mb_addr" style="width:80%" placeholder="주소"><br>
					<input type="text" id="sample2_detailAddress" name="mb_daddr" style="width:80%" placeholder="상세주소">
					<input type="hidden" id="sample2_extraAddress" placeholder="참고항목">
				</td>
            </tr>
                           
            <tr>
                <th>당신의 연령은</th>
                <td>
                <input type="radio" name="mb_age" value="10">10대 &nbsp;&nbsp;
                <input type="radio" name="mb_age" value="20">20대 &nbsp;&nbsp;
                <input type="radio" name="mb_age" value="30">30대 &nbsp;&nbsp;
                <input type="radio" name="mb_age" value="40">40대 &nbsp;&nbsp;
                </td>     
            </tr>
             
            <tr>
                <td colspan="2" class="text-center">
                <input type="button" id="btn_Join" class="btn btn-primary" value="가입하기">
                <input type="button" id="btn_cancle" class="btn btn-danger" value="취소">
                </td>
            </tr>
             
             
        </table>
    	</form>    
    	
    	<!-- Daum api -->
    	<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
    	<div id="layer" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
		<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
		</div>
		<!-- 우편번호API 동작코드 -->
		<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script src="/js/post.js"></script>
        </div>
 		</div>
    	<!-- /.row -->
    
    </div>
    <!-- /.col-lg-9 -->
    
    </div>
    <!-- /.row -->
    
  </div>
  <!-- /.container -->

  <!-- Footer -->
  <%@include file="/WEB-INF/views/include/footer.jsp" %>



</body>

</html>      
      