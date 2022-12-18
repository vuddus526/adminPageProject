package com.cpy.springPortfolio.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.cpy.springPortfolio.board.model.dao.BoardDAO;
import com.cpy.springPortfolio.board.model.dto.BoardDTO;


@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	BoardDAO boardDao;
	
	@Override
	public List<BoardDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord, String tbl) {
		return boardDao.getSelectAll(searchGubun, searchData, startRecord, lastRecord, tbl);
	}

	@Override
	public BoardDTO getSelectOne(BoardDTO paramDto) {
		return boardDao.getSelectOne(paramDto);
	}
	
	@Override
	public BoardDTO getPasswdCheck(BoardDTO paramDto) {
		return boardDao.getPasswdCheck(paramDto);
	}

	@Override
	public int setInsert(BoardDTO paramDto) {
		return boardDao.setInsert(paramDto);
	}

	@Override
	public int setUpdate(BoardDTO paramDto) {
		return boardDao.setUpdate(paramDto);
	}

	@Override
	public int setDelete(BoardDTO paramDto) {
		return boardDao.setDelete(paramDto);
	}
	
	@Override
	public int getTotalRecord(String searchGubun, String searchData) {
		return boardDao.getTotalRecord(searchGubun, searchData);
	}

	@Override
	public int getMaxNum() {
		return boardDao.getMaxNum();
	}

	@Override
	public int getMaxRefNo() {
		return boardDao.getMaxRefNo();
	}

	@Override
	public int getMaxNoticeNo() {
		return boardDao.getMaxNoticeNo();
	}

	@Override
	public void setUpdateHit(BoardDTO paramDto) {
		boardDao.setUpdateHit(paramDto);
	}

	@Override
	public void setUpdateReLevel(BoardDTO paramDto) {
		boardDao.setUpdateReLevel(paramDto);
	}


}
