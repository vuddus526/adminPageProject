<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<c:choose>
	<c:when test="${tbl == 'imsi' }">
		<c:set var="tblName" value="게시판" />
	</c:when>
	<c:when test="${tbl == 'freeboard' }">
		<c:set var="tblName" value="자유게시판" />
	</c:when>
	<c:when test="${tbl == 'onebyone' }">
		<c:set var="tblName" value="1:1게시판" />
	</c:when>
	<c:otherwise>
		<script>
			function aaa(){
				alert('허용되지 않은 게시판');
				location.href="${path}";
			}
			
			aaa();
		</script>
	</c:otherwise>
</c:choose>

<h2>${tblName }</h2>
   
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
    
<table border="1" width="80%" align="center">
	<tr>
		<td>순번</td>
		<td>작성자</td>
		<td width="20%">제목</td>
		<td>조회수</td>
		<td>회원번호</td>
		<td>공지글</td>
		<td>비밀글</td>
		<td>자식글 수</td>
		<td>등록일</td>
	</tr>
	<c:forEach var="dto" items="${list }">
		<tr>
			<td>
				<c:if test="${dto.noticeNo > 0 }">
					공지
				</c:if>
				<c:if test="${dto.noticeNo == 0 }">
					${jj }
				</c:if>
			</td>
			
			<td>${dto.writer }</td>
			
			<td>
				<c:forEach var="i" begin="1" end="${dto.stepNo - 1 }" step="1">
						&nbsp;
				</c:forEach>		
				<c:if test="${dto.stepNo > 1}">
					<c:set var="reVar" value="[Re]:" />
				</c:if>
				<c:if test="${dto.stepNo <= 1}">
					<c:set var="reVar" value="" />
				</c:if>
				
				<a href="#" onClick="move('view','${pageNumber }','${dto.no }', '${searchGubun }', '${searchData }');">${reVar }${dto.subject }</a>
			</td>
			
			<%-- <fmt:formatDate value="${dto.regiDate }" pattern="yyyy-MM-dd HH:mm:ss.SSSSSSSSS"/> --%>
			<td>${dto.hit }</td>
			<td>${dto.memberNo }</td>
			<td>
				<c:if test="${dto.noticeNo > 0 }">
					T
				</c:if>
				<c:if test="${dto.noticeNo == 0 }">
					F
				</c:if>
			</td>
			<td>${dto.secretGubun }</td>
			<td>${dto.childCounter }</td>
			<td>${dto.regiDate }</td>
		</tr>
		<c:set var="jj" value="${jj = jj - 1 }"/>
		<%-- <c:set var="imsiStep" value=""/>
		<c:set var="step" value=""/> --%>
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
			
			<option value="" <c:if test ="${searchGubun == '' }">selected</c:if>>-- 선택 --</option>
			<option value="writer" <c:if test ="${searchGubun == 'writer' }">selected</c:if>>작성자</option>
			<option value="subject" <c:if test ="${searchGubun == 'subject' }">selected</c:if>>제목</option>
			<option value="content" <c:if test ="${searchGubun == 'content' }">selected</c:if>>내용</option>
			<option value="writer_subject_content" <c:if test ="${searchGubun == 'writer_subject_content' }">selected</c:if>>작성자+제목+내용</option>
					
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
		document.searchForm.action = "${path }/board/list";
		document.searchForm.method = "post";
		document.searchForm.submit();
	}

	function move(value1, value2, value3, value4, value5){
		location.href = '${path }/board/' + value1 + '?pageNumber=' + value2 + '&no=' + value3 + '&searchGubun=' + value4 + '&searchData=' + value5 + '&tbl=${tbl }';
	}
</script>