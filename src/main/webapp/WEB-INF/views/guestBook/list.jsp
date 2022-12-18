<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>방명록목록</h2>

<div style="border: 0px solid blue; width: 80%; margin: 30px 0px 10px 0px;" align="left">
	<c:choose>
		<c:when test="${searchGubun == '' }">
			전체 레코드 : ${totalRecord }건
		</c:when>
		<c:otherwise>
			검색어 '${searchData }'으/로 검색된 레코드 : ${totalRecord }건
		</c:otherwise>
	</c:choose>
</div>

<table border="0" width="80%">

	<c:forEach var="dto" items="${list }">
		<tr>
			<td style="padding: 0 0 20px 0;">

				<table border="1" align="center" style="width:100%;">
					<tr>
						<td>이름</td>
						<td><a href="#" onClick="move('view', '${pageNumber }', '${dto.no }', '${searchGubun }', '${searchData }');">${dto.name}</a></td>
						<td>날짜</td>
						<td>${dto.regiDate}</td>
					</tr>
					<tr>
						<td>이메일</td>
						<td colspan="3">${dto.email}</td>
					</tr>
					<tr>
						<td colspan="4">
							${dto.content}
							<div style="text-align: right; padding: 10px 0px 0px 0px;">
								<button type="button" onClick="move('sujung', '${pageNumber }', '${dto.no }', '${searchGubun }', '${searchData }');">수정</button>
								<button type="button" onClick="move('sakje', '${pageNumber }', '${dto.no }', '${searchGubun }', '${searchData }');">삭제</button>
							</div>	
						</td>
					</tr>					
				</table>
			
			</td>
		</tr>
		<c:set var="jj" value="${jj = jj - 1 }" />
	</c:forEach>
	
</table>


<div style="border: 0px solid blue; width: 80%; margin-top: 10px;" align="right">
	|
	<a href="#" onClick="move('list', '${pageNumber }', '', '', '');">전체목록</a>
	|
	<a href="#" onClick="move('chuga', '${pageNumber }', '', '${searchGubun }', '${searchData }');">등록</a>
	|
</div>

<div style="border: 0px solid blue; width: 80%; margin-top: 10px;" align="center">
	<form name="searchForm">
	<select name="searchGubun">
		<option value="" <c:if test="${searchGubun == '' }">selected</c:if>>-- 선택 --</option>
		<option value="name" <c:if test="${searchGubun == 'name' }">selected</c:if>>이름</option>
		<option value="content" <c:if test="${searchGubun == 'content' }">selected</c:if>>내용</option>
		<option value="name_content" <c:if test="${searchGubun == 'name_content' }">selected</c:if>>이름+내용</option>	
	</select>
	&nbsp;
	<input type="text" name="searchData" value="${searchData }">
	&nbsp;
	<button type="button" id="btnSearch" onClick="search();">검색</button>
	</form>
</div>

<c:if test="${totalRecord > 0 }">
	<div style="border: 0px solid blue; width: 80%; margin-top: 30px;" align="center">
	
		<a href="#" onClick="move('list', '1', '', '${searchGubun }', '${searchData }');">[첫페이지]</a>
		&nbsp;&nbsp;
		
		<c:if test="${startPage > blockSize }">
			<a href="#" onClick="move('list', '${startPage - blockSize }', '', '${searchGubun }', '${searchData }');">[이전10개]</a>
		</c:if>
		<c:if test="${startPage <= blockSize }">
			[이전10개]
		</c:if>
		&nbsp;&nbsp;
		
		<c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
			<c:if test="${i == pageNumber }">
				<b>[${i }]</b>
			</c:if>
			<c:if test="${i != pageNumber }">
				<a href="#" onClick="move('list', '${i }', '', '${searchGubun }', '${searchData }');">${i }</a>
			</c:if>
			&nbsp;
		</c:forEach>
		
		&nbsp;&nbsp;
		<c:if test="${lastPage < totalPage }">
			<a href="#" onClick="move('list', '${startPage + blockSize }', '', '${searchGubun }', '${searchData }');">[다음10개]</a>
		</c:if>
		<c:if test="${lastPage >= totalPage }">
			[다음10개]
		</c:if>
		
		&nbsp;
		<a href="#" onClick="move('list', '${totalPage }', '', '${searchGubun }', '${searchData }');">[끝페이지]</a>
	
	</div>
</c:if>

<script>
function search() {
	document.searchForm.action = "${path}/guestBook/list";
	document.searchForm.method = "post";
	document.searchForm.submit();
}

function move(value1, value2, value3, value4, value5) {
	location.href = '${path}/guestBook/' + value1 + '?pageNumber=' + value2 + '&no=' + value3 + '&searchGubun=' + value4 + '&searchData=' + value5;
}
</script>