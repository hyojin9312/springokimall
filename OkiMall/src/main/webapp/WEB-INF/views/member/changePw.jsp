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
  
   <!-- 비밀번호변경 유효성 검사 -->
  <script type="text/javascript" src="/js/member/changepw.js"></script>

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
      <!-- 비밀번호 변경  -->
		<div class="col-11"> 
    	<h2 class="my-4">비밀번호 변경</h2>
    	<form id="frmchangePw" action="changePw" method="post">
        <table class="table table-boardered">
            <tr>
            	<td>
            		<input type="hidden" name="mb_id" value="${sessionScope.user.mb_id}">
            	</td>
            </tr>
            <tr>
                <td>
                	<input type="password" class="form-control" id="mb_pw" placeholder="현재 비밀번호를 입력해주세요.">
                </td>      
            </tr>
             
            <tr>
                <td>
                	<input type="password" class="form-control" id="mb_pw_change"  name="mb_pw" placeholder="변경할 비밀번호를 입력해주세요.">
                </td>        
            </tr>
            
            <tr>
                <td>
                	<input type="password" class="form-control" id="mb_pw_ck" placeholder="변경할 비밀번호를 다시 입력해주세요.">
                </td>        
            </tr>
             
            <tr>
                <td colspan="2" class="text-center">
                <input type="button" id="btn_submit" class="btn btn-primary" value="확인">
                </td>
            </tr>
             
             
        </table>
    	</form>    
    	
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
      