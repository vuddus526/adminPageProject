<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<table border="0" width="90%" align="center">
	<tr>
		<td colspan="10" align="right" style="padding: 10px 0px 10px 0px;">
			<c:choose>
				<c:when test="${sessionScope.sessionNo == null || sessionScope.sessionNo == 0 }">
					<a href="${path }/memeber/join">회원가입</a>
					&nbsp;&nbsp;&nbsp;
					<a href="${path }/member/login">로그인</a>
				</c:when>
				<c:otherwise>
					${sessionScope.sessionName }님 환영합니다.
					&nbsp;&nbsp;
					<a href="${path }/member/logout">로그아웃</a>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td align="center" style="padding: 10px 10px;" id="noLogin">
			<a href="${path }/">Home</a>
		</td>
		<td align="center" style="padding: 10px 10px;" id="member">
			<a href="${path }/member/list">회원관리</a>
		</td>
		<td align="center" style="padding: 10px 10px;" id="memo">
			<a href="${path }/memo/list">메모장</a>
		</td>
		<td align="center" style="padding: 10px 10px;" id="guestBook">
			<a href="${path }/guestBook/list">방명록</a>
		</td>
		<td align="center" style="padding: 10px 10px;" id="board">
			<a href="${path }/board/list">게시판</a>
		</td>
		
		<%-- <td align="center" style="padding: 10px 10px;" id="board">
			<a href="${path }/board/list?tbl=freeboard">자유게시판</a>
		</td>
		<td align="center" style="padding: 10px 10px;" id="board">
			<a href="${path }/board/list?tbl=onebyone">1:1게시판</a>
		</td> --%>
		
		<td align="center" style="padding: 10px 10px;" id="product">
			<a href="${path }/product/list">상품관리</a>
		</td>
		<td align="center" style="padding: 10px 10px;" id="shop">
			<a href="${path }/shop/list">쇼핑몰</a>
		</td>
		<td align="center" style="padding: 10px 10px;" id="chart">
			<a href="${path }/chart/list">차트</a>
		</td>
	</tr>
</table>

<script>
	$("#${folderName }").css("background-color", "silver");
</script>