<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>
	
<h2>에러페이지</h2>

에러코드 : ${errorCode } <br>
에러설명 : ${errorMent } <br>
<a href="${imsiUrl }">이동</a>