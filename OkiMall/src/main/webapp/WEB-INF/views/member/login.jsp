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
   
  <script type="text/javascript" src="/js/login.js"></script> 
  <script>
  	if("${msg}"=="LOGIN_FAIL"){
  		alert("로그인에 실패하였습니다.\n아이디와 비밀번호 다시 확인해주세요.");
  	}
  </script> 
  <!-- Bootstrap core CSS -->
  <!-- Custom styles for this template -->
  <%@include file="/WEB-INF/views/include/bootcss.jsp" %>
  
  <style>
 
    .card {
        margin: 0 auto; /* Added */
        float: none; /* Added */
        margin-bottom: 10px; /* Added */
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
	}
	
	.form-signin .form-control {
  		position: relative;
  		height: auto;
  		-webkit-box-sizing: border-box;
     	-moz-box-sizing: border-box;
        	 box-sizing: border-box;
  		padding: 10px;
  		font-size: 16px;
	}	
   </style>

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
      	<%@ include file="/WEB-INF/views/include/carousel.jsp" %>
        <div class="row">
      		<!-- 로그인  -->
		<div class="card align-middle" style="width:30rem; border-radius:20px;">
		<div class="card-title" style="margin-top:30px;">
			<h2 class="card-title text-center" style="color:#113366;">LOGIN</h2>
		</div>
		<div class="card-body">
        <form class="form-signin" id="loginform" action="/member/loginOk" method="POST">
        <h5 class="form-signin-heading">회원 로그인</h5>
        <label for="inputEmail" class="sr-only">ID</label>
        <input type="text" id="mb_id" name="mb_id" class="form-control" placeholder="아이디" required autofocus><BR>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="mb_pw" name="mb_pw" class="form-control" placeholder="비밀번호" required><br>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 아이디저장
          </label>
        </div>
        <button id="btn_login" class="btn btn-lg btn-primary btn-block" type="submit">로 그 인</button>
        </form>
        </div>
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
      