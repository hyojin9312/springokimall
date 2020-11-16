<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script id="subCGListTemplate" type="text/x-handlebars-template">
	{{#each .}}
		<li><a href="/product/list?cate_code={{cate_code}}">{{cate_name}}</a></li>
	{{/each}}
</script>
<script>

</script>   
    
    <h1 class="my-4">OkiMall</h1>
	<div class="list-group mainCategory">
		<ul style="width: 100%; ">
		<c:forEach items="${categoryList}" var="list">
			<li class="list-group-item" value="${list.cate_code }">
				<a href="/product/list?cate_code=${list.cate_code}" >
					<span>${list.cate_name}</span>
				</a>
			</li>	
		</c:forEach>
		</ul>
	
          <%--  <a href="/product/list?cate_code=${cate_code}" class="list-group-item">상의</a>
          <a href="/product/list?cate_code=${cate_code}" class="list-group-item">하의</a>
          <a href="/product/list?cate_code=${cate_code}" class="list-group-item">아우터</a>
          <a href="/product/list?cate_code=${cate_code}" class="list-group-item">신발</a>--%>
    </div>