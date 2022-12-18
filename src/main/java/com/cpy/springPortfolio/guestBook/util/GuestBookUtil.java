package com.cpy.springPortfolio.guestBook.util;

import javax.servlet.http.HttpServletRequest;

import com.cpy.springPortfolio._common.Util;

public class GuestBookUtil extends Util {
	
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
		
		if(searchGubun.equals("name")) {
			
		} else if(searchGubun.equals("content")) {
			
		} else if(searchGubun.equals("name_content")) {
			
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
