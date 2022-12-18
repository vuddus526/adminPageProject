package com.cpy.springPortfolio._common;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {
	public String[] getServerInfo(HttpServletRequest request) {
		String referer = request.getHeader("REFERER");
		if(referer == null || referer.trim().equals("")) {
			referer = "";
		}
		
		String path = request.getContextPath();
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI().toString();
		//String ip = Inet4Address.getLocalHost().getHostAddress();
		
		String ip ="";
		try {
			ip= Inet4Address.getLocalHost().getHostAddress();
		}catch(UnknownHostException e) {
			//e.printStackTrace();
		}
		
		String ip6 = request.getHeader("X-Forwarded-For");  
        if (ip6 == null || ip6.length() == 0 || "unknown".equalsIgnoreCase(ip6)) {  
        	ip6 = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip6 == null || ip6.length() == 0 || "unknown".equalsIgnoreCase(ip6)) {  
        	ip6 = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip6 == null || ip6.length() == 0 || "unknown".equalsIgnoreCase(ip6)) {  
        	ip6 = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip6 == null || ip6.length() == 0 || "unknown".equalsIgnoreCase(ip6)) {  
        	ip6 = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip6 == null || ip6.length() == 0 || "unknown".equalsIgnoreCase(ip6)) {  
        	ip6 = request.getRemoteAddr();  
        }
		
		String[] result = new String[6];
		result[0] = referer;
		result[1] = path;
		result[2] = url;
		result[3] = uri;
		result[4] = ip;
		result[5] = ip6;

		return result;
	}
	
	public String[] getUrlCheck(String uri) {
		
		String[] imsiArray = uri.split("/");
		String folderName = imsiArray[imsiArray.length -2];
		String fileName = imsiArray[imsiArray.length -1];
	
		
		String[] result = new String[2];
		result[0] = folderName;
		result[1] = fileName;
		
		return result;
		
	}
	
	public String[] getSessionCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		int sessionNo = 0;
		if(session.getAttribute("sessionNo") != null ) {
			sessionNo = (Integer) session.getAttribute("sessionNo");
		}
		
		String sessionId = "";
		if(session.getAttribute("sessionId") != null ) {
			sessionId = (String) session.getAttribute("sessionId");
		}
		
		String sessionName = "";
		if(session.getAttribute("sessionName") != null ) {
			sessionName = (String) session.getAttribute("sessionName");
		}
		
		String[] result = new String[3];
		result[0] = sessionNo + "";
		result[1] = sessionId;
		result[2] = sessionName;
		
		return result;
	}
	
	public String getBlankCheck(String str) {
		String result = str;
		
		if(result == null || result.trim().equals("")) {
			result = "";
		}
		return result;
	}
	
	public int getNumberCheck(String str, int defaultNumber) {
		String imsiDefaultNumber = String.valueOf(defaultNumber);
		
		if(str == null || str.trim().equals("")) {
			str = imsiDefaultNumber;
		}
		
		int result = 0;
		try {
			//Double.parseDouble(str);
			//return Integer.parseInt(str);
			result = Integer.parseInt(str);
		}catch(Exception e) {
			//return Integer.parseInt(imsiDefaultNumber);
			result = defaultNumber;
		}finally {
			return result;
		}
	}
	
	public int[] pager(int pageSize, int blockSize, int totalRecord, int pageNumber) {
		
		int jj = totalRecord - pageSize * (pageNumber - 1);
		int startRecord = pageSize * (pageNumber - 1) + 1;
		int lastRecord = pageSize * pageNumber;
		
		if(lastRecord > totalRecord) {
			lastRecord = totalRecord;
		}
		
		int totalPage = 0;
		int startPage = 1;
		int lastPage = 1;
		
		if(totalRecord > 0) {
			totalPage = totalRecord / pageSize + (totalRecord % pageSize == 0 ? 0 : 1);
			startPage = (pageNumber / blockSize - (pageNumber % blockSize != 0 ? 0 : 1)) * blockSize + 1;
			lastPage = startPage + blockSize - 1;
			
			if(lastPage > totalPage) {
				lastPage = totalPage;
			}
		}
		
		int[] result = new int[6];
		result[0] = jj;
		result[1] = startRecord;
		result[2] = lastRecord;
		result[3] = totalPage;
		result[4] = startPage;
		result[5] = lastPage;
		return result;
	}
}
