package com.cpy.springPortfolio.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cpy.springPortfolio.board.model.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<BoardDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord, String tbl) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchGubun", searchGubun);
		map.put("searchData", searchData);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		map.put("tbl", tbl);
		return sqlSession.selectList("board.getSelectAll", map);
	}

	@Override
	public BoardDTO getSelectOne(BoardDTO paramDto) {
		return sqlSession.selectOne("board.getSelectOne", paramDto);
	}

	@Override
	public BoardDTO getPasswdCheck(BoardDTO paramDto) {
		return sqlSession.selectOne("board.getPasswdCheck", paramDto);
	}

	@Override
	public int setInsert(BoardDTO paramDto) {
		return sqlSession.insert("board.setInsert", paramDto);
	}

	@Override
	public int setUpdate(BoardDTO paramDto) {
		return sqlSession.update("board.setUpdate", paramDto);
	}

	@Override
	public int setDelete(BoardDTO paramDto) {
		return sqlSession.delete("board.setDelete", paramDto);
	}

	@Override
	public int getTotalRecord(String searchGubun, String searchData) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchGubun", searchGubun);
		map.put("searchData", searchData);
		return sqlSession.selectOne("board.getTotalRecord", map);
	}

	@Override
	public int getMaxNum() {
		return sqlSession.selectOne("board.getMaxNum");
	}

	@Override
	public int getMaxRefNo() {
		return sqlSession.selectOne("board.getMaxRefNo");
	}

	@Override
	public int getMaxNoticeNo() {
		return sqlSession.selectOne("board.getMaxNoticeNo");
	}

	@Override
	public void setUpdateHit(BoardDTO paramDto) {
		sqlSession.update("board.setUpdateHit", paramDto);
	}

	@Override
	public void setUpdateReLevel(BoardDTO paramDto) {
		sqlSession.update("board.setUpdateReLevel", paramDto);
	}

}
