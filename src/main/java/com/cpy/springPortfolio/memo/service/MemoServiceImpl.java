package com.cpy.springPortfolio.memo.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.cpy.springPortfolio.memo.model.dto.MemoDTO;
import com.cpy.springPortfolio.memo.model.dao.MemoDAO;




@Service
public class MemoServiceImpl implements MemoService{
	
	@Inject
	MemoDAO memoDao;
	
	@Override
	public List<MemoDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord) {
		return memoDao.getSelectAll(searchGubun, searchData, startRecord, lastRecord);
	}

	@Override
	public MemoDTO getSelectOne(MemoDTO paramDto) {
		return memoDao.getSelectOne(paramDto);
	}

	@Override
	public int setInsert(MemoDTO paramDto) {
		return memoDao.setInsert(paramDto);
	}

	@Override
	public int setUpdate(MemoDTO paramDto) {
		return memoDao.setUpdate(paramDto);
	}

	@Override
	public int setDelete(MemoDTO paramDto) {
		return memoDao.setDelete(paramDto);
	}
	
	@Override
	public int getTotalRecord(String searchGubun, String searchData) {
		return memoDao.getTotalRecord(searchGubun, searchData);
	}

}
