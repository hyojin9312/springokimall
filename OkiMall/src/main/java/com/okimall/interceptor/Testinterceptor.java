package com.okimall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


// /member/** 주소로 요청하는 부분을 인터셉터 설정을 하여 제어를 하겠다.
public class Testinterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 인터셉터 설정이 관리자 메뉴 주소요청이 발생
		// 관리자 로그인 세션여부 체크
		// 1)정상로그인상태 : return true;
		// 2)로그인 상태 아님 : 관리자 로그인 주소이동 작업, return false;
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	
	
}
