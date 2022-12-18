package com.cpy.springPortfolio.memo.model.dao;

import java.util.List;

import com.cpy.springPortfolio.memo.model.dto.MemoDTO;



public interface MemoDAO {
	public List<MemoDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord);
	public MemoDTO getSelectOne(MemoDTO paramDto);
	public int setInsert(MemoDTO paramDto);
	public int setUpdate(MemoDTO paramDto);
	public int setDelete(MemoDTO paramDto);
	
	public int getTotalRecord(String searchGubun ,String searchData);
}
