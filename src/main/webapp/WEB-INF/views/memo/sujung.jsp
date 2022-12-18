<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>메모수정</h2>

<form name="sujungForm">
<input type="hidden" name="no" value="${dto.no }">
	<table border="1" align="center">
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer" value="${dto.writer }"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="content" style="width:300px; height:100px;">${dto.content }</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="sujung();">수정하기</button>
				&nbsp;&nbsp;
				<a href="${path }/memo/list">[목록으로]</a>
			</td>
		</tr>
	</table>
</form>

<script>
	function sujung(){
		if(confirm('수정할까요?')){
			document.sujungForm.action = "${path }/memo/sujungProc";
			document.sujungForm.method = "post";
			document.sujungForm.submit();
		}
	}
</script>
