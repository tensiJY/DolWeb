<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#lBtn").click(function(){
			//	할일
			//		무결성 검사하고
			
			//		서버에 데이터 보낸다.
			$("#lfrm").submit();
		});
		$("#oBtn").click(function(){
			$(location).attr("href", "../Login/Logout.sun");
		});
	});
</script>
<body>

	<c:if test="${empty sessionScope.UID}">
	<form method="POST" id="lfrm" action="../Login/LoginProc.sun">
	<table width="500" align="center" board="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" id="id" name="id"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" id="pw" name="pw"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="checkbox" name="auto" id="auto" value="auto">자동 로그인
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="lBtn" value="로그인">
			</td>		
		</tr>
	</table>
	</form>
	</c:if>
	<c:if test="${not empty sessionScope.UID}">
		<table width="500" border="1" align="center">
			<tr>
				<td align="center">
					${sessionScope.UID} 님 환영합니다.
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="button" id="oBtn" value="로그아웃">
					<input type="button" id="hBtn" value="홈으로">
				</td>
			</tr>
		</table>
	</c:if>
</body>
</html>










