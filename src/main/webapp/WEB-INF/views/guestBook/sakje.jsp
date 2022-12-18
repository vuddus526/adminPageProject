<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>매모삭제</h2>

<form name="sakjeForm">
<input type="hidden" name="no" value="${dto.no }">
<table border="1" width="80%">
	<tr>
		<td>이름</td>
		<td>${dto.name }</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>${dto.email }</td>
	</tr>			
	<tr>
		<td>내용</td>
		<td>${dto.content }</td>
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
			<button type="button" onClick="sakje();">삭제하기</button>
			&nbsp;
			<a href="${path }/guestBook/list">목록</a>
		</td>
	</tr>
</table>
</form>

<script>
function sakje() {
	document.sakjeForm.action = "${path}/guestBook/sakjeProc";
	document.sakjeForm.method = "post";
	document.sakjeForm.submit();
}
</script>