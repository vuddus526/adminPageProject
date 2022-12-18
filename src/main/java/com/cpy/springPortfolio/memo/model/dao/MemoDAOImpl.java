package com.cpy.springPortfolio.memo.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cpy.springPortfolio.memo.model.dto.MemoDTO;




@Repository
public class MemoDAOImpl implements MemoDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<MemoDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchGubun", searchGubun);
		map.put("searchData", searchData);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		return sqlSession.selectList("memo.getSelectAll", map);
	}

	@Override
	public MemoDTO getSelectOne(MemoDTO paramDto) {
		return sqlSession.selectOne("memo.getSelectOne", paramDto);
	}

	@Override
	public int setInsert(MemoDTO paramDto) {
		return sqlSession.insert("memo.setInsert", paramDto);
	}

	@Override
	public int setUpdate(MemoDTO paramDto) {
		return sqlSession.update("memo.setUpdate", paramDto);
	}

	@Override
	public int setDelete(MemoDTO paramDto) {
		return sqlSession.delete("memo.setDelete", paramDto);
	}

	@Override
	public int getTotalRecord(String searchGubun, String searchData) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchGubun", searchGubun);
		map.put("searchData", searchData);
		return sqlSession.selectOne("memo.getTotalRecord", map);
	}
}
