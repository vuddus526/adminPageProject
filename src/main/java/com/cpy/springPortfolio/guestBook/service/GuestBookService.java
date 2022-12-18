package com.cpy.springPortfolio.guestBook.service;

import java.util.List;

import com.cpy.springPortfolio.guestBook.model.dto.GuestBookDTO;

public interface GuestBookService {
	public List<GuestBookDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord);
	public GuestBookDTO getSelectOne(GuestBookDTO paramDto);
	public int setInsert(GuestBookDTO paramDto);
	public int setUpdate(GuestBookDTO paramDto);
	public int setDelete(GuestBookDTO paramDto);
	
	public int getTotalRecord(String searchGubun ,String searchData);
}

