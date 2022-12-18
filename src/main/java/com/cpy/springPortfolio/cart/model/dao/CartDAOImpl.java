package com.cpy.springPortfolio.cart.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cpy.springPortfolio.cart.model.dto.CartDTO;


@Repository
public class CartDAOImpl implements CartDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<CartDTO> getSelectAll(CartDTO paramDto) {
		return sqlSession.selectList("cart.getSelectAll", paramDto);
	}

	@Override
	public CartDTO getSelectOne(CartDTO paramDto) {
		return sqlSession.selectOne("cart.getSelectOne", paramDto);
	}

	@Override
	public int setInsert(CartDTO paramDto) {
		return sqlSession.insert("cart.setInsert", paramDto);
	}

	@Override
	public int setUpdate(CartDTO paramDto) {
		return sqlSession.update("cart.setUpdate", paramDto);
	}

	@Override
	public int setDelete(CartDTO paramDto) {
		return sqlSession.delete("cart.setDelete", paramDto);
	}

	@Override
	public int getTotalRecord(CartDTO paramDto) {
		return sqlSession.selectOne("cart.getTotalRecord", paramDto);
	}

	@Override
	public List<CartDTO> getCartAll(CartDTO paramDto) {
		return sqlSession.selectList("cart.getCartAll");
	}
}
