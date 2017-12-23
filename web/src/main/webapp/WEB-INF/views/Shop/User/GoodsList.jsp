<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<%--메뉴 구성을 위한 대, 중, 소카테고릴 보여주고 --%>
	<table width="1000" border="1" align="center">
		<tr>
			<td>대카테고리</td>
			<td>
			<c:forEach var="data" items="${LCODE}">
				<a href="../Shop/GoodsList.sun?code=${data.code}">${data.name}</a>&nbsp;&nbsp;&nbsp;
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td>중카테고리</td>
			<td>
			<c:forEach var="data" items="${MCODE}">
				<a href="../Shop/GoodsList.sun?code=${data.code}">${data.name}</a>&nbsp;&nbsp;&nbsp;
			</c:forEach>
			</td>
		</tr>
		<tr>
			<td>소카테고리</td>
			<td>
			<c:forEach var="data" items="${SCODE}">
				<a href="../Shop/GoodsList.sun?code=${data.code}">${data.name}</a>&nbsp;&nbsp;&nbsp;
			</c:forEach>
			</td>
		</tr>
	</table>
	<%--	상품 목록 보여주고 --%>
	<table border="1" align="center" width="1000">
		<tr>
			<th>이미지</th>
			<th>상품명</th>
			<th>카테고리</th>
			<th>제조사</th>
			<th>가격</th>
		</tr>	
		<c:forEach var="data" items="${GLIST}">
			<tr>
				<td><img src="../goodsImg/${data.saveName}"></td>
				<td>${data.name}</td>
				<td>${data.sname}</td>
				<td>${data.maker}</td>
				<td>${data.price}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
			