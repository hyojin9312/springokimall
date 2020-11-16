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
   
  <script>
  $(document).ready(function(){
	 $("#btn_submit").on("click",function(){
		if($("#mb_pw").val()== null || $("#mb_pw").val()==""){
			alert("비밀번호를 입력해주세요");
		} else {
			$("#checkPwForm").submit();
		}
	 }); 
  });
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
      	
        <div class="row">
        <!-- 비밀번호  -->
		<div class="card align-middle" style="width:30rem; border-radius:20px;">
		<div class="card-title" style="margin-top:30px;">
			<h2 class="card-title text-center" style="color:#113366;">비밀번호 확인</h2>
		</div>
		<div class="card-body">
        <form class="form-signin" id="checkPwForm" action="checkPw" method="POST">
        <input type="hidden" name="url" value="${url}" />
        <input type="password" id="mb_pw" name="mb_pw" class="form-control" placeholder="비밀번호를 입력해주세요" required><br>
        <button id="btn_submit" class="btn btn-lg btn-primary btn-block" type="button">확 인</button>
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