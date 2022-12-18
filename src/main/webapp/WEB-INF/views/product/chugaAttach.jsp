<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>상품등록</h2>

<form name="chugaForm">
	<table border="1" width="80%">
		<tr>
			<td>상품이름</td>
			<td><input type="text" name="productName"></td>
		</tr>
		<tr>
			<td>제조사</td>
			<td><input type="text" name="vendor"></td>
		</tr>
		<tr>
			<td>상품가격</td>
			<td><input type="text" name="productPrice"></td>
		</tr>
		<tr>
			<td>상품설명</td>
			<td><textarea name="productDescription" style="width:200px; height: 100px"></textarea></td>
		</tr>
		<tr>
			<td>상품이미지</td>
			<td>
				<input type="file" name="file"><br>
				<input type="file" name="file"><br>
				<input type="file" name="file"><br>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" onclick="chuga();">등록하기</button>
				&nbsp;&nbsp;
				<a href="${path }/product/list">[목록으로]</a>
			</td>
		</tr>
	</table>
</form>

<script>
	function chuga(){
		if(confirm('등록할까요?')){
			document.chugaForm.enctype= "multipart/form-data";
			document.chugaForm.action = "${path }/product/chugaAttachProc";
			document.chugaForm.method = "post";
			document.chugaForm.submit();
		}
	}
</script>
