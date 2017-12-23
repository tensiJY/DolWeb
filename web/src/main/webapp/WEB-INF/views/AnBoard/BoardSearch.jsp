<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<body>
	<!-- 	검색 폼 만들고 -->
	<!-- 	검색 결과 출력하고 -->
	<table width="1000" border="1" align="center">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>작성일</th>
			<th>조회수</th>
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
	<!-- 	기타 기능 만들고 -->
</body>
</html>