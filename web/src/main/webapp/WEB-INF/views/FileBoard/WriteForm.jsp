<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
//	추가된 파일 첨부 입력상자의 개수를 기억할 변수
	var	count = 1;
	$(document).ready(function(){
		$("#wBtn").click(function(){
			//	무결성 하고
			
			$("#wFrm").submit();
		});
		$("#aBtn").click(function(){
			//	할일
			count++;
			if(count > 5) {
				count = 5;
				return;
			}
			//		추가할 내용을 아래쪽 감춘 부분에서 알아낸다.
			var		div = $("#copy").find("tr");
			//		실제 추가할 내용은 div 안에 있는 tr 부분이므로
			//		target 앞쪽에 붙여넣기를 해준다.
			var copy = div.clone();
			$("#target").before(copy);
		});
	});
	
	//	JQuery는 문서가 완성된 후 처리하는 기법의 프로그램 방식이다.
	//	지금은 동적인 추가이기 때문에 JQuery 처리가 되지 않고 있다.
	//	이처럼 동적인 추가인 경우에는 JavaScript를 사용해야만 한다.
	function delete1(my) {
		//	이 함수를 호출할 때 이벤트가 일어난 단추를 알려주면
		var	tr = $(my).parents("tr");
		//	그 단추의 상위 tr을 찾아서
		tr.remove();
		//	그 tr를 지운다.
		count--;
	}
</script>
<body>
	<!-- 	글쓰기 폼을 만든다. 
			폼의 내용이 파일 첨부가 있을 경우에는 parameter 타입으로 전송하면 안되고
			반드시 스트림 방식으로 처리해야 한다.
			
			방법		enctype="multipart/form-data"		선언을 한다.
	-->
	<form method="POST" id="wFrm" action="../FileBoard/WriteProc.sun" enctype="multipart/form-data">
	<table width="800" border="1" align="center">
		<tr>
			<td>글쓴이</td>
			<td>
				<input type="text" name="writer" id="writer" value="${sessionScope.UID}" readonly>
<!-- 	팁	 
			입력상자를 사용하지 못하게 하는 방법
			1.	readonly
				==>		이렇게 하면 이 안에 데이터가 서버에 전달된다.
			2.	disabled="disabled"
				==>		이렇게 하면 이 안에 데이터는 서버에 전달되지 않는다.
-->
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" id="title"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="body" id="body"></textarea></td>
		</tr>
		<tr>
			<td>첨부파일 <input type="button" id="aBtn" value="추가"></td>
			<td><input type="file" name="files" id="files"></td>
		</tr>
		<tr id="target">
			<td>비밀번호</td>
			<td><input type="password" name="pw" id="pw"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="wBtn" value="글쓰기">
			</td>
		</tr>	
	</table>
	</form>
	<!-- 	이 부분은 display를 none으로 해 놓았으므로 화면 보이지 않는다. -->
	<div style="display:none" id="copy">
		<table>
		<tr>
			<td>첨부파일 <input type="button"value="삭제" onClick="delete1(this);"></td>
			<td><input type="file" name="files" id="files"></td>
		</tr>
		</table>
	</div>	
</body>
</html>






