<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>

<table border="1" align="center" width="80%">
	<tr>
		<td height="100" align="center">
			
			<jsp:include page = "../_include/inc_menu.jsp"></jsp:include>
			
		</td>
	</tr>
	<tr>
		<td height="300" align="center" style="paddiong: 30px;">
			
			<jsp:include page = "../${folderName }/${fileName }.jsp"></jsp:include>
			
		</td>
	</tr>
	<tr>
		<td height="100" align="center">
			
			<jsp:include page = "../_include/inc_bottom.jsp"></jsp:include>
			
			
		</td>
	</tr>
</table>

</body>
</html>