<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>게시판삭제</h2>

<form name="sakjeForm">
<input type="hidden" name="no" value="${dto.no }">
<input type="hidden" name="tbl" value="${dto.tbl }">
<input type="hidden" name="childCounter" value="${dto.childCounter }">
	<table border="1" align="center">
		<tr>
			<td>작성자</td>
			<td>${dto.writer }</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${dto.email }</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${dto.subject }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" style="width:200px; height:100px;" readonly>${dto.content }</textarea></td>
		</tr>
		<tr>
			<td>공지글</td>
			<td>
				<c:if test="${dto.noticeNo > 0 }">
					O
				</c:if>
				<c:if test="${dto.noticeNo <= 0 }">
					X
				</c:if>
			</td>
		</tr>
		<tr>
			<td>비밀글</td>
			<td>
				<c:if test="${dto.secretGubun == 'T' }">
					O
				</c:if>
				<c:if test="${dto.secretGubun != 'T' }">
					X
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" id="btnSujung">삭제하기</button>
			</td>
		</tr>
	</table>
</form>

<script>
$(document).ready(function(){
	$("#btnSujung").click(function(){
		if(confirm('삭제하시겠습니까?')){
			document.sakjeForm.action = "${path }/board/sakjeProc";
			document.sakjeForm.method = "post";
			document.sakjeForm.submit();
		}
	});
});
</script>