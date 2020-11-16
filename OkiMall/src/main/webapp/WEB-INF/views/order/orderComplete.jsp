<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Oki</title>

<!-- Bootstrap core JavaScript -->
<%@include file="/WEB-INF/views/include/bootjs.jsp" %>
 
<!-- Bootstrap core CSS -->
<!-- Custom styles for this template -->
<%@include file="/WEB-INF/views/include/bootcss.jsp" %>
<script type="text/javascript" src="/js/order/orderComplete.js"></script>
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
			<!-- general form elements -->
			<div class="row">
			<section class="content-header">
				<h1>
					상품구매 <small>주문내역</small>
				</h1>
			</section>
				<!-- /.box-header -->
							
			<%-- 주문내역  출력 --%>
			<section class="content container-fluid">
			<div class="row">
				<!-- left column -->
				<div class="box" style="border: none; padding:200px 50px; text-align: center;">
					<div class="box-body">
						<h3>해당 상품의 주문이 완료되었습니다.</h3><br>
						<button type="button" id="btn_orderList" class="btn btn-primary">주문내역 확인</button>
						<button type="button" id="btn_main" class="btn btn-default">쇼핑 계속하기</button>	
					</div>
				</div>				
			</div>
			</section>
			<!-- /.box -->	
			</div>		
      
	  </div>
      <!-- /.col-lg-9 -->
     
     <!-- /.col-lg-3 -->
    </div>
    <!-- /.row -->
    
  </div>
  <!-- /.container -->

  <!-- Footer -->
  <%@include file="/WEB-INF/views/include/footer.jsp" %>



</body>

</html>      