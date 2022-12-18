<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>방명록등록</h2>

<form name="chugaForm">
<table border="1" width="80%">
	<tr>
		<td>이름</td>
		<td><input type="text" name="name"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email"></td>
	</tr>	
	<tr>
		<td>내용</td>
		<td><textarea name="content" style="width:200px; height: 100px"></textarea></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="passwd"></td>
	</tr>	
	<tr>
		<td colspan="2">
			<button type="button" onClick="save();">저장하기</button>
		</td>
	</tr>
</table>
</form>

<script>
function save() {
	if (confirm('저장하시겠습니까?')) {
		document.chugaForm.action = "${path}/guestBook/chugaProc";
		document.chugaForm.method = "post";
		document.chugaForm.submit();
	}
}
</script>