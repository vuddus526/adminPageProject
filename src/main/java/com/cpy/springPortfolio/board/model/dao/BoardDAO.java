package com.cpy.springPortfolio.board.model.dao;

import java.util.List;

import com.cpy.springPortfolio.board.model.dto.BoardDTO;

public interface BoardDAO {
	public List<BoardDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord, String tbl);
	public BoardDTO getSelectOne(BoardDTO paramDto);
	public int setInsert(BoardDTO paramDto);
	public int setUpdate(BoardDTO paramDto);
	public int setDelete(BoardDTO paramDto);
	
	public BoardDTO getPasswdCheck(BoardDTO paramDto);
	
	public int getTotalRecord(String searchGubun ,String searchData);
	public int getMaxNum();
	public int getMaxRefNo();
	public int getMaxNoticeNo();
	public void setUpdateHit(BoardDTO paramDto);
	public void setUpdateReLevel(BoardDTO paramDto);
}
