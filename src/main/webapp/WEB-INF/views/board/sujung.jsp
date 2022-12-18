<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>게시판수정</h2>

<form name="sujungForm">
<input type="hidden" name="no" value="${dto.no }">
<input type="hidden" name="tbl" value="${dto.tbl }">
	<table border="1" width="80%" align="center">
		<tr>
			<td>제목</td>
			<td><input type="text" name="subject" value="${dto.subject }"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" style="width:200px; height:100px;">${dto.content }</textarea></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" value="${dto.email }"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td>공지글</td>
			<td>
				<c:if test="${dto.noticeNo > 0 }">
					<input type="checkbox" name="noticeGubun" value="T" checked>공지글체크
				</c:if>
				<c:if test="${dto.noticeNo <= 0 }">
					<input type="checkbox" name="noticeGubun" value="T">공지글체크
				</c:if>
			</td>
		</tr>
		<tr>
			<td>비밀글</td>
			<td>
				<c:if test="${dto.secretGubun == 'T' }">
					<input type="checkbox" name="secretGubun" value="T" checked>비밀글체크
				</c:if>
				<c:if test="${dto.secretGubun != 'T' }">
					<input type="checkbox" name="secretGubun" value="T">비밀글체크
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" id="btnSujung">수정하기</button>
			</td>
		</tr>
	</table>
</form>

<script>
$(document).ready(function(){
	$("#btnSujung").click(function(){
		if(confirm('수정하시겠습니까?')){
			document.sujungForm.action = "${path }/board/sujungProc";
			document.sujungForm.method = "post";
			document.sujungForm.submit();
		}
	});
});
</script>