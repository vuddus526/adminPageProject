package com.cpy.springPortfolio.product.service;

import java.util.List;

import com.cpy.springPortfolio.product.model.dto.ProductDTO;


public interface ProductService {
	public List<ProductDTO> getSelectAll(String searchGubun, String searchData, int startRecord, int lastRecord);
	public ProductDTO getSelectOne(ProductDTO paramDto);
	public int setInsert(ProductDTO paramDto);
	public int setUpdate(ProductDTO paramDto);
	public int setDelete(ProductDTO paramDto);
	
	public int getTotalRecord(String searchGubun ,String searchData);
}

