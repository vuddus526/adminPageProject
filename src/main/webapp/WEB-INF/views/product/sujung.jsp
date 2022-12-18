<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>상품수정</h2>

<form name="sujungForm">
<input type="hidden" name="productNo" value="${dto.productNo }">
	<table border="1" align="center">
		<tr>
			<td>상품이름</td>
			<td><input type="text" name="productName" value="${dto.productName }"></td>
		</tr>
		<tr>
			<td>제조사</td>
			<td><input type="text" name="vendor" value="${dto.vendor }"></td>
		</tr>
		<tr>
			<td>상품가격</td>
			<td><input type="text" name="productPrice" value="${dto.productPrice }"></td>
		</tr>
		<tr>
			<td>상품설명</td>
			<td><textarea name="productDescription" style="width:200px; height: 100px">${dto.productDescription }</textarea></td>
		</tr>
		<tr>
			<td>기존상품이미지</td>
			<td>
				<c:set var="tempArray1" value="${fn:split(dto.attachInfo,'|')}"></c:set>
				<c:forEach var="i" begin="0" end="${fn:length(tempArray1)-1 }" step="1">
					<c:choose>
						<c:when test="${tempArray1[i] == '-'}">
							이미지x
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
			<td>수정할 상품이미지</td>
			<td>
				<input type="file" name="file"><br>
				<input type="file" name="file"><br>
				<input type="file" name="file"><br>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="sujung();">수정하기</button>
				&nbsp;&nbsp;
				<a href="${path }/product/list">[목록으로]</a>
			</td>
		</tr>
	</table>
</form>

<script>
	function sujung(){
		if(confirm('수정할까요?')){
			document.sujungForm.enctype= "multipart/form-data";
			document.sujungForm.action = "${path }/product/sujungProc";
			document.sujungForm.method = "post";
			document.sujungForm.submit();
		}
	}
</script>
