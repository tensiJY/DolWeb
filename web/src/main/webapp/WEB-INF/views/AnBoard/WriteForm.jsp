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
			//	무결성 검사 하시고.......
			
			$("#wFrm").submit();
		});
	});
</script>
<body>
	<%--	글쓰기 폼을 이쁘게 만든다. --%>
	<form method="POST" id="wFrm" action="../AnBoard/WriteProc.sun">
	<table width="700" border="1" align="center">
		<tr>
			<td>글쓴이</td>
			<td>
				<input type="text" id="writer" name="writer" value="${sessionScope.UID}" readonly>
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>
				<input type="text" id="title" name="title">
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea id="body" name="body"></textarea>
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>
				<input type="password" id="password" name="password">
			</td>
		</tr>
		<tr>
			<td>태그</td>
			<td>
				<input type="text" id="tags" name="tags"><br>
				검색 태그는 공백이나 ,를 이용해서 입력해 주세요
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="wBtn" value="글쓰기">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>


