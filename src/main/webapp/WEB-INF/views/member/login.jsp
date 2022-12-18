<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>로그인</h2>

<form name="loginForm">
	<table border="1" width="80%">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="login();">로그인</button>
			</td>
		</tr>
	</table>
</form>

<script>
	function login(){
		if(confirm('로그인 할까요?')){
			if(document.loginForm.id.value == "admin"){
				document.loginForm.action = "${path }/member/adminLoginProc";
				document.loginForm.method = "post";
				document.loginForm.submit();
			}else{
				document.loginForm.action = "${path }/member/loginProc";
				document.loginForm.method = "post";
				document.loginForm.submit();
			}
				
		}
	}
</script>