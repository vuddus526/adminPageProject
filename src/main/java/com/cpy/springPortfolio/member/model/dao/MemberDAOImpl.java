package com.cpy.springPortfolio.member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cpy.springPortfolio.admin.model.dto.AdminDTO;
import com.cpy.springPortfolio.member.model.dto.MemberDTO;


@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<MemberDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchGubun", searchGubun);
		map.put("searchData", searchData);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		return sqlSession.selectList("member.getSelectAll", map);
	}

	@Override
	public MemberDTO getSelectOne(MemberDTO paramDto) {
		return sqlSession.selectOne("member.getSelectOne", paramDto);
	}

	@Override
	public int setInsert(MemberDTO paramDto) {
		return sqlSession.insert("member.setInsert", paramDto);
	}

	@Override
	public int setUpdate(MemberDTO paramDto) {
		return sqlSession.update("member.setUpdate", paramDto);
	}

	@Override
	public int setDelete(MemberDTO paramDto) {
		return sqlSession.delete("member.setDelete", paramDto);
	}

	@Override
	public MemberDTO getLogin(MemberDTO paramDto) {
		return sqlSession.selectOne("member.getLogin", paramDto);
	}

	@Override
	public int getTotalRecord(String searchGubun, String searchData) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchGubun", searchGubun);
		map.put("searchData", searchData);
		return sqlSession.selectOne("member.getTotalRecord", map);
	}

	@Override
	public AdminDTO getAdminLogin(AdminDTO paramDto) {
		return sqlSession.selectOne("member.getAdminLogin", paramDto);
	}
}
