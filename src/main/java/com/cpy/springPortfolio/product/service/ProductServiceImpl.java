package com.cpy.springPortfolio.product.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.cpy.springPortfolio.product.model.dao.ProductDAO;
import com.cpy.springPortfolio.product.model.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Inject
	ProductDAO productDao;
	
	@Override
	public List<ProductDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord) {
		return productDao.getSelectAll(searchGubun, searchData, startRecord, lastRecord);
	}

	@Override
	public ProductDTO getSelectOne(ProductDTO paramDto) {
		return productDao.getSelectOne(paramDto);
	}

	@Override
	public int setInsert(ProductDTO paramDto) {
		return productDao.setInsert(paramDto);
	}

	@Override
	public int setUpdate(ProductDTO paramDto) {
		return productDao.setUpdate(paramDto);
	}

	@Override
	public int setDelete(ProductDTO paramDto) {
		return productDao.setDelete(paramDto);
	}
	
	@Override
	public int getTotalRecord(String searchGubun, String searchData) {
		return productDao.getTotalRecord(searchGubun, searchData);
	}

}
