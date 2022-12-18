package com.cpy.springPortfolio.board.util;

import javax.servlet.http.HttpServletRequest;

import com.cpy.springPortfolio._common.Util;

public class BoardUtil extends Util {
	
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
		
		if(searchGubun.equals("writer")) {
			
		} else if(searchGubun.equals("subject")) {
			
		} else if(searchGubun.equals("content")) {
			
		} else if(searchGubun.equals("writer_subject_content")) {
			
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
