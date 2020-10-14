package com.okimall.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.okimall.domain.MemberVO;
import com.okimall.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	private final static String NS = "com.okimall.mappers.MemberMapper";
	
	@Autowired
	private SqlSession session;
	
	//회원가입
	@Override
	public void join(MemberVO vo) throws Exception {
		
		session.insert(NS+".join",vo);
		
	}
	
	// 아이디중복체크
	@Override
	public int idcheck(String mb_id) throws Exception {
		
		return session.selectOne(NS+".idcheck", mb_id);
	}
	
	// 회원정보 수정
	@Override
	public void modifyUser(MemberVO vo) throws Exception {
		
		session.update(NS+".modifyUser", vo);
		
	}
	
	//로그인
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		
		return session.selectOne(NS+".login", dto);
	}
	
	//로그인시간 업데이트
	@Override
	public void loginupdate(String mb_id) throws Exception {
		
		session.update(NS+".loginupdate", mb_id);
		
	}

	


}
