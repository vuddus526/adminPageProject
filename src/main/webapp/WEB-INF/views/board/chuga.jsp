<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>
		
<c:choose>
	<c:when test="${dto.no > 0 }">
		<h2>답변글 작성</h2>
		<c:set var="subject" value="${dto.subject }"/>
	</c:when>
	<c:otherwise>
		<h2>게시글 등록</h2>
	</c:otherwise>
</c:choose>

<form name="chugaForm">
<input type="hidden" name="no" value="${dto.no }">
<input type="hidden" name="tbl" value="${dto.tbl }">
	<table border="1" align="center">
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="subject" value="${subject }"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" style="width:200px; height:100px;">${dto.content }</textarea></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td>공지글</td>
			<td>
				<input type="checkbox" name="noticeGubun" value="T">공지글체크
			</td>
		</tr>
		<tr>
			<td>비밀글</td>
			<td>
				<input type="checkbox" name="secretGubun" value="T">비밀글체크
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<c:if test="${dto.no > 0 }">
					<c:set var="btnName" value ="답변하기"></c:set>
				</c:if>
				<c:if test="${dto.no == 0 }">
					<c:set var="btnName" value ="등록하기"></c:set>
				</c:if>
				<button type="button" id="btnChuga">${btnName }</button>
			</td>
		</tr>
	</table>
</form>

<script>
$(document).ready(function(){
	$("#btnChuga").click(function(){
		if(confirm('가입하시겠습니까?')){
			document.chugaForm.action = "${path }/board/chugaProc";
			document.chugaForm.method = "post";
			document.chugaForm.submit();
		}
	});
});
</script>