<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<aside class="main-sidebar">
<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<!-- 로그인 안 한상태 -->
		<c:if test="${sessionScope.admin == null}">
		</c:if>
		<c:if test="${sessionScope.admin != null}">
			<div class="user-panel">
		</div>

		<!-- search form (Optional)
		<form action="#" method="get" class="sidebar-form">
			<div class="input-group">
				<input type="text" name="q" class="form-control" placeholder="Search...">
					<span class="input-group-btn">
				<button type="submit" name="search" id="search-btn" class="btn btn-flat">
					<i class="fa fa-search"></i>
				</button>
					</span>
			</div>
		</form>
		 -->
		<!-- /.search form -->

		<!-- Sidebar Menu -->
		<ul class="sidebar-menu" data-widget="tree">
			<li class="header">admin menu</li>
				<!-- Optionally, you can add icons to the links -->
				<li class="treeview">
					<a href="#">
						<i class="fa fa-link"></i> 
						<span>상품관리</span> 
						<span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
						</span> 
					</a>
					<ul class="treeview-menu">
						<li>
							<a href="/admin/product/insert">상품 등록</a>
						</li>
						<li>
							<a href="/admin/product/list">상품 목록</a>
						</li>
					</ul>
				</li>
				<li class="treeview">
					<a href="#">
						<i class="fa fa-link"></i> 
						<span>주문관리</span> 
						<span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
						</span> 
					</a>
					<ul class="treeview-menu">
						<li>
							<a href="/admin/orderlist">주문 목록</a>
						</li>
					</ul>
				</li>
				<li class="treeview">
					<a href="#">
						<i class="fa fa-link"></i> 
						<span>회원관리</span> 
						<span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
						</span> 
					</a>
					<ul class="treeview-menu">
						<li>
							<a href="/admin/list">회원목록</a>
						</li>
						<li>
							<a href="#">후기목록</a>
						</li>
					</ul>
				</li>
		</ul>
		</c:if>
		
	<!-- /.sidebar-menu -->
	</section>
<!-- /.sidebar -->
</aside>
    