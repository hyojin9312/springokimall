<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  
   <!-- 회원정보수정 유효성 검사 -->
  <script src="/js/member/modify.js"></script>

  <!-- Bootstrap core CSS -->
  <!-- Custom styles for this template -->
  <%@include file="/WEB-INF/views/include/bootcss.jsp" %>

 
</head>

<body>

  <!-- Navigation -->
 <%@include file="/WEB-INF/views/include/top.jsp" %>

  <!-- Page Content -->
  <div class="container">

    <div class="row" >

      <div class="col-lg-3">
	   
	   <!-- category -->
      <%@ include file="/WEB-INF/views/include/category.jsp" %>

      </div>
      	<div class="col-lg-9">
       <div class="row">
      <!-- 회원가입  -->
		<div class="col-12"> 
    	<h2 class="my-4">회원정보 수정</h2>
    	<p>수정하고자하는 정보를 수정해주세요</p>
    	<form id="frmModify" action="modify" method="post">
        <table class="table table-boardered">
           <tr>
                <th>아이디</th>
                <td>
               		<input type="text" class="form-control" id="mb_id" name="mb_id" value="${vo.mb_id}">
                </td>	    
            </tr>
            <tr>
                <th>비밀번호확인</th>
                <td>
                	<input type="password" class="form-control" id="mb_pw" name="mb_pw" placeholder="현재비밀번호를 입력해주세요">
                </td>      
            </tr>
            <tr>
                <th>이름</th>
                <td>
                	<input type="text" class="form-control" id="mb_name" name="mb_name"  value="${vo.mb_name}">
                </td>       
            </tr>
             
            <tr>
                <th>닉네임</th>
                <td>
                	<input type="text" class="form-control" id="mb_nick" name="mb_nick" value="${vo.mb_nick}">
                </td>       
            </tr> 
             
            <tr>
                <th>이메일</th>
                <td>
                	<input type="email" style="width:70%;" id="mb_email" name="mb_email" value="${vo.mb_email}">
                	<button id="btn_sendcode" type="button" style="display: none;">이메일 인증</button>
                	<p id="authcode" style="color: red;"></p>
                </td>       
            </tr>
            <tr id="email_authcode" style="display: none;">
            	<th>이메일 인증코드</th>
            	<td>
            		<input type="text" style="width: 50%;" id="mb_authcode" placeholder="인증코드를 입력해주세요">
            		<button id="btn_code_ck" type="button">확인</button>
            	</td>
            </tr>
            <tr>
                <th>휴대폰번호</th>
                <td>
                	<input type="tel" class="form-control" id="mb_phone" name="mb_phone" value="${vo.mb_phone}">
                </td>       
            </tr>
            <tr>
            	<th>주소</th>
            	<td>
            		<input type="text" id="sample2_postcode" name="mb_epost" value="${vo.mb_epost}" style="width:50%" placeholder="우편번호">
			    	<input type="button" onclick="sample2_execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" id="sample2_address" name="mb_addr" value="${vo.mb_addr}" style="width:80%" placeholder="주소"><br>
					<input type="text" id="sample2_detailAddress" name="mb_daddr" value="${vo.mb_daddr}" style="width:80%" placeholder="상세주소">
					<input type="hidden" id="sample2_extraAddress" placeholder="참고항목">
				</td>
            </tr>
                           
            <tr>
                <th>당신의 연령은</th>
                <td>
                	<input type="radio" name="mb_age" value="10" <c:out value="${vo.mb_age == '10'?'checked':'' }"/>>10대 &nbsp;&nbsp;
                	<input type="radio" name="mb_age" value="20" <c:out value="${vo.mb_age == '20'?'checked':'' }"/>>20대 &nbsp;&nbsp;
                	<input type="radio" name="mb_age" value="30" <c:out value="${vo.mb_age == '30'?'checked':'' }"/>>30대 &nbsp;&nbsp;
                	<input type="radio" name="mb_age" value="40" <c:out value="${vo.mb_age == '40'?'checked':'' }"/>>40대 &nbsp;&nbsp;
                </td>     
            </tr>
             
            <tr>
                <td colspan="2" class="text-center">
                <input type="button" id="btn_modify" class="btn btn-primary" value="수정확인">
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

		<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script src="/js/member/post.js"></script>
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