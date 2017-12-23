<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../se2/photo_uploader/popup/attach_photo.js"></script>
<body>
<!-- 	스마트 에디터에 업로드된 이미지를 알려주는 함수는
		setPhotoToEditor()
		이 함수는 attach_photo.js 에 있는 함수이다.
		
		이 함수에는 업로드된 이미지의 정보를 path, name으로 JSON의 Map 형태로 
		알려준다.
-->
<script>
	$(function(){
		var	data = [{
			sFileName : "${NAME}",				//	title 속성에 들어가고
			sFileURL : "../seupload/${NAME}",	//	src 속성에 들어간다.
			bNewLine : true		// 이미지 그린후 줄 바꿈 처리 여부 결정
		}];
		//	저장은 서버 관리에 하고 
		//	웹에서는 우리눈에 보이는 경로인 <img src="../seupload/"로 처리하므로...
		setPhotoToEditor(data);
		self.close();
	});
</script>
</body>
</html>

