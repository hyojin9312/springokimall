<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
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
<!-- Bootstrap core CSS -->
  <!-- Custom styles for this template -->
  <%@include file="/WEB-INF/views/include/bootcss.jsp" %>
  
  <style>
	ul{
   		list-style:none;
   		padding-left:0px;
    }
    .product{
    	display: inline-block;
    	margin: 10px;
    	padding:22px 40px;
    }
    .description{
    	margin: 10px;
    }
</style>

<script>
/* 장바구니 버튼 클릭 이벤트 */
var cart_click = function(gds_no){
	$.ajax({
		url: "/cart/add",
		type: "post",
		dataType: "text",
		data: {gds_no: gds_no},
		success: function(data){
			var result = confirm("장바구니에 추가되었습니다.\n지금 확인하시겠습니까?");
			if(result){
				location.href="/cart/list";
			} else{}
		}
	});
}
</script>
 
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
       
		
    	<%-- MAIN CONTENT --%> 
			<section class="content container-fluid">
				<%-- 상품 목록 표시 --%>
				<div class="container text-center">
					<h3>${cate_name}</h3><br>
					
					<ul class="pdtList">
						<%-- 상품이 존재하지 않는 경우 --%>
						<c:if test="${empty productList}">
							<span>등록된 상품이 존재하지 않습니다.</span>
						</c:if>
						
						<%-- 상품이 존재하는 경우 --%>
						<c:forEach items="${productList}" var="productVO"  >
						<li class="product" style="width: 30%;">
							${productVO.gds_no}
							<div class="thumnail">
								<a href="/product/read${pm.makeQuery(pm.cri.page)}&gds_no=${productVO.gds_no}&cate_code=${cate_code}">
									<img src="/product/displayFile?fileName=${productVO.gds_imag}" >
								</a>
							</div>
							<div class="description">
								<a href="/product/read${pm.makeQuery(pm.cri.page)}&gds_no=${productVO.gds_no}&cate_code=${cate_code}" >${productVO.gds_name}</a>
								<p>가격: <fmt:formatNumber value="${productVO.gds_pric}" pattern="###,###,###" />원<br>
								 	할인가: <fmt:formatNumber value="${productVO.gds_disc}" pattern="###,###,###" />원</p>
							</div>
							<div class="btnContainer">
								<button class="btn btn-primary" id="btn_buy" type="button" 
									onclick="location.href = '/order/buy?gds_no=${productVO.gds_no}&ord_amount=1';">구매</button>
								<button class="btn btn-default" id="btn_cart" type="button" 
									onclick="cart_click(${productVO.gds_no})">장바구니</button>
							</div>
						</c:forEach>
					</ul>
					
				</div>
				<%-- 페이지 표시 --%>
				<div class="box-footer container" style="width:100%; min-width:1400px;">
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${pm.prev}">
								<li><a href="list${pm.makeQuery(pm.startPage-1)}&cate_code=${cate_code}">&laquo;</a>
								</li>
							</c:if>

							<c:forEach begin="${pm.startPage}" end="${pm.endPage}"
								var="idx">
								<li <c:out value="${pm.cri.page == idx?'class =active':''}"/>>
									<a href="list${pm.makeQuery(idx)}&cate_code=${cate_code}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pm.next && pm.endPage > 0}">
								<li><a href="list${pm.makeQuery(pm.endPage +1)}&cate_code=${cate_code}">&raquo;</a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			</section>
    	
    	
        
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