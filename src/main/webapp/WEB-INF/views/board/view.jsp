<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>게시판상세보기</h2>
    
<table border="1" width="80%">
	<tr>
		<td width="100px">작성자</td>
		<td>${dto.writer }</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>${dto.email }</td>
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
		<td>등록일</td>
		<td>${dto.regiDate }</td>
	</tr>
</table>

<div style="border: 0px solid blue; width: 80%; paddion: 10px 0px;" align="center">
<table border="1" width="100%">
	<tr>
		<td width="100px">이전글</td>
		<td>
			<c:if test="${dto.preNo > 0 }">
				<a href="#" onclick="move('view', '${pageNumber }', '${dto.preNo }', '${searchGubun }', '${searchData }');">${dto.preSubject }</a>
			</c:if>
			<c:if test="${dto.preNo == 0 }">
				이전 글이 없습니다.
			</c:if>
		</td>
	</tr>
	<tr>
		<td>다음글</td>
		<td>
			<c:if test="${dto.nxtNo > 0 }">
				<a href="#" onclick="move('view', '${pageNumber }', '${dto.nxtNo }', '${searchGubun }', '${searchData }');">${dto.nxtSubject }</a>
			</c:if>
			<c:if test="${dto.nxtNo == 0 }">
				다음 글이 없습니다.
			</c:if>
		</td>
	</tr>
</table>
</div>

[이전글]
&nbsp;
[다음글]

<div style="border: 0px solid blue; width: 80%; margin: 30px" align="right">
|
<a href="#" onclick="move('list', '', '', '', '');">전체목록</a>
|
<a href="#" onclick="move('list', '${pageNumber }', '', '${searchGubun }', '${searchData }');">목록</a>
|
<a href="#" onclick="move('chuga', '${pageNumber }', '', '${searchGubun }', '${searchData }');">등록</a>
|
<a href="#" onclick="move('chuga', '${pageNumber }', '${dto.no }', '${searchGubun }', '${searchData }');">답변</a>
|
<a href="#" onclick="move('sujung', '${pageNumber }', '${dto.no }', '${searchGubun }', '${searchData }');">수정</a>
|
<a href="#" onclick="move('sakje', '${pageNumber }', '${dto.no }', '${searchGubun }', '${searchData }');">삭제</a>
|
</div>

<script>
	function move(value1, value2, value3, value4, value5){
		location.href = '${path }/board/' + value1 + '?pageNumber=' + value2 + '&no=' + value3 + '&searchGubun=' + value4 + '&searchData=' + value5 + '&tbl=${dto.tbl }';
	}
</script>