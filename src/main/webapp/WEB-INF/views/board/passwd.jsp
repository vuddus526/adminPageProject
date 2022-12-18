<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>비밀글확인</h2>

<form name="loginForm">
<input type="text" name="no" value="${imsiDto.no }">
<input type="text" name="tbl" value="${imsiDto.tbl }">
	<table border="1" width="80%">
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="check();">확인</button>
			</td>
		</tr>
	</table>
</form>

<script>
	function check(){
		document.loginForm.action = "${path }/board/passwdProc";
		document.loginForm.method = "post";
		document.loginForm.submit();	
	}
</script>