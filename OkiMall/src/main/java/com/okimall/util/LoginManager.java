package com.okimall.util;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoginManager implements HttpSessionBindingListener {
	
	// 싱글톤패턴
	private static LoginManager loginMng = null;
	
	// 로그인한 접속자 저장
	private static Hashtable<HttpSession, String> loginUsers = new Hashtable<HttpSession, String>();
	
	public static synchronized LoginManager getInstance() {
		
		//첫 접속자에 의하여  객체 생성. 2번째 부터는 생성된 객체 참조
		if(loginMng == null) {
			loginMng = new LoginManager();
		}
		
		return loginMng;
	}
	
	// 세션정보 추가시 호출되는 이벤트
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		
		loginUsers.put(event.getSession(), event.getName()); //getSession() 세션객체 확보 세션객체는 클라이언트가 서버에 접속이 되는 순간에 세션객체가 생성이 된다.
		System.out.println(event.getName() + "로그인 완료");
		System.out.println("현재접속자수:" + getUserCount());

	}

	// 세션소멸시 호출되는 이벤트
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}
	
	public void removeSession(String userId) {
		Enumeration<HttpSession> e = loginUsers.keys();
		HttpSession session = null;
		
		while(e.hasMoreElements()) {
			session = e.nextElement();
			if(loginUsers.get(session).equals(userId)) {
				session.invalidate();
			}
		}
 	}
	
	// 아이디 중복 로그인 방지목적으로 체크
	public boolean isUsing(String userId) {
		return loginUsers.containsValue(userId);
	}
	
	// 로그인을 확인 후 아이디를 세션형태로 저장
	public void setSession(HttpSession session, String userId) {
		session.setAttribute(userId, this);
	}
	
	public String getUserID(HttpSession session) {
		return (String)loginUsers.get(session);
	}
	
	// 현재 접속자수 확인
	private int getUserCount() {
		return loginUsers.size();
	}
	
	// 현재 접속중인 모든 사용자 아이디를 출력
	public void printloginUsers() {
		Enumeration<HttpSession> e = loginUsers.keys();
		HttpSession session = null;
		System.out.println("================================");
		int i = 0;
		while(e.hasMoreElements()) {
			session = e.nextElement();
			System.out.println((++i) + "접속자:" + loginUsers.get(session));
		}
		System.out.println("================================");
	}
	
	// 현재 접속중인 모든 사용자 리스트를 리턴
	public Collection<String> getUsers(){
		Collection<String> collection = loginUsers.values();
		return collection;
	}

}
