<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<body>
	<!-- 	�˻� �� ����� -->
	<!-- 	�˻� ��� ����ϰ� -->
	<table width="1000" border="1" align="center">
		<tr>
			<th>��ȣ</th>
			<th>����</th>
			<th>�۾���</th>
			<th>�ۼ���</th>
			<th>��ȸ��</th>
		</tr>
		<c:forEach var="data" items="${LIST}">
			<tr>
				<td>${data.no}</td>
				<td>${data.title}</td>
				<td>${data.writer}</td>
				<td>${data.wday}</td>
				<td>${data.hit}</td>
			</tr>
		</c:forEach>
	</table>
	<!-- 	��Ÿ ��� ����� -->
</body>
</html>