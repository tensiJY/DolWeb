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
			//	무결성 검사하고
			//	서브밋 시킨다.
			$("#wfrm").attr("action", "../AnBoard/AnWriteProc.sun").submit();
		});
	});
</script>
<body>
<!-- 	댓글쓰기 폼 만들기
		주의
			폼을 만들때는 폼 안에 있는 데이터만 서버에 전달된다.
			이때 만약 서버에 전달이 되어야 하는데 화면에 출력하기가 애매한 경우에는
			hidden으로 반드시 처리해서 전달되도록 해야 한다.
-->
	<form method="POST" id="wfrm" action="">
	<input type="hidden" name="oriNo" value="${oriNo}">
	<input type="hidden" name="nowPage" value="${nowPage}">
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
			<td>비밀번호</td>
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




