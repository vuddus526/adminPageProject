<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>   

<h2>회원상세보기</h2>

<table border="1" width="80%">
	<tr>
		<td>No.</td>
		<td>${dto.no }</td>
	</tr>
	<tr>
		<td>아이디</td>
		<td>${dto.id }</td>
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
		<td>
			<c:if test="${dto.grade == '최우수회원'}">
				<c:set var="textColor" value="red" />
			</c:if>
			<c:if test="${dto.grade == '우수회원'}">
				<c:set var="textColor" value="blue" />
			</c:if>
			<c:if test="${dto.grade == '일반회원'}">
				<c:set var="textColor" value="black" />
			</c:if>
			
			<font style="color: ${textColor }; font-wright: bold;">${dto.grade }</font>
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
		<td width="100px">이전 회원</td>
		<td>
			<c:if test="${dto.preNo > 0 }">
				<a href="#" onclick="move('view', '${pageNumber }', '${dto.preNo }', '${searchGubun }', '${searchData }');">${dto.preId }</a>
			</c:if>
			<c:if test="${dto.preNo == 0 }">
				이전 회원이 없습니다.
			</c:if>
		</td>
	</tr>
	<tr>
		<td>다음 회원</td>
		<td>
			<c:if test="${dto.nxtNo > 0 }">
				<a href="#" onclick="move('view', '${pageNumber }', '${dto.nxtNo }', '${searchGubun }', '${searchData }');">${dto.nxtId }</a>
			</c:if>
			<c:if test="${dto.nxtNo == 0 }">
				다음 회원이 없습니다.
			</c:if>
		</td>
	</tr>
</table>
</div>

<div style="border: 0px solid blue; width: 80%; margin: 30px" align="right">
|
<a href="#" onclick="move('list', '', '', '', '');">전체목록</a>
|
<a href="#" onclick="move('list', '', '', '', '');">목록</a>
|
<a href="#" onclick="move('chuga', '', '', '', '');">등록</a>
|
<a href="#" onclick="move('sujung', '', '${dto.no }', '', '');">수정</a>
|
<a href="#" onclick="move('sakje', '', '${dto.no }', '', '');">삭제</a>
|
</div>

<script>
	function move(value1, value2, value3, value4, value5){
		location.href = '${path }/member/' + value1 + '?pageNumber=' + value2 + '&no=' + value3 + '&searchGubun=' + value4 + '&searchData=' + value5;
	}
</script>
