package com.okimall.persistence;

import com.okimall.domain.AdminVO;
import com.okimall.dto.AdminDTO;

public interface AdminDAO {
	
	// 로그인
	public AdminVO login(AdminDTO dto) throws Exception;

	// 로그인시간 업데이트
	public void loginUpdate(String admin_id) throws Exception;
	
}
