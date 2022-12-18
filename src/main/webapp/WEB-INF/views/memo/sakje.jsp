<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>메모삭제</h2>

<form name="sakjeForm">
<input type="hidden" name="no" value="${dto.no }">
	<table border="1" align="center">
		<tr>
			<td>작성자</td>
			<td>${dto.writer }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${dto.content }</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="sakje();">삭제하기</button>
				&nbsp;&nbsp;
				<a href="${path }/memo/list">[목록으로]</a>
			</td>
		</tr>
	</table>
</form>

<script>
	function sakje(){
		if(confirm('삭제할까요?')){
			document.sakjeForm.action = "${path }/memo/sakjeProc";
			document.sakjeForm.method = "post";
			document.sakjeForm.submit();
		}
	}
</script>
