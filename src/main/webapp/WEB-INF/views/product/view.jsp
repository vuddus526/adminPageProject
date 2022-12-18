<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>   

<h2>상품상세보기</h2>

<table border="1" width="80%">
	<tr>
		<td>No.</td>
		<td>${dto.productNo }</td>
	</tr>
	<tr>
		<td>상품이름</td>
		<td>${dto.productName }</td>
	</tr>
	<tr>
		<td>제조사</td>
		<td>${dto.vendor }</td>
	</tr>
	<tr>
		<td>상품가격</td>
		<td>${dto.productPrice }</td>
	</tr>
	<tr>
		<td>상품설명</td>
		<td><textarea name="productDescription" style="width:200px; height: 100px" readonly>${dto.productDescription }</textarea></td>
	</tr>
	<tr>
		<td>상품이미지</td>
		<td>
			<c:set var="tempArray1" value="${fn:split(dto.attachInfo,'|')}"></c:set>
			<c:forEach var="i" begin="0" end="${fn:length(tempArray1)-1 }" step="1">
				<c:choose>
					<c:when test="${tempArray1[i] == '-'}">
						&nbsp;
					</c:when>
					<c:otherwise>
						<c:set var="tempArray2" value="${fn:split(tempArray1[i],',')}"></c:set>
						<c:set var="temp1" value="${tempArray2[0]}"></c:set>
						<c:set var="temp2" value="${tempArray2[1]}"></c:set>
						<img src="${path }/attach${path }/product/${temp2 }" alt="${dto.productName}" title="${dto.productName}" style="width:50px; height:50px;">
					</c:otherwise>
				</c:choose>	
			</c:forEach>
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
		<td width="100px">이전 상품</td>
		<td>
			<c:if test="${dto.preProductNo > 0 }">
				<a href="#" onclick="move('view', '${pageNumber }', '${dto.preProductNo }', '${searchGubun }', '${searchData }');">${dto.preProductName }</a>
			</c:if>
			<c:if test="${dto.preProductNo == 0 }">
				이전 상품이 없습니다.
			</c:if>
		</td>
	</tr>
	<tr>
		<td>다음 상품</td>
		<td>
			<c:if test="${dto.nxtProductNo > 0 }">
				<a href="#" onclick="move('view', '${pageNumber }', '${dto.nxtProductNo }', '${searchGubun }', '${searchData }');">${dto.nxtProductName }</a>
			</c:if>
			<c:if test="${dto.nxtProductNo == 0 }">
				다음 상품이 없습니다.
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
<a href="#" onclick="move('list', '', '', '', '');">목록</a>
|
<a href="#" onclick="move('chuga', '', '', '', '');">등록</a>
|
<a href="#" onclick="move('sujung', '', '${dto.productNo }', '', '');">수정</a>
|
<a href="#" onclick="move('sakje', '', '${dto.productNo }', '', '');">삭제</a>
|
</div>

<script>
	function move(value1, value2, value3, value4, value5){
		location.href = '${path }/product/' + value1 + '?pageNumber=' + value2 + '&productNo=' + value3 + '&searchGubun=' + value4 + '&searchData=' + value5;
	}
</script>