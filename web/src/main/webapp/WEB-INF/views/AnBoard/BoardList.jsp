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
		$("#wBtn").click(function(){
			$(location).attr("href", "../AnBoard/WriteForm.sun");
		});
		
		$("#sBtn").click(function(){
			//	
			$("#sfrm").submit();
		});
	});
</script>
<body>
<%--	검색 상자를 만들고 --%>
	<form method="POST" id="sfrm" action="../AnBoard/BoardSearch.sun">
	<table width="1000" border="1" align="center">
		<tr>
			<td align="center">
				<select id="kind" name="kind">
					<option value="title">제목</option>
					<option value="body">본문</option>
					<option value="writer">글쓴이</option>
					<option value="tags">태그</option>
				</select>
				<input type="text" id="word" name="word">
				<input type="button" id="sBtn" value="검색하기">
			</td>
		</tr>
	</table>
	</form>
<%--	목록을 보여주고 --%>
	<table width="1000" border="1" align="center">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="data" items="${LIST}">
<%--
		목록 구하기 질의 명령을 실행하면 한줄의 데이터가 VO 클래스 묶여서
		이것이 다시 ArrayList로 묶여서 제공된다.
--%>		
			<tr>
				<td>${data.no}</td>
				<td>
					<a href="../AnBoard/HitProc.sun?nowPage=${PINFO.nowPage}&oriNo=${data.no}">${data.title}</a>
				</td>
				<td>${data.writer}</td>
				<td>${data.wday}</td>
				<td>${data.hit}</td>
			</tr>
		</c:forEach>
	</table>
<%--	페이지 이동 기능 --%>
	<table width="1000" border="1" align="center">
		<tr>
			<td align="center">
				<%--	[이전] --%>
				<c:if test="${PINFO.startPage eq 1}">
					[이전]
				</c:if>
				<c:if test="${PINFO.startPage ne 1}">
					<a href="">[이전]</a>
				</c:if>
				<%--	[1][2][3] --%>
				<c:forEach var="page" begin="${PINFO.startPage}" end="${PINFO.endPage}">
					<a href="">[${page}]</a>
				</c:forEach>
				<%--	[다음] --%>
				<c:if test="${PINFO.endPage eq PINFO.totalPage}">
					[다음]
				</c:if>
				<c:if test="${PINFO.endPage ne PINFO.totalPage}">
					<a href="">[다음]</a>
				</c:if>
			</td>
		</tr>
	</table>
<%--	기타 기능을 만든다. --%>
	<table width="1000" border="1" align="center">
		<tr>
			<td align="right">
				<input type="button" id="wBtn" value="글쓰기">
			</td>
		</tr>
	</table>
</body>
</html>