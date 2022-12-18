package com.cpy.springPortfolio.product.util;

import javax.servlet.http.HttpServletRequest;

import com.cpy.springPortfolio._common.Util;

public class ProductUtil extends Util {
	
	public String[] getSearchCheck(HttpServletRequest request) {
		String searchGubun = request.getParameter("searchGubun");
		String searchData = request.getParameter("searchData");
		
		if(searchGubun == null || searchGubun.trim().equals("")) { 
			searchGubun = ""; 
		}
		
		if(searchData == null || searchData.trim().equals("")) { 
			searchData = ""; 
		}
		
		if(searchGubun.equals("") || searchData.equals("")) { 
			searchGubun = "";
			searchData = ""; 
		}
		
		if(searchGubun.equals("productName")) {
			
		} else if(searchGubun.equals("productPrice")) {
			
		} else if(searchGubun.equals("productDescription")) {
			
		} else if(searchGubun.equals("productName_productPrice_productDescription")) {
			
		} else {
			searchGubun = "";
			searchData = "";
		}
		
		String[] result = new String[2];
		result[0] = searchGubun;
		result[1] = searchData;
		
		return result;
	}
}
