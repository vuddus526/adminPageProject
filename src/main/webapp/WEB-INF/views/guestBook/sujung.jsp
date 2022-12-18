<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>방명록수정</h2>

<form name="sujungForm">
<input type="hidden" name="no" value="${dto.no }">
<table border="1" width="80%">
	<tr>
		<td>이름</td>
		<td><input type="text" name="name" value="${dto.name }"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email" value="${dto.email }"></td>
	</tr>	
	<tr>
		<td>내용</td>
		<td><input type="text" name="content" value="${dto.content }"></td>
	</tr>
	<tr>
		<td>등록일</td>
		<td>${dto.regiDate }</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>	
	<tr>
		<td colspan="2">
			<button type="button" onClick="sujung();">수정하기</button>
			&nbsp;
			<a href="${path }/guestBook/list">목록</a>
		</td>
	</tr>
</table>
</form>

<script>
function sujung() {
	document.sujungForm.action = "${path}/guestBook/sujungProc";
	document.sujungForm.method = "post";
	document.sujungForm.submit();
}
</script>