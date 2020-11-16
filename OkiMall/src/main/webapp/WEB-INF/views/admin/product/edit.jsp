<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/admin_plugin_js.jsp" %>
<%@include file="/WEB-INF/views/include/admin_header.jsp" %>
<head>
<script src="/ckeditor/ckeditor.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<%-- Handlebar Template --%>
<script id="subCGListTemplate" type="text/x-handlebars-template">
	<option value="default">2차 카테고리 선택</option>
	{{#each .}}
	<option value="{{cate_code}}">{{cate_name}}</option>
	{{/each}}
</script>

<script>
$(document).ready(function(){
    // ckEditor작업
    var ckeditor_config = {
        resize_enabled : false,
        enterMode : CKEDITOR.ENTER_BR,
        shiftEnterMode : CKEDITOR.ENTER_P,
        toolbarCanCollapse : true,
        removePlugins : "elementspath",
        filebrowserUploadUrl : '/admin/product/imgUpload'
    };
    CKEDITOR.replace("gds_deta", ckeditor_config);

    $("#mainCategory").on("change", function(){
        var mainCGCode = $(this).val();
        var url = "/admin/product/subCGList/" + mainCGCode;

        $.getJSON(url,function(data){
            subCGList(data,$("#subCategory"), $("#subCGListTemplate"))
        });
    });

    $("#btn_list").on("click", function(){
        var result = confirm("내용을 저장하지 않고, 목록으로 돌아가시겠습니까?");

        if(result){
            location.href ="/admin/product/list${pm.makeSearch(pm.cri.page)}";
        }else{}
    });
});
</script>
<script>
var subCGList = function(subCGStr, target, templateObject) {

    var template = Handlebars.compile(templateObject.html());
    var options = template(subCGStr); // 템플릿에 2차카테고리 데이터가 바인딩된 소스

    // 기존 option 제거(누적방지)	
    $("#subCategory option").remove(); // 카테고리 선택에 의해 존재했던 2차카테고리 정보를 제거
    target.append(options);
}
</script>
<script>
// 파일변경 이벤트메소드

var fileChange = function(fis){
    var str = fis.value;
    $("#fileName").html("파일이 변경됨");
}
</script>

<script src="/js/admin/edit.js"></script>
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
					Admin Page <small>Product Edit</small>
				</h1>
				<ol class="breadcrumb">
					<li>
						<a href="#"><i class="fa fa-dashboard"></i> 상품관리</a>
					</li>
					<li class="active">상품수정</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">
				<!-- 상품등록 폼 -->
				<div class="row">
					<!-- left column -->
					<div class="col-md-12">
						<!-- general form elements -->
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title">PRODUCT EDIT </h3>
							</div>
							<!-- /.box-header -->

							<form id='editForm' role="form" action="/admin/product/edit" method="post" enctype="multipart/form-data">
								<div class="box-body">
									<div class="form-group">
										<input type="hidden" name="page" value="${cri.page}" />
										<input type="hidden" name="perPageNum" value="${cri.perPageNum}" />
										<input type="hidden" name="searchType" value="${cri.searchType}" />
										<input type="hidden" name="keyword" value="${cri.keyword}" />
									</div>
									
									<div class="form-group">
										<input name="gds_no" type="hidden" value="${vo.gds_no}" />
										<label for="exampleInputEmail1" style="width:30%; margin-right:20px;" >1차 카테고리</label>
										<label for="exampleInputEmail1" style="width:30%;" >2차 카테고리</label> <br />
										<select class="form-control" id="mainCategory" name="cg_code_prt" style="width:30%; margin-right:10px; display: inline-block;" >
											<option value="default">1차 카테고리 선택</option>
											<c:forEach items="${cateList}" var="list">
												<option value="${list.cate_code}"<c:out value="${vo.cate_par == list.cate_code?'selected':''}"/>>${list.cate_name}</option>
											</c:forEach>
										</select>
										<select class="form-control" id="subCategory" name="cate_code" style="width: 30%; display: inline-block;">
										 	<option value="default">2차 카테고리 선택</option>
										 	<c:forEach items="${subCateList}" var="subList">
										  		<option value="${subList.cate_code}"<c:out value="${vo.cate_code == subList.cate_code?'selected':''}"/>>${subList.cate_name}</option>
										 	</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1">Product Name</label> <input
											type="text" id="gds_name" name="gds_name" class="form-control"
											value="${vo.gds_name}">
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1">Company</label> <input
											type="text" id="gds_make" name="gds_make" class="form-control"
											value="${vo.gds_make}">
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:40%; margin-right:10px;">Price</label> 
										<label for="exampleInputEmail1" style="width:40%;">Discount</label> 
										<input style="width:40%; margin-right:10px; display: inline-block;"
											type="text" id="gds_pric" name="gds_pric" class="form-control" 
											value="${vo.gds_pric}" />
										<input style="width:40%; display: inline-block;"
											type="text" id="gds_disc" name="gds_disc" class="form-control "
											value="${vo.gds_disc}" />
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">Detail</label>
										<textarea class="form-control" id="gds_deta" name="gds_deta" rows="8">
											${vo.gds_deta}</textarea>
									</div>
									<div class="form-group">
										<input type="hidden" name="gds_imag" value="${vo.gds_imag}" />	
										<label for="exampleInputEmail1">Thumbnail Image</label> 
										<span id="fileName" style="margin-left:5px; font-size:14px;">현재 등록된 파일: <c:out value="${originFile}" /></span>
										<input onchange="fileChange(this)"
											type="file" id="file1" name="file1" class="form-control" />
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:30%; margin-right:10px;">Amount</label> 
										<label for="exampleInputEmail1" style="width:15%;">Buy availability</label><br /> 
										<input style="width:30%; margin-right:10px; display: inline-block;"
											type="text" id="gds_odd" name='gds_odd' class="form-control" 
											value="${vo.gds_odd}" />
										<select class="form-control" id="gds_buytf" name="gds_buytf" style="width: 15%; display: inline-block;">
										  <option 
										  	<c:out value="${vo.gds_buytf == 'Y'?'selected':''}"/>>Y</option>
										  <option
										  	<c:out value="${vo.gds_buytf == 'N'?'selected':''}"/>>N</option>
										</select>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:40%; margin-right:10px;">Submit Date</label> 
										<label for="exampleInputEmail1" style="width:40%;">Update Date</label> <br />
										<span class="form-control" style="width:40%; margin-right:10px; display:inline-block;">
											<fmt:formatDate value="${vo.gds_redate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
										<span class="form-control" style="width:40%; display: inline-block;">
											<fmt:formatDate value="${vo.gds_update}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
									</div>
								</div>
								
								
								<!-- /.box-body -->

								<div class="box-footer">
									<div>
										<hr>
									</div>

									<ul class="mailbox-attachments clearfix uploadedList">
									</ul>

									<button id="btn_submit" type="button" class="btn btn-primary" >Submit</button>
									<button id="btn_list" type="button" class="btn btn-default" >List</button>
								</div>
							</form>


						</div>
						<!-- /.box -->	
					</div>
					<!--/.col (left) -->

				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<%@include file="/WEB-INF/views/include/admin_footer.jsp" %>

		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Create the tabs -->
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li class="active">
					<a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a>
				</li>
				<li>
					<a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a>
				</li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<!-- Home tab content -->
				<div class="tab-pane active" id="control-sidebar-home-tab">
					<h3 class="control-sidebar-heading">Recent Activity</h3>
					<ul class="control-sidebar-menu">
						<li>
							<a href="javascript:;"> <i class="menu-icon fa fa-birthday-cake bg-red"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

									<p>Will be 23 on April 24th</p>
								</div>
							</a>
						</li>
					</ul>
					<!-- /.control-sidebar-menu -->

					<h3 class="control-sidebar-heading">Tasks Progress</h3>
					<ul class="control-sidebar-menu">
						<li>
							<a href="javascript:;">
								<h4 class="control-sidebar-subheading">
									Custom Template Design
									<span class="pull-right-container">
										<span class="label label-danger pull-right">70%</span>
									</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-danger" style="width: 70%"></div>
								</div>
							</a>
						</li>
					</ul>
					<!-- /.control-sidebar-menu -->

				</div>
				<!-- /.tab-pane -->
				<!-- Stats tab content -->
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
				<!-- /.tab-pane -->
				<!-- Settings tab content -->
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">General Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading">
								Report panel usage
								<input type="checkbox" class="pull-right" checked>
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
		<!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

	
</body>
</html>