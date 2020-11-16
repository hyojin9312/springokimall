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
  
   <!-- 회원탈퇴 유효성 검사 -->
  <script type="text/javascript" src="/js/member/delete.js"></script>

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
    	<h2 class="my-4">회원탈퇴</h2>
    	<form id="frmdel" action="delete" method="post">
        <table class="table table-boardered">
            <tr>
            	<td>
            		<input type="hidden" name="mb_id" value="${sessionScope.user.mb_id}" /> 회원 탈퇴 하시겠습니까?
            	</td>
            </tr>
             
              <tr>
                <td colspan="2" class="text-center">
                <input type="button" id="btn_del" class="btn btn-danger" value="확인">
                <input type="button" id="btn_cancle" class="btn btn-default" value="취소">
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
      