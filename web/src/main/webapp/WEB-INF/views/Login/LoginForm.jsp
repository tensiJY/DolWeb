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
			//	����
			//		���Ἲ �˻��ϰ�
			
			//		������ ������ ������.
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
			<td>���̵�</td>
			<td><input type="text" id="id" name="id"></td>
		</tr>
		<tr>
			<td>��й�ȣ</td>
			<td><input type="password" id="pw" name="pw"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="checkbox" name="auto" id="auto" value="auto">�ڵ� �α���
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="lBtn" value="�α���">
			</td>		
		</tr>
	</table>
	</form>
	</c:if>
	<c:if test="${not empty sessionScope.UID}">
		<table width="500" border="1" align="center">
			<tr>
				<td align="center">
					${sessionScope.UID} �� ȯ���մϴ�.
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="button" id="oBtn" value="�α׾ƿ�">
					<input type="button" id="hBtn" value="Ȩ����">
				</td>
			</tr>
		</table>
	</c:if>
</body>
</html>










