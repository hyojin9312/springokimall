package com.okimall.service;

import com.okimall.domain.AdminVO;
import com.okimall.dto.AdminDTO;

public interface AdminService {
	
	// 로그인
	public AdminVO login(AdminDTO dto) throws Exception;

}
