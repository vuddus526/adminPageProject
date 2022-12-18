<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>방명록상세보기</h2>

<table border="1" width="80%">
	<tr>
		<td>No.</td>
		<td>${dto.no }</td>
	</tr>
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
		<td><textarea name="content" style="width:200px; height: 100px" readonly>${dto.content }</textarea></td>
	</tr>
	<tr>
		<td>등록일</td>
		<td>${dto.regiDate }</td>
	</tr>
</table>

<div style="border: 0px solid blue; width: 80%; paddion: 10px 0px;" align="center">
<table border="1" width="100%">
	<tr>
		<td width="100px">이전 작성자</td>
		<td>
			<c:if test="${dto.preNo > 0 }">
				<a href="#" onclick="move('view', '${pageNumber }', '${dto.preNo }', '${searchGubun }', '${searchData }');">${dto.preName }</a>
			</c:if>
			<c:if test="${dto.preNo == 0 }">
				이전 작성자가 없습니다.
			</c:if>
		</td>
	</tr>
	<tr>
		<td>다음 작성자</td>
		<td>
			<c:if test="${dto.nxtNo > 0 }">
				<a href="#" onclick="move('view', '${pageNumber }', '${dto.nxtNo }', '${searchGubun }', '${searchData }');">${dto.nxtName }</a>
			</c:if>
			<c:if test="${dto.nxtNo == 0 }">
				다음 작성자가 없습니다.
			</c:if>
		</td>
	</tr>
</table>
</div>

[이전글]
&nbsp;
[다음글]

<div style="border: 0px solid blue; width: 80%; margin-top: 10px;" align="right">
	|
	<a href="#" onClick="move('list', '${pageNumber }', '', '', '');">전체목록</a>
	|
	<a href="#" onClick="move('list', '${pageNumber }', '', '${searchGubun }', '${searchData }');">목록</a>
	|
	<a href="#" onClick="move('chuga', '${pageNumber }', '', '${searchGubun }', '${searchData }');">등록</a>
	|
	<a href="#" onClick="move('sujung', '${pageNumber }', '${dto.no }', '${searchGubun }', '${searchData }');">수정</a>
	|
	<a href="#" onClick="move('sakje', '${pageNumber }', '${dto.no }', '${searchGubun }', '${searchData }');">삭제</a>
	|
</div>

<script>
function move(value1, value2, value3, value4, value5) {
	location.href = '${path}/guestBook/' + value1 + '?pageNumber=' + value2 + '&no=' + value3 + '&searchGubun=' + value4 + '&searchData=' + value5;
}
</script>