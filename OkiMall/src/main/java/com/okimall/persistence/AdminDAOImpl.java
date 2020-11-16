package com.okimall.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.okimall.domain.AdminVO;
import com.okimall.dto.AdminDTO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	private final static String NS = "com.okimall.mappers.AdminMapper";
	
	@Autowired
	private SqlSession session;
	
	// 로그인
	@Override
	public AdminVO login(AdminDTO dto) throws Exception {
		
		return session.selectOne(NS + ".login", dto);
		
	}
	
	// 로그인시간 업데이트
	@Override
	public void loginUpdate(String admin_id) throws Exception {
		
		session.update(NS+".loginUpdate", admin_id);
		
	}

}
