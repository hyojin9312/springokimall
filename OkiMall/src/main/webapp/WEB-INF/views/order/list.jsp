<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%> 
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
					주문목록 <small>Order List</small>
				</h1>
			</section>
				<!-- /.box-header -->
							
			<%-- 주문내역  출력 --%>
			<section class="content container-fluid">
			<div class="row">
				<!-- left column -->
				<div class="box" style="border: none; padding: 10px 30px;">
						<div class="box-body">
							<table class="table text-center">
								<%-- 상품이 존재하지 않는 경우 --%>
								<c:if test="${empty orderList}">
									<tr>
										<td colspan="10"> 
											<p style="padding:50px 0px; text-align: center;">주문하신 상품이 없습니다.</p>
										</td>
									<tr>
								</c:if>
								
								<%-- 상품이 존재하는 경우,  리스트 출력 --%>
								<c:forEach items="${orderList}" var="orderVO" varStatus="status">
									<c:if test="${status.index==0 || orderVO.ord_no != no}">
									<tr style="background-color: aliceBlue;" >
										<td colspan="6" style="text-align:left;">
											<b>주문날짜: <fmt:formatDate value="${orderVO.ord_date}" pattern="yyyy/MM/dd HH:mm:ss"/>
											(주문번호: ${orderVO.ord_no} ) </b>
										</td>
										<td> 
											<button class="btn btn-primary" onclick="location.href='/order/read?ord_no=${orderVO.ord_no}';">
											주문 상세보기</button> 
										</td>
									<tr>
									<tr style="background-color: whitesmoke;">
										<td>IMAGE</td>
										<td>NAME</td>
										<td>PRICE</td>
										<td>DISCOUNT</td>
										<td>AMOUNT</td>
										<td>TOTAL</td>
										<td>REVIEW</td>
									</tr>
									</c:if>
									<c:set var="code" value="${orderVO.ord_no}">	</c:set>
									<tr>
										<td class="col-md-2">
											<a href="/product/read?gds_no=${orderVO.gds_no}">
												<img src="/product/displayFile?fileName=${orderVO.gds_imag}" style="width:100px;">
											</a>
										</td>
										<td class="col-md-2">
											<a href="/product/read?gds_no=${orderVO.gds_no}"
												style="color: black;"> ${orderVO.gds_name} </a>
										</td>
										<td class="col-md-1">
											<fmt:formatNumber value="${orderVO.ord_price}" pattern="###,###,###" /></p>
										</td>	
										<td class="col-md-1">
											<fmt:formatNumber value="${orderVO.ord_disc}" pattern="###,###,###" /></p>
										</td>	
										<td class="col-md-1">
											<p>${orderVO.ord_coun}</p>
										</td>
										<td class="col-md-1">
											<fmt:formatNumber value="${(orderVO.ord_price-orderVO.ord_disc) * orderVO.ord_coun}" pattern="###,###,###" /></p>
										</td>
										<td class="col-md-2">
											
											<button type="button" class="btn btn-flat" 
												onclick="location.href='/product/read?gds_no=${orderVO.gds_no}';" value="${orderVO.gds_no}" >상품후기 쓰기</button>
										</td>
									</tr>
								</c:forEach>
							</table>
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