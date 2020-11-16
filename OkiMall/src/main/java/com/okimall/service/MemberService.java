package com.okimall.service;

import java.util.List;

import com.okimall.domain.MemberVO;
import com.okimall.dto.MemberDTO;

public interface MemberService {
	
	// MemberVO 가져오기
	public MemberVO readUser(String mb_id) throws Exception;
		
	// 회원가입
	public void join(MemberVO vo) throws Exception;
	
	// 아이디중복체크
	public int idcheck(String mb_id) throws Exception;
	
	// 회원정보 수정
	public void modifyUser(MemberVO vo) throws Exception;
	
	// 비밀번호 변경
	public void changePw(MemberDTO dto) throws Exception;
	
	// 회원탈퇴
	public void deleteUser(String mb_id) throws Exception;
	
	// 로그인
	public MemberDTO login(MemberDTO dto) throws Exception;
	
	// 회원조회
	public List<MemberVO> memberRead(MemberVO vo) throws Exception;
	
}
