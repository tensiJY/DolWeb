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
	function Search() {
		//	무결성 검사 하고
		
		$("#sfrm").submit();
	}
	$(document).ready(function(){
		$("#wBtn").click(function(){
			$(location).attr("href", "../FileBoard/WriteForm.sun");
		});
		
		$("#sBtn").click(Search);
	});
</script>
<body>
<!-- 	검색창 만들고 -->
	<form method="POST" id="sfrm" action="../FileBoard/BoardSearch.sun">
		<input type="hidden" name="nowPage" value="1">
		<table width="1000" border="1" align="center">
			<tr>
				<td align="center">
					<select id="target" name="target">
						<option value="writer">글쓴이</option>
						<option value="title">제목</option>
						<option value="body">본문</option>
						<option value="both">제목 + 본문</option>
					</select>
					<input type="text" id="word" name="word">
					<input type="button" id="sBtn" value="검색하기">					
				</td>
			</tr>
		</table>
	</form>
<!-- 	목록보기 보여주고 -->
	<table width="1000" border="1" align="center">
		<tr>
			<td colspan="6">${PINFO.nowPage} / ${PINFO.totalPage}(${PINFO.totalCount})</td>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>첨부파일</th>
		</tr>
		<c:forEach var="data" items="${LIST}" varStatus="status">
		<tr>
			<%--
				데이터베이스의 번호는 무시하고
				출력 순서대로 번호를 지정하는 방법
			--%>
			<td>${(PINFO.nowPage - 1) * PINFO.listCount + status.count}</td>
			<td>
				<a href="../FileBoard/BoardView.sun?oriNo=${data.no}&nowPage=${PINFO.nowPage}&kind=list">${data.title}</a>
			</td>
			<td>${data.writer}</td>
			<td>${data.wday}</td>
			<td>${data.hit}</td>
			<td>${data.cnt}</td>
<%--			
			<c:if test="${data.cnt ne 0}">
				첩부파일이 존재한다는 의미이고 
			</c:if>
			<c:if test="${data.cnt eq 0}">
				첩부파일이 존재하지 않는다는 의미이다. 
			</c:if>
 --%>			
		</tr>
		</c:forEach>		
	</table>
<!-- 	숙제
		페이지 이동 기능 만들어서 테스트 해 보시오.
-->

<!-- 	기타 기능 만들고 -->
	<table width="1000" border="1" align="center">
		<tr>
			<td align="center">
				<input type="button" id="wBtn" value="글쓰기">
			</td>
		</tr>
	</table>
</body>
</html>