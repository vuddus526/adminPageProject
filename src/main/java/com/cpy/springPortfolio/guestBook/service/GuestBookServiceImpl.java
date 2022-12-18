package com.cpy.springPortfolio.guestBook.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.cpy.springPortfolio.guestBook.model.dao.GuestBookDAO;
import com.cpy.springPortfolio.guestBook.model.dto.GuestBookDTO;


@Service
public class GuestBookServiceImpl implements GuestBookService{
	
	@Inject
	GuestBookDAO guestBookDao;
	
	@Override
	public List<GuestBookDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord) {
		return guestBookDao.getSelectAll(searchGubun, searchData, startRecord, lastRecord);
	}

	@Override
	public GuestBookDTO getSelectOne(GuestBookDTO paramDto) {
		return guestBookDao.getSelectOne(paramDto);
	}

	@Override
	public int setInsert(GuestBookDTO paramDto) {
		return guestBookDao.setInsert(paramDto);
	}

	@Override
	public int setUpdate(GuestBookDTO paramDto) {
		return guestBookDao.setUpdate(paramDto);
	}

	@Override
	public int setDelete(GuestBookDTO paramDto) {
		return guestBookDao.setDelete(paramDto);
	}
	
	@Override
	public int getTotalRecord(String searchGubun, String searchData) {
		return guestBookDao.getTotalRecord(searchGubun, searchData);
	}

}
