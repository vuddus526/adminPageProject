<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>회원삭제</h2>

<form name="sakjeForm">
<input type="hidden" name="no" value="${dto.no }">
	<table border="1" align="center">
		<tr>
			<td>아이디</td>
			<td>${dto.id }</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${dto.name }</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>${dto.phone }</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${dto.email }</td>
		</tr>
		<tr>
			<td>주민번호</td>
			<td>${dto.jumin }</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>${dto.juso1 } ${dto.juso2 } ${dto.juso3 } ${dto.juso4 }</td>
		</tr>
		<tr>
			<td>등급</td>
			<td>${dto.grade }</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="sakje();">삭제하기</button>
				&nbsp;&nbsp;
				<a href="${path }/member/list">[목록으로]</a>
			</td>
		</tr>
	</table>
</form>

<script>
	function sakje(){
		if(confirm('삭제할까요?')){
			document.sakjeForm.action = "${path }/member/sakjeProc";
			document.sakjeForm.method = "post";
			document.sakjeForm.submit();
		}
	}
</script>
