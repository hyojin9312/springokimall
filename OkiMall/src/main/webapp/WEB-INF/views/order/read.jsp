<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
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
					주문 상세 내역 <small>Order Detail</small>
				</h1>
			</section>
				<!-- /.box-header -->
							
			<%-- 주문내역  출력 --%>
			<section class="content container-fluid">
			<div class="row">
				<!-- left column -->
				<div class="box" style="border: none;">
					<form id="orderForm" method="post" action="/order/buy">
						<div class="box-body" style="padding:30px 10px 100px 10px;">
							<%-- 주문내역 상단 버튼 --%>
							<div class="orderList" style="padding: 0px 40px;">
								<%-- 주문내역 테이블 --%>
								<table class="table  text-center" id="ordertb">
									<thead id="thead">
										<tr style="background-color: aliceBlue;" >
											<td colspan="7" style="text-align:left;">
												<b>주문날짜: <fmt:formatDate value="${order.ord_date}" pattern="yyyy/MM/dd HH:mm:ss"/>
												(주문번호: ${order.ord_no} )</b>
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
										<%-- 상품이 존재하지 않는 경우 --%>
										<tr>
											<c:if test="${empty orderList}">
												<span>구매한 상품이 존재하지 않습니다.</span>
											</c:if>
										</tr>
									<thead>
									
									<%-- 상품이 존재하는 경우,  리스트 출력 --%>
									<tbody>
									<c:forEach items="${orderList}" var="product" varStatus="status">
									<c:set var="totalPrice" value="${totalPrice + orderList[status.index].gds_pric * orderList[status.index].ord_coun}"></c:set>
									<c:set var="discPrice" value="${orderList[status.index].ord_disc}"></c:set>
										<tr id="row">
											<td class="col-md-2">
												<a href="/product/read?gds_no=${product.gds_no}">
													<img src="/product/displayFile?fileName=${product.gds_imag}" style="width:100px;">
												</a>
											</td>
											<td class="col-md-2">
												<a href="/product/read?gds_no=${product.gds_no}"
													style="color: black;"> ${product.gds_name} </a>
											</td>
											<td class="col-md-1">
												<p><fmt:formatNumber value="${product.gds_pric}" pattern="###,###,###" /></p>
											</td>
											<td class="col-md-1">
												<p><fmt:formatNumber value="${product.ord_disc}" pattern="###,###,###" /></p>
											</td>
											<td class="col-md-1">
												<p>${product.ord_coun}</p>
											</td>
											<td class="col-md-1">
												<p ><fmt:formatNumber value="${(product.ord_price - product.ord_disc) * product.ord_coun}"  pattern="###,###,###" /></p>
											</td>
											<td class="col-md-1">
												<button type="button" name="btn_review" class="btn btn-flat" 
												onclick="location.href='/product/read?gds_no=${product.gds_no}';" value="${product.gds_no}" >상품후기 쓰기</button>
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
								<br><br><br>
							</div>
							<hr><br>
							
							<%-- 주문 정보 --%>
							<div class="orderInfo" style="min-width:1000px;" > 
								<div class="userInfo" style="display:inline-block; float:left; width:60%; padding: 0% 5%;">
									<div class="container" style="width:100%;">
										<span>[주문 정보]</span>
										<div class="form-group">
											<label for="inputName">* 이름</label> <input type="text"
												class="form-control" value="${order.ord_name}" readonly>
										</div>
										<div class="form-group">
											<label for="inputMobile">* 휴대폰 번호</label> <input type="tel"
												class="form-control" value="${order.ord_phone}" readonly>
										</div>
										<div class="form-group">
											<label for="inputAddr">* 주소</label> <br />
											<input type="text" id="sample2_postcode" name="ord_epost" class="form-control" 
												value = "${order.ord_epost}" 
												style="width:calc(100% - 128px); margin-right: 5px; display: inline-block;" placeholder="우편번호" readonly>
											<input type="button" onclick="sample2_execDaumPostcode()" id="btn_postCode" class="btn btn-default" value="우편번호 찾기" disabled="disabled"><br>
											<input type="text" id="sample2_address" name="ord_addr" class="form-control" 
												value = "${order.ord_addr}" 
												placeholder="주소" style=" margin:3px 0px;" readonly>
											<input type="text" id="sample2_detailAddress" name="ord_daddr" class="form-control" 
												value = "${order.ord_daddr}"
												placeholder="상세주소" readonly >
											<input type="hidden" id="sample2_extraAddress" class="form-control" 
												placeholder="참고항목">
										</div>
									</div>
								</div>
								
								<%-- 주문 금액 확인 --%>
								<div class="orderConfirm" style="display:inline-block; width:20%; margin: 0px 5%;">
								<br>
									<%-- 주문 금액 --%>
									<div style="width: 400px;">
										<span>[결제 금액]</span>
										<table class="table text-center" style="margin-top:15px;" >
											<tr>
												<td class="col-md-1">총 상품금액</td>
												<td class="col-md-1" style="height:30px; text-align: center;">
													<fmt:formatNumber value="${totalPrice}" pattern="###,###,###" />원</td>
											</tr>
											<tr>
												<td class="col-md-1">할인된 금액(-)</td>
												<td class="col-md-1" style="height:30px; text-align: center;">
													<fmt:formatNumber value="${discPrice}" pattern="###,###,###" />원</td>
											</tr>
											<tr>
												<td class="col-md-1"><label>결제 금액</label></td>
												<td class="col-md-1" style="height:30px; text-align: center;">
													<label><fmt:formatNumber value="${order.ord_total}" pattern="###,###,###" />원</label>
												</td>
											</tr>
										</table>
								
									</div>
								</div>
							</div>
						</div>
					</form>
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