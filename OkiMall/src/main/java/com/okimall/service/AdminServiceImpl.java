package com.okimall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okimall.domain.AdminVO;
import com.okimall.dto.AdminDTO;
import com.okimall.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO dao;
	
	
	// 로그인
	@Override
	public AdminVO login(AdminDTO dto) throws Exception {
		
		AdminVO vo = dao.login(dto);
		dao.loginUpdate(dto.getAdmin_id());
		
		return vo;
	}



}
