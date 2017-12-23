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
		$("#anBtn").click(function(){
			//	답글쓰기 요청을 한다.
			$(location).attr("href", "../AnBoard/AnWriteForm.sun?oriNo=${MAP.VIEW.no}&nowPage=${nowPage}");
		});
		
		$("#mBtn").click(function(){
			$(location).attr("href", "../AnBoard/ModifyForm.sun?oriNo=${MAP.VIEW.no}&nowPage=${nowPage}");
		});
		
		$("#dBtn").click(function() {
			//	먼저 비빌번호 입력 받고
			var	pw = prompt("비밀번호를 입력해 주세요");			
			//	이 비밀번호를 폼에 입력한 후
			$("#password").val(pw);
			//	폼을 서브밋 시킨다.
			$("#ifrm").submit();
		});
	});
</script>
<body>
<!-- 	상세보기 보여주고	

		넘어온 데이터는 MAP 이라는 키값으로 Map 형태로 넘어왔고
		이중에서 상세보기 내용은 VIEW라는 키값으로 묶여 있다.
		
		${MAP.VIEW}		<==		묶인 AnBoardVO	가 나온다.
		
		이 중에서 번호는 getNo() 함수로 제공되고 있으므로...
		
		번호를 출력하려면		${MAP.VIEW.no}
-->
	<table width="800" border="1" align="center">
		<tr>
			<td>글번호</td>
			<td>${MAP.VIEW.no}</td>
			<td>조회수</td>
			<td>${MAP.VIEW.hit}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${MAP.VIEW.writer}</td>
			<td>작성일</td>
			<td>${MAP.VIEW.wday}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td colspan="3">${MAP.VIEW.title}</td>
		</tr>
		<tr>
			<td>본문</td>
			<td colspan="3">${MAP.VIEW.body}</td>
		</tr>
		<tr>
			<td>태그</td>
			<td colspan="3">${MAP.VIEW.tags}</td>
		</tr>
	</table>
<!-- 	답글 목록 보여주고 -->
	<table width="800" border="1" align="center">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	<c:forEach var="data" items="${MAP.LIST}">
		<tr>
			<td>${data.no}</td>
<!-- 	답글형 게시판은 원글에 대한 답글이 계층형으로 표시되는 것이 좋다.
		(원글보다는 조금 들어간 모습으로 표현하는 것이 좋다.)
		
		그래서 우리는 Step 이라는 개념을 도입해 놓았고
		Step 이라는 개념은 몇번째 단계의 답글인지를 기억한 것이다.
		
		결론	Step을 이용하면 지금 출력할 데이터가 몇번째 단계인지를 표시할 수 
				있을 것이다.
 -->
			<td>
	<c:if test="${data.bstep ne 0}">	<!-- 	답글인 경우에는 들여쓰기가 되어야 하겠다. -->
		<c:forEach var="step" begin="1" end="${data.bstep}">
			&nbsp;&nbsp;&nbsp;
		</c:forEach>	
	</c:if>		
				<a href="../AnBoard/HitProc.sun?nowPage=${nowPage}&oriNo=${data.no}">${data.title}</a>
			</td>
			<td>${data.writer}</td>
			<td>${data.wday}</td>
			<td>${data.hit}</td>
		</tr>
	</c:forEach>		
	</table>
<!-- 	기타 기능 만들고 

		JSP시간에 공부했던 세션을 이용한 권한 설정을 해서 
		필요한 사람에게만 권한을 주도록 처리해야 한다.
-->
	<table width="800" border="1" align="center">
		<tr>
			<td align="center">
				<input type="button" id="anBtn" value="답글쓰기">
				<input type="button" id="mBtn" value="수정하기">
				<input type="button" id="dBtn" value="삭제하기">
			</td>
		</tr>
	</table>
	<!-- 	
			삭제를 하기위해서는 당연히 비밀번호를 받아서 알려주어야 한다.
			이처럼 중요한 정보를 GET 방식으로 보내면 보안에 취약하다.
			
			임시 폼을 만들어서 전달할 값을 모두 폼 안에 입력한 후
			그 폼을 submit 방식으로 전달하여 보안에 주의 해야 한다,
	-->
	<form method="POST" id="ifrm" action="../AnBoard/DeleteProc.sun">
		<input type="hidden" id="password" name="password">
		 <!-- 	이처럼 폼을 만들어서 보내면 폼에 있는 내용만 서버에 가므로
		 		같이 전달할 필요가 있는 데이터는 모두 폼 안에 입력해 놓아야 한다.
		 -->
		 <input type="hidden" id="oriNo" name="oriNo" value="${MAP.VIEW.no}">
		 <input type="hidden" id="nowPage" name="nowPage" value="${nowPage}">
	</form>	
</body>
</html>
