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
			$(location).attr("href", "../FileBoard/BoardList.sun?nowPage=${nowPage}");
		});		
		$("#sBtn").click(function(){
			$(location).attr("href", "../FileBoard/BoardSearch.sun?nowPage=${nowPage}");
		});
	});
</script>
<body>
	<!-- 	상세보기 보여주고 -->
	<table border="1" width="800" align="center">
		<tr>
			<td>글번호</td>
			<td>${VIEW.no}</td>
			<td>조회수</td>
			<td>${VIEW.hit}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${VIEW.writer}</td>
			<td>작성일</td>
			<td>${VIEW.wday}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td colspan="3">${VIEW.title}</td>
		</tr>
		<tr>
			<td>본문</td>
			<td colspan="3">${VIEW.body}</td>
		</tr>
	</table>
	<!-- 	첨부파일 보여주고 -->
	<table border="1" width="800" align="center">
		<tr>
			<td align="center">첨부파일</td>
		</tr>
		<c:forEach var="files" items="${FILE}">
			<tr>
				<td>
					<a href="../FileBoard/FileDownload.sun?oriNo=${files.no}">${files.oriName}</a> (${files.len}) 다운로드수 : ${files.download}
				</td>
			</tr>
		</c:forEach>
	</table>	
	<!-- 	이전글 다음글 보여주고 -->
	<table width="800" border="1" align="center">
		<tr>
			<td>이전글</td>
			<td>
				<c:if test="${PRENEXT.PRENO ne 0}">
					<a href="../FileBoard/BoardView.sun?oriNo=${PRENEXT.PRENO}&nowPage=${nowPage}&kind=${kind}">${PRENEXT.PRETITLE}</a>
				</c:if>
				<c:if test="${PRENEXT.PRENO eq 0}">
					${PRENEXT.PRETITLE}
				</c:if>
			</td>
		</tr>
		<tr>
			<td>다음글</td>
			<td>
				<c:if test="${PRENEXT.NEXTNO ne 0}">
					<a href="../FileBoard/BoardView.sun?oriNo=${PRENEXT.NEXTNO}&nowPage=${nowPage}&kind=${kind}">${PRENEXT.NEXTTITLE}</a>
				</c:if>
				<c:if test="${PRENEXT.NEXTNO eq 0}">
					${PRENEXT.NEXTTITLE}
				</c:if>
			</td>
		</tr>
	</table>	
	<!-- 	기타 기능 만들고 -->
	<table width="800" border="1" align="center">
		<tr>
			<td>
				<c:if test="${kind eq 'list'}">
					<input type="button" id="lBtn" value="목록보기">
				</c:if>
				<c:if test="${kind eq 'search'}">
					<input type="button" id="sBtn" value="목록보기">
				</c:if>
			</td>
		</tr>
	</table>
</body>
</html>





