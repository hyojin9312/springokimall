package com.okimall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final String LOGIN = "user";
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info("======login preHandle execute....");
		HttpSession session = request.getSession();
		
		if(session.getAttribute(LOGIN) != null) {
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.info("======login postHandle execute....");
		
		HttpSession session = request.getSession();
		Object user = modelAndView.getModel().get("loginVO");
		
		if(user != null) {
			logger.info("== 로그인프로세스");
			session.setAttribute(LOGIN, user);
			
			
			Object dest = session.getAttribute("dest");
			response.sendRedirect(dest != null ? (String) dest:"/member/login");
		}
		
	}
	
}
