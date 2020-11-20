<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>     
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/views/include/admin_plugin_js.jsp" %>
<%@include file="/WEB-INF/views/include/admin_header.jsp" %>
<meta charset="UTF-8">
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- Main Header -->
		<%@include file="/WEB-INF/views/include/admin_main_header.jsp"%>
		
		<!-- Left side column. contains the logo and sidebar -->
		<%@include file="/WEB-INF/views/include/admin_left.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Admin Page <small>주문 목록</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> 주문 관리</a></li>
					<li class="active">회원 목록</li>
				</ol>
			</section>

			<%-- MAIN CONTENT --%>
			<!-- Main content -->
			<section class="content container-fluid">

				<!--------------------------
		        | Your Page Content Here |
		        -------------------------->

				<div class="row">
					<!-- left column -->
					<%-- 검색 조건 설정 및 페이지 이동에도 해당 값 유지 --%>
					<div class="col-md-12">
						<div class="row text-center">
							<!-- <div style="display: inline-block; float: right; margin-right:15px;">
							<button id="btn_edit_check"  class="btn btn-default" >선택 상품 수정</button>
							<button id="btn_delete_check"  class="btn btn-danger" >선택 상품 삭제</button>
							</div>	 -->
						</div>
						<br>

						<div class="box" style="border: none;">
							<div class="box-body">
								<table class="table table-striped text-center">
									<tr>
										<th>No</th>
										<th>주문번호</th>
										<th>상품번호</th>
										<th>상품이름</th>
										<th>상품수량</th>
										<th>주문자</th>
										<th>주소</th>
										<th>결제금액</th>
										<th>주문날짜</th>
									</tr>
									
									<%-- 상품 리스트 출력 --%>
									<c:if test="${empty orderList}">
										<tr>
											<td colspan="10"> 
												<p style="padding:50px 0px; text-align: center;"></p>
												<td colspan="10"> 
												<p style="padding:50px 0px; text-align: center;">주문목록이 없습니다.</p>
												
											</td>
										<tr>
									</c:if>
									<c:forEach items="${orderList}" var="OrderListVO" varStatus="status">
										<tr>
											<td class="col-md-1">${status.count}</td>
											<td class="col-md-1">${OrderListVO.ord_no}</td>
											<td class="col-md-1">${OrderListVO.gds_no}</td>
											<td class="col-md-1">${OrderListVO.gds_name}</td>
											<td class="col-md-1">${OrderListVO.ord_coun}</td>
											<td class="col-md-1">${OrderListVO.mb_id}</td>
											<td class="col-md-2">${OrderListVO.ord_addr}</td>
											<td class="col-md-2"><fmt:formatNumber value="${OrderListVO.ord_total}" pattern="###,###,###" /> </td>
											<td class="col-md-4"><fmt:formatDate value="${OrderListVO.ord_date}" pattern="yyyy.MM.dd HH:mm:ss"/></td>
										</tr>

									</c:forEach>
								</table>
							</div>
							<!-- /.box-body -->


							<!-- 페이징 기능 -->
							<div class="box-footer">

								<div class="text-center">
									<ul class="pagination">
										<!-- 이전표시 여부  [이전] -->
										<c:if test="${pm.prev}">
											<li><a href="list${pm.makeSearch(pm.startPage-1)}">&laquo;</a>
											</li>
										</c:if>
										<!-- 페이지목록번호 :  1  2  3  4  5  -->
										<c:forEach begin="${pm.startPage}" end="${pm.endPage}"
											var="idx">
											<li <c:out value="${pm.cri.page == idx?'class =active':''}"/>>
												<a href="list${pm.makeSearch(idx)}">${idx}</a>
											</li>
										</c:forEach>
										<!-- 다음표시 여부  [다음]-->
										<c:if test="${pm.next && pm.endPage > 0}">
											<li><a href="list${pm.makeSearch(pm.endPage +1)}">&raquo;</a>
											</li>
										</c:if>

									</ul>
								</div>

							</div>
							<!-- /.box-footer-->
						</div>
					</div>
					<!--/.col (left) -->

				</div>

			</section>
		</div>
		<!-- /.content -->
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<%@include file="/WEB-INF/views/include/footer.jsp"%>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Create the tabs -->
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li class="active"><a href="#control-sidebar-home-tab"
					data-toggle="tab"><i class="fa fa-home"></i></a></li>
				<li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i
						class="fa fa-gears"></i></a></li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<!-- Home tab content -->
				<div class="tab-pane active" id="control-sidebar-home-tab">
					<h3 class="control-sidebar-heading">Recent Activity</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:;"> <i
								class="menu-icon fa fa-birthday-cake bg-red"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

									<p>Will be 23 on April 24th</p>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

					<h3 class="control-sidebar-heading">Tasks Progress</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript:;">
								<h4 class="control-sidebar-subheading">
									Custom Template Design <span class="pull-right-container">
										<span class="label label-danger pull-right">70%</span>
									</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-danger"
										style="width: 70%"></div>
								</div>
						</a></li>
					</ul>
					<!-- /.control-sidebar-menu -->

				</div>
				<!-- /.tab-pane -->
				<!-- Stats tab content -->
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab
					Content</div>
				<!-- /.tab-pane -->
				<!-- Settings tab content -->
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">General Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading"> Report panel
								usage <input type="checkbox" class="pull-right" checked>
							</label>

							<p>Some information about this general settings option</p>
						</div>
						<!-- /.form-group -->
					</form>
				</div>
				<!-- /.tab-pane -->
			</div>
		</aside>
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

</body>
</html>