<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file = "../_include/inc_header.jsp" %>



<!-- <script>
function aaa(){
	location.href = '${path}/member/list';
}

aaa();
</script> -->

 	<c:if test ="${sessionNo == null || sessionNo == '' }">
 		<div style="border: 0px solid red; width: 60%; text-align: center; margin: 0 auto;">
 			<font style="font-size: 100px; font-weight: bold;">Here<br>We<br>Go !!!</font>
		 	<div style="padding: 20px 0px;">
		 		<form name="loginForm">
			 		아이디 : <input type="text" name="id" style="width: 80px;"> &nbsp;
			 		비밀번호 : <input type="password" name="passwd" style="width: 80px;"> &nbsp;
			 		<button type="button" onclick="login();">로그인</button>
		 		</form>
		 	</div>
	 	</div>
	</c:if>
	
	<c:if test="${sessionNo > 0 }">
		 <div style="border: 0px solid red; width: 60%; text-align: center; margin: 0 auto;">
	 		<font style="font-size: 70px; font-weight: bold;">${sessionName }님 환영합니다</font>
	 	</div>
	</c:if>

 
 <script>
 function login(){
		if(confirm('로그인 할까요?')){
			if(document.loginForm.id.value == "admin"){
				document.loginForm.action = "${path }/member/adminLoginProc";
				document.loginForm.method = "post";
				document.loginForm.submit();
			}else{
				document.loginForm.action = "${path }/member/loginProc";
				document.loginForm.method = "post";
				document.loginForm.submit();
			}
				
		}
	}
</script>