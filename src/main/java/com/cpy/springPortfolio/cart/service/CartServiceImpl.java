package com.cpy.springPortfolio.cart.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.cpy.springPortfolio.cart.model.dao.CartDAO;
import com.cpy.springPortfolio.cart.model.dto.CartDTO;


@Service
public class CartServiceImpl implements CartService{
	
	@Inject
	CartDAO cartDao;
	
	@Override
	public List<CartDTO> getSelectAll(CartDTO paramDto) {
		return cartDao.getSelectAll(paramDto);
	}

	@Override
	public CartDTO getSelectOne(CartDTO paramDto) {
		return cartDao.getSelectOne(paramDto);
	}

	@Override
	public int setInsert(CartDTO paramDto) {
		return cartDao.setInsert(paramDto);
	}

	@Override
	public int setUpdate(CartDTO paramDto) {
		return cartDao.setUpdate(paramDto);
	}

	@Override
	public int setDelete(CartDTO paramDto) {
		return cartDao.setDelete(paramDto);
	}
	
	@Override
	public int getTotalRecord(CartDTO paramDto) {
		return cartDao.getTotalRecord(paramDto);
	}

	@Override
	public List<CartDTO> getCartAll(CartDTO paramDto) {
		return cartDao.getCartAll(paramDto);
	}

}
