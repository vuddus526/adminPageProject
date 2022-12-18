package com.cpy.springPortfolio.cart.model.dao;

import java.util.List;

import com.cpy.springPortfolio.cart.model.dto.CartDTO;

public interface CartDAO {
	public List<CartDTO> getSelectAll(CartDTO paramDto);
	public CartDTO getSelectOne(CartDTO paramDto);
	public int setInsert(CartDTO paramDto);
	public int setUpdate(CartDTO paramDto);
	public int setDelete(CartDTO paramDto);
	
	public int getTotalRecord(CartDTO paramDto);
	public List<CartDTO> getCartAll(CartDTO paramDto);
}
