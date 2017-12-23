<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<script>
	function sendModify() {
		var	frm = document.getElementById("mFrm");
		frm.submit();
	}
</script>
<body>
	<form method="POST" id="mFrm" action="../AnBoard/ModifyProc.sun">
		<input type="hidden" name="oriNo" value="${VIEW.no}">
		<input type="hidden" name="nowPage" value="${nowPage}">
		<table width="800" border="1" align="center">
			<tr>
				<td>글쓴이</td>
				<td><input type="text" value="${sessionScope.UID}" readyOnly>
			</tr>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" id="title" name="title" value="${VIEW.title}">
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea id="body" name="body">${VIEW.body}</textarea>
				</td>
			</tr>
			<tr>
				<td>태그</td>
				<td>
					<input type="text" id="tags" name="tags" value="${VIEW.tags}"><br>
					검색 태그는 공백이나 ,를 이용해서 입력해 주세요
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" id="password" name="password"><br>
					수정을 위해서 비밀번호를 입력해 주세요
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="button" id="mBtn" value="수정하기" onClick="sendModify()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>


