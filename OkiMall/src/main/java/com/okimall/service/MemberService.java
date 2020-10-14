package com.okimall.service;

import com.okimall.domain.MemberVO;
import com.okimall.dto.MemberDTO;

public interface MemberService {
	
	// 회원가입
	public void join(MemberVO vo) throws Exception;
	
	// 아이디중복체크
	public int idcheck(String mb_id) throws Exception;
	
	// 회원정보 수정
	public void modifyUser(MemberVO vo) throws Exception;
	
	// 로그인
	public MemberDTO login(MemberDTO dto) throws Exception;
	
}
