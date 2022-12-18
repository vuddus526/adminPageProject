<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>   

<h2>쇼핑몰상세보기</h2>

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

<form name="jumunForm">

<select name="jumun_su">

<c:forEach var="i" begin="1" end="20" step="1">

	<option value="${i }">${i }개</option>

</c:forEach>
</select>&nbsp;


<button type="button" onclick="move('list','', '', '', '');">계속쇼핑</button> &nbsp;
<button type="button" onclick="move('list','', '', '', '');">바로구매</button> &nbsp;
<button type="button" onclick="moveCart('chugaProc', '${dto.productNo }');">장바구니담기</button> &nbsp;
<button type="button" onclick="moveCart('list', '');">장바구니바로가기</button> &nbsp;
</form>

<script>
	function move(value1, value2, value3, value4, value5){
		location.href = '${path }/shop/' + value1 + '?pageNumber=' + value2 + '&productNo=' + value3 + '&searchGubun=' + value4 + '&searchData=' + value5;
	}
	
	function moveCart(value1, value2){
		document.jumunForm.action = "${path }/cart/" + value1 + "?productNo=" + value2;		
		document.jumunForm.method = "post";
		document.jumunForm.submit();
	}
</script>