package com.cpy.springPortfolio.member.service;

import java.util.List;

import com.cpy.springPortfolio.admin.model.dto.AdminDTO;
import com.cpy.springPortfolio.member.model.dto.MemberDTO;

public interface MemberService {
	public List<MemberDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord);
	public MemberDTO getSelectOne(MemberDTO paramDto);
	public int setInsert(MemberDTO paramDto);
	public int setUpdate(MemberDTO paramDto);
	public int setDelete(MemberDTO paramDto);
	
	public MemberDTO getLogin(MemberDTO paramDto);
	public AdminDTO getAdminLogin(AdminDTO paramDto);
	public int getTotalRecord(String searchGubun ,String searchData);
}

