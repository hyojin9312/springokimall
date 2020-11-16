<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="/">OkiMall</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          
          <%-- 로그인안한 상태 --%>
          <c:if test="${sessionScope.user == null}">
           <li class="nav-item active">
            <a class="nav-link" href="/">Home
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/member/login">로그인</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/member/join">회원가입</a>
          </li>
          </c:if>
          
          <%--로그인 한 상태 --%>
          <c:if test="${sessionScope.user != null}">
          <li class="nav-item active">
            <a class="nav-link" href="/">
              <span class="sr-only">${sessionScope.user.mb_name}님</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/member/logout">로그아웃</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/member/checkPw?url=modify">회원수정</a>
          </li>  
          <li class="nav-item">  
            <a class="nav-link" href="/member/checkPw?url=delete">회원탈퇴</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/member/checkPw?url=changePw">비밀번호 변경</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/cart/list">장바구니</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/order/list">주문조회</a>
          </li>
          </c:if>
        </ul>
      </div>
    </div>
  </nav>
  