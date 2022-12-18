<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>장바구니목록</h2>
    
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

<form name="listForm">
	<table border="1"  width="80%">
		<tr>
			<td>
				<input type="checkbox" name="checkAll" id="checkAll">
			</td>
			<td>순번</td>
			<td>회원번호</td>
			<td>상품코드</td>
			<td>상품이미지</td>
			<td>상품명</td>
			<td>가격</td>
			<td>수량</td>
			<td>총가격</td>
			<td>제조사</td>
			<td>등록일</td>
			<td>비고</td>
		</tr>
		<c:set var="hab" value="0"/>
		<c:forEach var="list" items="${list }">
			<tr>
				<td>
					<input type="checkbox" name="cartNo" value="${list.cartNo }">
				</td>
				<td>${jj }</td>
				<td>${list.memberNo }</td>
				<td>${list.productNo }</td>
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
				<td><a href="#" onclick="move('view', '${pageNumber }', '${list.productNo }', '${searchGubun }', '${searchData }');">${list.productName }</a></td>
				<td>${list.productPrice }</td>
				<td>${list.amount }</td>
				<td>${list.amount * list.productPrice }</td>
				<td>${list.vendor }</td>
				<td>${list.regiDate }</td>
				<td>
					<a href="#" onclick="clearOne('${list.cartNo }');">[삭제]</a>
				</td>
			</tr>
			<c:set var="jj" value="${jj = jj - 1 }"/>
			<c:set var="hab" value="${hab + (list.productPrice * list.amount) }"/>
		</c:forEach>
	
		
		<tr>
			<td colspan="12" align="right">
				합계 : ${hab }
				&nbsp;
				<input type="text" name="totalCounter" value="">
				<button type="button" onclick="cartClearSuntaek();">선택삭제</button>
				<button type="button" onclick="cartClearBatch();">선택삭제(Batch)</button>
				<button type="button" onclick="cartClearScript();">선택삭제(Script)</button>
				<button type="button" onclick="cartOrder();">선택상품주문</button>
			</td>
		</tr>
		
		<c:if test="${totalRecord > 0 }">
			 <tr>
				<td colspan="20" align="center">
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
				</td>
			</tr>
		</c:if>
	
	</table>
</form>

<form name="imsiForm">
<input type="hidden" name="cartNo">
</form>

<form name="scriptForm">
<input type="hidden" name="suntaekNo">
</form>

<script>
	$(document).ready(function(){
		$("#checkAll").click(function(){
			if($("#checkAll").prop("checked")) {
				$("input[name=cartNo]").prop("checked", true);
			}else {
				$("input[name=cartNo]").prop("checked", false);
			}
		});
	});
	
	function move(value1, value2, value3, value4, value5){
		location.href = '${path }/shop/' + value1 + '?pageNumber=' + value2 + '&productNo=' + value3 + '&searchGubun=' + value4 + '&searchData=' + value5;
	}
	
	function clearOne(value1){
		if(confirm('정말 삭제할까요?')){
			document.imsiForm.cartNo.value = value1;
			document.imsiForm.action = "${path }/cart/sakjeProc";
			document.imsiForm.method = "post";
			document.imsiForm.submit();
		}
	}
	
	function cartClearSuntaek() {
		document.listForm.action = "${path }/cart/SuntaekSakjeProc";
		document.listForm.method = "post";
		document.listForm.submit();
	}
	
	function cartClearBatch() {
		if(confirm('선택한 내용을 삭제할까요?')){
			document.listForm.action = "${path }/cart/cartSakjeBatchProc";
			document.listForm.method = "post";
			document.listForm.submit();
		}
	}
	
	function cartClearScript(){
		var imsiCartNo = "";
		$('input[name="cartNo"]:checked').each(function (index) {
			if( imsiCartNo != ""){
				imsiCartNo += ",";
			}
			imsiCartNo += $(this).val();
		});

		document.scriptForm.suntaekNo.value = imsiCartNo;
		
		document.scriptForm.action = "${path }/cart/cartSakjeScriptProc";
		document.scriptForm.method = "post";
		document.scriptForm.submit();
	}
</script>
