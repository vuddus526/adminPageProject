<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>

<h2>회원수정</h2>

<form name="sujungForm">
<input type="hidden" name="no" value="${dto.no }">
	<table border="1" align="center">
		<tr>
			<td>아이디</td>
			<td>${dto.id }</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${dto.name }</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone" value="${dto.phone }"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" value="${dto.email }"></td>
		</tr>
		<tr>
			<td>주민번호</td>
			<td>${dto.jumin }</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>
				<input type="text" name = "juso1" id="sample6_postcode" placeholder="우편번호" value="${dto.juso1 }">
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" name = "juso2" id="sample6_address" placeholder="주소" value="${dto.juso2 }"><br>
				<input type="text" name = "juso3" id="sample6_detailAddress" placeholder="상세주소" value="${dto.juso3 }">
				<input type="text" name = "juso4" id="sample6_extraAddress" placeholder="참고항목" value="${dto.juso4 }">
			</td>
		</tr>
		<tr>
			<td>등급</td>
			<td>
				<input type="radio" name="grade" value="최우수회원" <c:if test="${dto.grade == '최우수회원' }">checked</c:if>> 최우수회원 &nbsp;
				<input type="radio" name="grade" value="우수회원" <c:if test="${dto.grade == '우수회원' }">checked</c:if>> 우수회원 &nbsp;
				<input type="radio" name="grade" value="일반회원" <c:if test="${dto.grade == '일반회원' }">checked</c:if>> 일반회원 &nbsp;
			</td>	
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="sujung();">수정하기</button>
				&nbsp;&nbsp;
				<a href="${path }/member/list">[목록으로]</a>
			</td>
		</tr>
	</table>
</form>

<script>
	function sujung(){
		if(confirm('수정할까요?')){
			document.sujungForm.action = "${path }/member/sujungProc";
			document.sujungForm.method = "post";
			document.sujungForm.submit();
		}
	}
</script>
