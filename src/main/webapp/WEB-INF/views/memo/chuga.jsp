<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>메모등록</h2>

<form name="chugaForm">
	<table border="1" width="80%">
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="content" style="width:300px; height:100px;"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" onclick="chuga();">등록하기</button>
				&nbsp;&nbsp;
				<a href="${path }/memo/list">[목록으로]</a>
			</td>
		</tr>
	</table>
</form>

<script>
	function chuga(){
		if(confirm('등록할까요?')){
			document.chugaForm.action = "${path }/memo/chugaProc";
			document.chugaForm.method = "post";
			document.chugaForm.submit();
		}
	}
</script>
