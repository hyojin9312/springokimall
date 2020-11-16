<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%> 
<!DOCTYPE html>
<html>
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
<script type="text/javascript" src="/js/order/buy.js"></script>
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
				<div class="box" style="border: none;">
					<form id="orderForm" method="post" action="/order/order">
						<div class="box-body" style="padding:30px 10px 100px 10px;">
							<%-- 주문내역 상단 버튼 --%>
							<div class="orderList" style="padding: 0px 40px;">
								<div style="width:100%;">
									<span style="display: inline-block; float: left; margin:20px 10px 5px 0px;">[주문내역]</span>
									<div class="btn-container" style="display: inline-block; float: right; margin:20px 10px 5px 5px;">
										<button id="btn_delete_check"  class="btn btn-default" type="button">선택 상품 삭제</button>
									</div>
								</div>
								<%-- 주문내역 테이블 --%>
								<table class="table table-striped text-center" id="ordertb">
									<thead id="thead">
										<tr>
											<th><input type="checkbox" id="checkAll" checked="checked"/></th>
											<th>IMAGE</th>
											<th>NAME</th>
											<th>PRICE</th>
											<th>DISCOUNT</th>
											<th>AMOUNT</th>
											<th>TOTAL</th>
										</tr>
										
										<%-- 상품이 존재하지 않는 경우 --%>
										<tr>
											<c:if test="${empty productList}">
												<span>구매할 상품이 존재하지 않습니다.</span>
											</c:if>
										</tr>
									<thead>
									
									<%-- 상품이 존재하는 경우,  리스트 출력 --%>
									<tbody>
									<c:forEach items="${productList}" var="productVO" varStatus="i">
										<tr id="productVO_${productVO.gds_no}" class="productRow">
											<td class="col-md-1">
												<input type="checkbox" name="check" class="check" value="${productVO.gds_no}" checked="checked" >
												<input type="hidden" id="amount_${productVO.gds_no}" name="orderDetailList[${i.index}].ord_coun" value="${amountList[i.index]}" />
												<input type="hidden" name="orderDetailList[${i.index}].gds_no" value="${productVO.gds_no}" />
												<input type="hidden" name="orderDetailList[${i.index}].ord_price" value="${productVO.gds_pric}" />
												<input type="hidden" name="orderDetailList[${i.index}].ord_disc" value="${productVO.gds_disc}" />
												<c:set var="total" value="${(productVO.gds_pric - productVO.gds_disc) * amountList[i.index] }"></c:set>
											</td>
											<td class="col-md-2">
												<a href="/product/read?gds_no=${productVO.gds_no}&cate_code=${cate_code}">
													<img src="/product/displayFile?fileName=${productVO.gds_imag}" style="width:100px;">
												</a>
											</td>
											<td class="col-md-2">
												<a href="/product/read?gds_no=${productVO.gds_no}&cate_code=${cate_code}"
													style="color: black;"> ${productVO.gds_name} </a>
											</td>
											<td class="col-md-1">
												<p><fmt:formatNumber value="${productVO.gds_pric}" pattern="###,###,###" /></p>
												<input type="hidden" name="price" value="${productVO.gds_pric}" />
											<td class="col-md-1">
												<p><fmt:formatNumber value="${productVO.gds_disc}" pattern="###,###,###" /></p>
												<input type="hidden" name="discount" value="${productVO.gds_disc}" /> 
											<td class="col-md-1">
												<p>${amountList[i.index]}</p>
												<input type="hidden" name="amount" value="${amountList[i.index]}" /> 
											</td>
											<td class="col-md-1">
												<p><fmt:formatNumber value="${productVO.gds_pric - productVO.gds_disc * amountList[i.index]}"  pattern="###,###,###" /></p>
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
										<div class="form-group" style="width:100%; margin-top:5px;">
											<input type="hidden" class="form-control" id="mb_id" name="mb_id" value="${user.mb_id}">
										</div>
										<div class="form-group">
											<label for="inputName">* 이름</label> <input type="text"
												class="form-control" id="ord_name" name="ord_name" value="${user.mb_name}">
										</div>
										<div class="form-group">
											<label for="inputMobile">* 휴대폰 번호</label> <input type="tel"
												class="form-control" id="ord_phone" name="ord_phone" value="${user.mb_phone}">
										</div>
										<div class="form-group">
											<label for="inputAddr">* 주소</label> <br />
											<input type="text" id="sample2_postcode" name="ord_epost" class="form-control" 
												value = "${user.mb_epost}"
												style="width:calc(100% - 128px); margin-right: 5px; display: inline-block;" placeholder="우편번호" readonly>
											<input type="button" onclick="sample2_execDaumPostcode()" id="btn_postCode" class="btn btn-default" value="우편번호 찾기"><br>
											<input type="text" id="sample2_address" name="ord_addr" class="form-control" 
												value = "${user.mb_addr}"
												placeholder="주소" style=" margin:3px 0px;" readonly>
											<input type="text" id="sample2_detailAddress" name="ord_daddr" class="form-control" 
												value = "${user.mb_daddr}"
												placeholder="상세주소" >
											<input type="hidden" id="sample2_extraAddress" class="form-control" 
												placeholder="참고항목">
										</div>
									</div>
								</div>
								
								<%-- 결제 방식 선택  및 주문 금액 확인 --%>
								<div class="orderConfirm" style="display:inline-block; width:20%; margin: 0px 5%;">
								<br>
									<%-- 결제 방식 --%>
									<div>
										<span>[결제 방식]</span><br>
										<div class="payment" style="margin-top:10px;">
											<input type="radio" name="payment" value="card" checked="checked">카드 결제
											<input type="radio" name="payment" value="tcash">실시간 계좌이체<br>
											<input type="radio" name="payment" value="phone">휴대폰 결제
											<input type="radio" name="payment" value="cash">무통장 입금
										</div>
									</div>
									<br><br><br>
									
									<%-- 주문 금액 --%>
									<div style="width: 400px;">
										<span>[결제 금액]</span>
										<table class="table text-center" style="margin-top:15px;" >
											<tr>
												<td class="col-md-1">총 상품금액</td>
												<td class="col-md-1" style="height:30px; text-align: center;"><p id="totalPrice">0</p></td>
											</tr>
											<tr>
												<td class="col-md-1"><label>결제 예정 금액</label></td>
												<td class="col-md-1" style="height:30px; text-align: center;">
													<label><p id="totalDiscount">0</p></label>
													<input type="hidden" id="ord_total" name="ord_total" value="${total }"/>
												</td>
											</tr>
											<tr>
												<td class="col-md-1" colspan="2" >
													<button id="btn_submit" class="btn btn-flat" type="button" style="padding: 10px 40px; background-color: grey; color:white;">결제하기</button>
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