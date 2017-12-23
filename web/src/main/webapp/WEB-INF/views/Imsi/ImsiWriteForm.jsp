<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../se2/js/HuskyEZCreator.js" charset="UTF-8"></script>
<script>
	var	oEditors = [];		//	에디터에 필요한 내용을 기억할 배열 변수
	$(function(){
		nhn.husky.EZCreator.createInIFrame({
			//	이 변수는 앞에서 선언된 변수이어야 한다.
	          oAppRef: oEditors,
	        //	body는 준비된 <textarea>의 id 값이 되어야 한다.
	          elPlaceHolder: "body",
	        //	입힐 스킨 내용을 지정한다.	
	          sSkinURI: "../se2/SmartEditor2Skin.html",
			//	스킨에 표시할 내용을 지정한다.
	          htParams : {
					bUseToolbar :true,
	              	bUseVerticalResizer : true,
            		bUseModeChanger : true
	          }
		});
	});
	
	$(document).ready(function(){
		$("#btn").click(function(){
			//	스킨을 입혀서 스마트 에디터를 구현한 것이다.
			//	문제
			//		사용자가 입력한 내용은 스킨에 입력한 것일뿐 textarea에는 
			//		입력이 되지 않는다.
			//	처리방법
			//		스킨에 있는 내용을 <textarea>에 반영한 후 서브밋 처리를 해야 한다.
			//	★★★
			oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
			
			//	이후에 무결성 검사를 하던지, 서브밋 시키도록 한다.
			var	body = $("#body").val();
			alert(body);
			
			//	submit 시킨다.
		});
	});
</script>
<body>
	<!-- 	가장 중요한것
			스마트 에디터를 적용하기 위한 곳에서는 엔코딩 타입이 반드시
			UTF-8이 되어야 한다.
			왜냐하면 네이버에서 제공하는 스마트 에디터 라이브러리가 모두 UTF-8로 
			세팅되어 있기 때문이다.
			
			그이외의 폼은 일반적인 폼을 만들듯이 만들어 나가면 된다.
	-->
	<form method="POST" id="frm" action="">
		<table width="800" border="1" align="center">
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" id="title"></td>
			</tr>
			<!-- 	스마트 에디터를 입혀서 내용을 입력받을 폼이 필요하면...
					그 부분은 <textarea>로 일단 만든 후 자바스크립트를 이용해서
					<textarea> 부분에 스마트 에디터 스킨을 입혀주면 된다.
			-->
			<tr>
				<td>본문</td>
				<td><textarea id="body" name="body" rows="10" cols="90"></textarea></td>
				<!-- 	주의
						cols에 의해서 스마트 에디터의 크기가 결정되므로 cols 부분을 이용해서
						스마트 에디터의 크기를 조절하면 된다.
				-->
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" id="btn" value="글쓰기">
				</td>
			</tr>
		</table>
	</form>
	
	
</body>
</html>


				
				
				
				