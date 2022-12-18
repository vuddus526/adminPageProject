<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>쇼핑몰목록</h2>
    
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
	<c:set var="k" value="0"/>
	<c:forEach var="i" begin="1" end="${row_counter }" step="1">
		<tr>
			<c:forEach var="j" begin="1" end="${cell_counter }" step="1">
			<c:set var="dto" value="${list[k] }"/>
				<td style="padding:20px 0px;">
					<table border="0" width="80%" align="center">
						<tr>
							<td>
								<c:if test="${k < list.size() }">
									<img src="${path }/attach${path}/product/${fn:split(dto.attachInfo, ',')[1] }" style="width:100px; height:100px;">
								</c:if>
								<c:if test="${k >= list.size() }">
									&nbsp;
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								<c:if test="${k < list.size() }">
									<a href="#" onclick="move('view', '${pageNumber }', '${dto.productNo }', '${searchGubun }', '${searchData }');">${dto.productName }</a>
								</c:if>
								<c:if test="${k >= list.size() }">
									&nbsp;
								</c:if>
							</td>
						</tr>
						<tr>
							<td>
								<c:if test="${k < list.size() }">
									${dto.productPrice }
								</c:if>
								<c:if test="${k >= list.size() }">
									&nbsp;
								</c:if>
							</td>
						</tr>
					</table>
				</td>
				<c:set var="k" value="${k = k + 1 }"/>
			</c:forEach>
		</tr>	
	</c:forEach>

</table>


<br>

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
		document.searchForm.action = "${path }/shop/list";
		document.searchForm.method = "post";
		document.searchForm.submit();
	}
	
	function move(value1, value2, value3, value4, value5){
		location.href = '${path }/shop/' + value1 + '?pageNumber=' + value2 + '&productNo=' + value3 + '&searchGubun=' + value4 + '&searchData=' + value5;
	}
</script>
