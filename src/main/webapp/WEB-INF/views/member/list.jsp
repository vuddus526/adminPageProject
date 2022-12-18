<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>회원목록</h2>
    
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
		<td>아이디</td>
		<td>이름</td>
		<td>연락처</td>
		<td>주민번호</td>
		<td>등급</td>
		<td>등록일</td>
	</tr>
	<c:forEach var="list" items="${list }">
		<tr>
			<td>${jj }</td>
			<td><a href="#" onclick="move('view', '${pageNumber }', '${list.no }', '${searchGubun }', '${searchData }');">${list.id }</a></td>
			<td>${list.name }</td>
			<td>${list.phone }</td>
			<td>${list.jumin }</td>
			<td>
				<c:if test="${list.grade == '최우수회원'}">
					<c:set var="textColor" value="red" />
				</c:if>
				<c:if test="${list.grade == '우수회원'}">
					<c:set var="textColor" value="blue" />
				</c:if>
				<c:if test="${list.grade == '일반회원'}">
					<c:set var="textColor" value="black" />
				</c:if>
				
				<font style="color: ${textColor }; font-wright: bold;">${list.grade }</font>
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
</div>

<div style="border: 0px solid blue; width: 80%; margin-top: 10px" align="center">
	<form name="searchForm">
		<select name="searchGubun">
			<%-- <c:choose>
				<c:when test="${searchGubun == 'id' }">
					<option value="">-- 선택 --</option>
					<option value="id" selected>아이디</option>
					<option value="name">이름</option>
					<option value="phone">전화번호</option>
					<option value="id_name_phone">아이디+이름+전화번호</option>
				</c:when>
				<c:when test="${searchGubun == 'name' }">
					<option value="">-- 선택 --</option>
					<option value="id">아이디</option>
					<option value="name" selected>이름</option>
					<option value="phone">전화번호</option>
					<option value="id_name_phone">아이디+이름+전화번호</option>
				</c:when>
				<c:when test="${searchGubun == 'phone' }">
					<option value="">-- 선택 --</option>
					<option value="id">아이디</option>
					<option value="name">이름</option>
					<option value="phone" selected>전화번호</option>
					<option value="id_name_phone">아이디+이름+전화번호</option>
				</c:when>
				<c:when test="${searchGubun == 'id_name_phone' }">
					<option value="">-- 선택 --</option>
					<option value="id">아이디</option>
					<option value="name">이름</option>
					<option value="phone">전화번호</option>
					<option value="id_name_phone" selected>아이디+이름+전화번호</option>
				</c:when>
				<c:otherwise>
					<option value="" selected>-- 선택 --</option>
					<option value="id">아이디</option>
					<option value="name">이름</option>
					<option value="phone">전화번호</option>
					<option value="id_name_phone">아이디+이름+전화번호</option>
				</c:otherwise>
			</c:choose> --%>
			
			<option value="" <c:if test ="${searchGubun == '' }">selected</c:if>>-- 선택 --</option>
			<option value="id" <c:if test ="${searchGubun == 'id' }">selected</c:if>>아이디</option>
			<option value="name" <c:if test ="${searchGubun == 'name' }">selected</c:if>>이름</option>
			<option value="phone" <c:if test ="${searchGubun == 'phone' }">selected</c:if>>전화번호</option>
			<option value="id_name_phone" <c:if test ="${searchGubun == 'id_name_phone' }">selected</c:if>>아이디+이름+전화번호</option>
					
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
		document.searchForm.action = "${path }/member/list";
		document.searchForm.method = "post";
		document.searchForm.submit();
	}
	
	function move(value1, value2, value3, value4, value5){
		location.href = '${path }/member/' + value1 + '?pageNumber=' + value2 + '&no=' + value3 + '&searchGubun=' + value4 + '&searchData=' + value5;
	}
</script>
