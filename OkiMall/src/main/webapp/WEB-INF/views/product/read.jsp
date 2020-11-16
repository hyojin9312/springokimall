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
  
   
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>  
<script type="text/javascript" src="/js/product/read.js"></script>
  

<script id="template" type="text/x-handlebars-template">
	{{#each .}}
		<li style="margin-right: 200px;" class="replyLi" data-rv_no={{rv_no}}>
        	<i class="fa fa-comments bg-blue"></i>
            <div class="timeline-item" >
                <span class="time">
                	<i class="fa fa-clock-o"></i>{{prettifyDate rv_date}}
                </span>
                <h3 class="timeline-header">
					<strong>{{checkRating rv_grade}} <p class='rv_grade' style="display:inline-block;">{{rv_grade}}</p></strong> 
					</h3>
                <div class="timeline-body">
					NUM: {{rv_no}} <p style="float:right;">작성자: {{mb_id}}</p> <br>
					<p id='rv_conte'>{{rv_conte}}</p> </div>
				<div class="timeline-footer" style="float:right;">
					{{eqReplyer mb_id rv_no}}
				</div>
	         </div>			
         </li>
	{{/each}}
</script>  
  
<script>
	$(document).ready(function(){
		
		$("#btn_list").on("click", function(){
			location.href="/product/list${pm.makeQuery(pm.cri.page)}&cate_code=${vo.cate_par}";
		});
		
		Handlebars.registerHelper("prettifyDate", function(timeValue) {
			var dateObj = new Date(timeValue);
			var year = dateObj.getFullYear();
			var month = dateObj.getMonth() + 1;
			var date = dateObj.getDate();
			return year + "/" + month + "/" + date;
		});
		
		Handlebars.registerHelper("checkRating", function(rating){
			var stars = "";
			switch(rating){
				case 1:
					 stars="★☆☆☆☆";
					 break;
				case 2:
					 stars="★★☆☆☆";
					 break;
				case 3:
					 stars="★★★☆☆";
					 break;
				case 4:
					 stars="★★★★☆";
					 break;
				case 5:
					 stars="★★★★★";
					 break;	 
				default:
					 stars="☆☆☆☆☆";
			}
			return stars;
		});
		
		Handlebars.registerHelper("eqReplyer", function(replyer, rv_no) {
			var btnHtml = '';
			
			if (replyer == "${user.mb_id}") {
				btnHtml = "<a class='btn btn-primary btn-xs' data-toggle='modal' data-target='#modifyModal'>"
					  + "MODIFY</a>"
					  + "<button class='btn btn-danger btn-xs' style='margin-left:5px;'" 
					  + "onclick='deleteReview("+rv_no+");'"
					  + "type='button' >DELETE</button>"; 
			}
			return new Handlebars.SafeString(btnHtml);
			

		});
		
	});

</script>  
<style>
     #star_grade a{
     	font-size:22px;
        text-decoration: none;
        color: lightgray;
    }
    #star_grade a.on{
        color: black;
    }
    
    #star_grade_modal a{
     	font-size:22px;
        text-decoration: none;
        color: lightgray;
    }
    #star_grade_modal a.on{
        color: black;
    }
    
    .popup {position: absolute;}
    .back { background-color: gray; opacity:0.5; width: 100%; height: 300%; overflow:hidden;  z-index:1101;}
    .front { 
       z-index:1110; opacity:1; boarder:1px; margin: auto; 
      }
     .show{
       position:relative;
       max-width: 1000px; 
       max-height: 800px; 
       overflow: auto;       
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
			<!-- general form elements -->
			<div class="row">
				<div class="col-10">
					<h3 class="box-title">PRODUCT DETAIL</h3>
				</div>
				<!-- /.box-header -->
							
				<%-- 상품 상세 정보 출력 --%>
				<div class="box-body">
					<div class="form-group container" style="margin:30px 0px; height:350px;" >
						<div style="float:left; width:30%; height:80%;">
							<img src="/product/displayFile?fileName=${vo.gds_imag}" style="display: inline; width:90%;">
						</div>
						<div style="display: inline-block; margin-left:20px;">
							<label for="exampleInputEmail1">Product Name</label><br>
								<span>${vo.gds_name}</span><br><br>		
								<label for="exampleInputEmail1">Company</label><br>
								<span>${vo.gds_make}</span><br><br>
										
						<div>
							<label for="exampleInputEmail1" style="width:100px; margin-right:10px;">Price</label> 
							<label for="exampleInputEmail1" style="width:100px;">Discount</label> <br>
							<span style="width:100px; margin-right:10px; display:inline-block;">
								<fmt:formatNumber value="${vo.gds_pric}" pattern="###,###,###" />원
							</span>
							<span style="width:100px; display:inline-block;">
								<fmt:formatNumber value="${vo.gds_disc}" pattern="###,###,###" />원
							</span>
						</div>
						<br>
										
						<div>
							<form method="get" action="/order/buy" >
								<label for="exampleInputEmail1">Amount</label><br>
								<input type="number" id="ord_amount" name="ord_amount" value="1" /><br><br>
								<input type="hidden" id="gds_no" name="gds_no" value="${vo.gds_no}" />
								<button type="submit" id="btn_buy" class="btn btn-primary">구매하기</button>
								<!-- 장바구니 기능으로 진행 -->
								<button type="button" id="btn_cart" class="btn btn-default">장바구니</button>
							</form>
						</div>
										
						</div>
					</div>
					<!-- 상품 상세 -->
					<label for="detail" style="margin-top: 50px;">Detail</label><br>
						<div contenteditable="false" style="border: 1px solid grey; padding: 20px; width: 1000px;">
							${vo.gds_deta}
						</div>
						<br>
								
						<%-- 상품 후기 --%>
						<div class='popup back' style="display:none;"></div>
							<div id="popup_front" class='popup front' style="display:none;">
							 	<img id="popup_img">
							</div>
						    <form role="form" action="modifyPage" method="post">
								<input type='hidden' name='bno' value="${boardVO.bno}">
								<input type='hidden' name='page' value="${cri.page}"> 
								<input type='hidden' name='perPageNum' value="${cri.perPageNum}">
								<%-- 
								<input type='hidden' name='searchType' value="${cri.searchType}">
								<input type='hidden' name='keyword' value="${cri.keyword}">
								 --%>
							</form>
								
						<div>
							<!-- 상품후기쓰기 부분 -->
							<div>
								<label for="review">Review</label><br>
								<div class="rating">
									<p id="star_grade">
										<a href="#">★</a>
										<a href="#">★</a>
										<a href="#">★</a>
										<a href="#">★</a>
										<a href="#">★</a>
									</p>
								</div>
								<textarea id="reviewContent" rows="3" style="width:1000px;"></textarea><br>
									
							<!-- 상품 후기 리스트 -->
							<ul class="timeline">
				 			<!-- timeline time label -->
								<li class="time-label" id="repliesDiv">
									<span class="btn btn-default">
									상품후기 보기 <small id='replycntSmall'> [ ${totalReview} ] </small>
									</span>
									<button class="btn btn-primary" id="btn_write_review" type="button">상품후기쓰기</button>
								</li>
								<li class="noReview" style="display:none;">
									<i class="fa fa-comments bg-blue"></i>
									<div class="timeline-item" >
									<h3 class="timeline-header">
									상품후기가 존재하지 않습니다.<br>
									상품후기를 입력해주세요.</h3>
									</div>
								</li>
											 
							</ul>
							<!-- 상품 후기 리스트 페이지부분 -->  
							<div class='text-center'>
								<ul id="pagination" class="pagination pagination-sm no-margin "></ul>
							</div>
						</div>
									 
									 
							<%-- Modal : 상품후기 수정/삭제 팝업 --%>
							<div id="modifyModal" class="modal modal-primary fade" role="dialog">
								<div class="modal-dialog">
								<!-- Modal content-->
									<div class="modal-content">
									      <div class="modal-header" >
									        <button type="button" class="close" data-dismiss="modal">&times;</button>
									        <div class="modal-title">
												<p id="star_grade_modal">
											        <a href="#">★</a>
											        <a href="#">★</a>
											        <a href="#">★</a>
											        <a href="#">★</a>
											        <a href="#">★</a>
												</p>
									        </div>
									      </div>
									      <div class="modal-body" data-rv_no>
									        <p><input type="text" id="replytext" class="form-control"></p>
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-info" id="btn_modal_modify">수정</button>
									        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
									      </div>
									</div>
								</div>
							</div>      
						</div>
							
							
							<!-- /.box-body -->

							<div class="footer">
								<div>
									<hr>
								</div>

								<ul class="mailbox-attachments clearfix uploadedList">
								</ul>

								<button id="btn_list" type="button" class="btn btn-primary" >GO List</button>
							</div>
						
						</div>
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