<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#wBtn").click(function(){
			//	���Ἲ �˻� �Ͻð�.......
			
			$("#wFrm").submit();
		});
	});
</script>
<body>
	<%--	�۾��� ���� �̻ڰ� �����. --%>
	<form method="POST" id="wFrm" action="../AnBoard/WriteProc.sun">
	<table width="700" border="1" align="center">
		<tr>
			<td>�۾���</td>
			<td>
				<input type="text" id="writer" name="writer" value="${sessionScope.UID}" readonly>
			</td>
		</tr>
		<tr>
			<td>����</td>
			<td>
				<input type="text" id="title" name="title">
			</td>
		</tr>
		<tr>
			<td>����</td>
			<td>
				<textarea id="body" name="body"></textarea>
			</td>
		</tr>
		<tr>
			<td>����</td>
			<td>
				<input type="password" id="password" name="password">
			</td>
		</tr>
		<tr>
			<td>�±�</td>
			<td>
				<input type="text" id="tags" name="tags"><br>
				�˻� �±״� �����̳� ,�� �̿��ؼ� �Է��� �ּ���
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="wBtn" value="�۾���">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>


