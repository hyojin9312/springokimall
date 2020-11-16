package com.okimall.persistence;

import java.util.List;

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
	

	// MemberVO 가져오기
	@Override
	public MemberVO readUser(String mb_id) throws Exception {
		
		return session.selectOne(NS+".readUser", mb_id);
	}
	
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
	
	// 비밀번호 변경
	@Override
	public void changePw(MemberDTO dto) throws Exception {
		
		session.update(NS+".changePw", dto);
		
	}
	
	// 회원탈퇴

	@Override
	public void deleteUser(String mb_id) throws Exception {
		
		session.delete(NS+ ".deleteUser", mb_id);
		
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

	@Override
	public String getTime() throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(NS + ".getTime");
	}
	
	// 회원조회
	@Override
	public List<MemberVO> memberRead(MemberVO vo) throws Exception {
		
		return session.selectList(NS+".memberRead", vo);
	
	}



}
