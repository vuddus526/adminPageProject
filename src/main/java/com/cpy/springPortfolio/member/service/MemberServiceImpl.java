package com.cpy.springPortfolio.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.cpy.springPortfolio.admin.model.dto.AdminDTO;
import com.cpy.springPortfolio.member.model.dao.MemberDAO;
import com.cpy.springPortfolio.member.model.dto.MemberDTO;


@Service
public class MemberServiceImpl implements MemberService{
	
	@Inject
	MemberDAO memberDao;
	
	@Override
	public List<MemberDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord) {
		return memberDao.getSelectAll(searchGubun, searchData, startRecord, lastRecord);
	}

	@Override
	public MemberDTO getSelectOne(MemberDTO paramDto) {
		return memberDao.getSelectOne(paramDto);
	}

	@Override
	public int setInsert(MemberDTO paramDto) {
		return memberDao.setInsert(paramDto);
	}

	@Override
	public int setUpdate(MemberDTO paramDto) {
		return memberDao.setUpdate(paramDto);
	}

	@Override
	public int setDelete(MemberDTO paramDto) {
		return memberDao.setDelete(paramDto);
	}

	@Override
	public MemberDTO getLogin(MemberDTO paramDto) {
		return memberDao.getLogin(paramDto);
	}
	
	@Override
	public int getTotalRecord(String searchGubun, String searchData) {
		return memberDao.getTotalRecord(searchGubun, searchData);
	}

	@Override
	public AdminDTO getAdminLogin(AdminDTO paramDto) {
		return memberDao.getAdminLogin(paramDto);
	}

}
