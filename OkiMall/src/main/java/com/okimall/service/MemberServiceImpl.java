package com.okimall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okimall.domain.MemberVO;
import com.okimall.dto.MemberDTO;
import com.okimall.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder passEncrypt;
	
	// MemberVO 가져오기
	@Override
	public MemberVO readUser(String mb_id) throws Exception {
		
		return dao.readUser(mb_id);
	}
	
	// 회원가입
	@Override
	public void join(MemberVO vo) throws Exception {
		
		dao.join(vo);
	}
	
	// 아이디중복체크
	@Override
	public int idcheck(String mb_id) throws Exception {
		
		return dao.idcheck(mb_id);
	}
	
	//회원정보 수정
	@Override
	public void modifyUser(MemberVO vo) throws Exception {
		
		dao.modifyUser(vo);
	}

	//비밀번호 변경
	@Override
	public void changePw(MemberDTO dto) throws Exception {
		
		dao.changePw(dto);
		
	}
	

	// 회원탈퇴
	@Override
	public void deleteUser(String mb_id) throws Exception {
		
		dao.deleteUser(mb_id);
		
	}
	
	// 로그인
	@Transactional
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		
		MemberDTO memDTO = dao.login(dto);
		
		if(memDTO != null) {
			
			if(passEncrypt.matches(dto.getMb_pw(), memDTO.getMb_pw())) {
				dao.loginupdate(memDTO.getMb_id());// 로그인 시간저장
			}else {
				memDTO = null;
			}
			
		}
		
		return memDTO;
	}

	// 회원조회
	@Override
	public List<MemberVO> memberRead(MemberVO vo) throws Exception {
		
		return dao.memberRead(vo);
	}
	


}
