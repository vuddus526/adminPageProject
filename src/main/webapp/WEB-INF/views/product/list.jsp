<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>상품목록</h2>
    
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
   
<table border="1" width="80%">
	<tr>
		<td>No.</td>
		<td>상품이름</td>
		<td>제조사</td>
		<td>상품가격</td>
		<td>상품설명</td>
		<td>상품이미지</td>
		<td>등록일</td>
	</tr>
	<c:forEach var="list" items="${list }">
		<tr>
			<td>${jj }</td>
			<td><a href="#" onclick="move('view', '${pageNumber }', '${list.productNo }', '${searchGubun }', '${searchData }');">${list.productName }</a></td>
			<td>${list.vendor }</td>
			<td>${list.productPrice }</td>
			<td>${list.productDescription }</td>
			
			<td>
				<c:set var="tempArray1" value="${fn:split(list.attachInfo,'|')}"></c:set>
				<c:forEach var="i" begin="0" end="${fn:length(tempArray1)-1 }" step="1">
					<c:choose>
						<c:when test="${tempArray1[i] == '-'}">
							&nbsp;
						</c:when>
						<c:otherwise>
							<c:set var="tempArray2" value="${fn:split(tempArray1[i],',')}"></c:set>
							<c:set var="temp1" value="${tempArray2[0]}"></c:set>
							<c:set var="temp2" value="${tempArray2[1]}"></c:set>
							<img src="${path }/attach${path }/product/${temp2 }" alt="${list.productName}" title="${list.productName}" style="width:50px; height:50px;">
						</c:otherwise>
					</c:choose>	
				</c:forEach>
			</td>
			
			<td>${list.regiDate }</td>
		</tr>
		<c:set var="jj" value="${jj = jj - 1 }"/>
	</c:forEach>
</table>

<div style="border: 0px solid blue; width: 80%; margin-top: 10px" align="right">
|
<a href="#" onclick="move('list', '', '', '', '');">전체목록</a>
|
<a href="#" onclick="move('chuga', '${pageNumber }', '', '${searchGubun }', '${searchData }');">등록</a>
|
<a href="#" onclick="move('chugaAttach', '${pageNumber }', '', '${searchGubun }', '${searchData }');">등록(attach)</a>
|
</div>

<div style="border: 0px solid blue; width: 80%; margin-top: 10px" align="center">
	<form name="searchForm">
		<select name="searchGubun">
			
			<option value="" <c:if test ="${searchGubun == '' }">selected</c:if>>-- 선택 --</option>
			<option value="productName" <c:if test ="${searchGubun == 'productName' }">selected</c:if>>상품이름</option>
			<option value="productPrice" <c:if test ="${searchGubun == 'productPrice' }">selected</c:if>>상품가격</option>
			<option value="productDescription" <c:if test ="${searchGubun == 'productDescription' }">selected</c:if>>상품설명</option>
			<option value="productName_productPrice_productDescription" <c:if test ="${searchGubun == 'productName_productPrice_productDescription' }">selected</c:if>>상품이름+상품가격+상품설명</option>
					
		</select>
		&nbsp;
		<input type="text" name="searchData" value="${searchData }">
		&nbsp;
		<button type="button" id="btnSearch" onclick="search();">검색</button>
	</form>
</div>

<c:if test="${totalRecord > 0 }">
	<div style="border: 0px solid blue; width: 80%; margin-top: 30px" align="center">
	
		<a href="#" onclick="move('list', '1', '', '${searchGubun }', '${searchData }');">[첫페이지]</a>
		&nbsp;&nbsp;
		
		<c:if test="${startPage > blockSize }">
			<a href="#" onclick="move('list', '${startPage - blockSize }', '', '${searchGubun }', '${searchData }');">[이전10개]</a>
		</c:if>
		<c:if test="${startPage <= blockSize }">
			[이전10개]
		</c:if>
		&nbsp;
		
		<c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
			<c:if test="${i == pageNumber }">
				<b>[${i }]</b>
			</c:if>
			<c:if test="${i != pageNumber }">
				<a href="#" onclick="move('list', '${i }', '', '${searchGubun }', '${searchData }');">${i }</a>
			</c:if>
			&nbsp;
		</c:forEach>
		
		<c:if test="${lastPage < totalPage }">
			<a href="#" onclick="move('list', '${startPage + blockSize }', '', '${searchGubun }', '${searchData }');">[다음10개]</a>
		</c:if>
		<c:if test="${lastPage >= totalPage }">
			[다음10개]
		</c:if>
		
		&nbsp;&nbsp;
		<a href="#" onclick="move('list', '${totalPage }', '', '${searchGubun }', '${searchData }');">[끝페이지]</a>
	
	</div>
</c:if>

<script>
	function search(){
		document.searchForm.action = "${path }/product/list";
		document.searchForm.method = "post";
		document.searchForm.submit();
	}
	
	function move(value1, value2, value3, value4, value5){
		location.href = '${path }/product/' + value1 + '?pageNumber=' + value2 + '&productNo=' + value3 + '&searchGubun=' + value4 + '&searchData=' + value5;
	}
</script>
