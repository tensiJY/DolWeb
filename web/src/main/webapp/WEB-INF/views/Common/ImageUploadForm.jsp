<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#btn").click(function(){
			//	무결성 검사하고
			
			$("#frm").submit();
		});
	});
</script>
<body>
	<form method="POST" id="frm" action="../Common/ImageUploadProc.sun" enctype="multipart/form-data">
		<table width="400" border="1" align="center">
			<tr>
				<td>첨부 이미지</td>
				<td><input type="file" id="files" name="files"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="첨부하기" id="btn">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>