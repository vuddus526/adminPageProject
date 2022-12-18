package com.cpy.springPortfolio.product.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cpy.springPortfolio.product.model.dto.ProductDTO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<ProductDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchGubun", searchGubun);
		map.put("searchData", searchData);
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		return sqlSession.selectList("product.getSelectAll", map);
	}

	@Override
	public ProductDTO getSelectOne(ProductDTO paramDto) {
		return sqlSession.selectOne("product.getSelectOne", paramDto);
	}

	@Override
	public int setInsert(ProductDTO paramDto) {
		return sqlSession.insert("product.setInsert", paramDto);
	}

	@Override
	public int setUpdate(ProductDTO paramDto) {
		return sqlSession.update("product.setUpdate", paramDto);
	}

	@Override
	public int setDelete(ProductDTO paramDto) {
		return sqlSession.delete("product.setDelete", paramDto);
	}

	@Override
	public int getTotalRecord(String searchGubun, String searchData) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchGubun", searchGubun);
		map.put("searchData", searchData);
		return sqlSession.selectOne("product.getTotalRecord", map);
	}
}
