package com.cpy.springPortfolio.guestBook.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cpy.springPortfolio.guestBook.model.dto.GuestBookDTO;

@Repository
public class GuestDAOImpl implements GuestBookDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<GuestBookDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchGubun", searchGubun);
		map.put("searchData", searchData);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		return sqlSession.selectList("guestBook.getSelectAll", map);
	}

	@Override
	public GuestBookDTO getSelectOne(GuestBookDTO paramDto) {
		return sqlSession.selectOne("guestBook.getSelectOne", paramDto);
	}

	@Override
	public int setInsert(GuestBookDTO paramDto) {
		return sqlSession.insert("guestBook.setInsert", paramDto);
	}

	@Override
	public int setUpdate(GuestBookDTO paramDto) {
		return sqlSession.update("guestBook.setUpdate", paramDto);
	}

	@Override
	public int setDelete(GuestBookDTO paramDto) {
		return sqlSession.delete("guestBook.setDelete", paramDto);
	}

	@Override
	public int getTotalRecord(String searchGubun, String searchData) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchGubun", searchGubun);
		map.put("searchData", searchData);
		return sqlSession.selectOne("guestBook.getTotalRecord", map);
	}
}
