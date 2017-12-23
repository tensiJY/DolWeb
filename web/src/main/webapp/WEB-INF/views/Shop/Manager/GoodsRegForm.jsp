<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<!-- 	상품 상세 정보는 스마트 에디터를 이용해서 처리하고자 한다. -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../se2/js/HuskyEZCreator.js" charset="UTF-8"></script>
<script>
	var oEditors = [];
	$(function(){
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "detail",
		 	sSkinURI: "../se2/SmartEditor2Skin.html",
			htParams : {
				bUseToolbar :true,
				bUseVerticalResizer : true,
				bUseModeChanger : true
			}
		});
	});
	
	$(document).ready(function(){
		//	상품 등록 단추 이벤트 처리
		$("#rBtn").click(function(){
			//	스마트 에디터 스킨에 있는 내용을 textarea에 반영하고
			oEditors.getById["detail"].exec("UPDATE_CONTENTS_FIELD", []);
			//	무결성 검사하고
			
			//	서브밋 시킨다.
			$("#frm").submit();
		});
		//	대카테고리를 선택하면 중카테고리를 불러와서 처리해야 하므로
		//	이 부분은 카테고리 등록 폼에서 이미 만들어 놓았다.
		//	다 아시겠지만... 이처럼 여러곳에서 사용해야 하는 자바스크립트 코드가 
		//	있다면...	이 부분은 js 파일로 따로 만들어서 사용해도 된다.
		$("#lcode").change(function(){
			//	무결성 검사하고
			var	code = $("#lcode option:selected").val();
			if(code == '0') {
				alert("대카테고리를 선택하세요");
				return;
			}
			//	Ajax로 불러오기 요청한다.
			$.ajax({
				url : '../Shop/MCategorySearch.sun',
				data : $("#frm").serialize(),
				dataType : 'json',
				type : 'post',
				success : function(data) {
					$("#mcode").find("option").remove();
					$("#mcode").append("<option value='0'>==선택하세요==</option>");
					var	cate = data.mcate;
					$.each(cate, function(index, item){
						var	code = item.code;
						var	name = item.name;
						$("#mcode").append("<option value='"+code+"'>"+name+"</option>");
					});
				},
				error : function() {
					alert("나오지마라");
				}
			});
		});
		//	소카테고리를 선택하면 소카테고리를 불러와서 처리해야 하므로
		$("#mcode").change(function(){
			//	숙제로 내준 것을 이용하면 된다.
			//	무결성 검사하고
			var	code = $("#mcode option:selected").val();
			if(code == '0') {
				alert("중카테고리를 선택하세요");
				return;
			}
			//	Ajax로 소카테고리를 보내달라고 요청한다.
			$.ajax({
				url : '../Shop/SCategorySearch.sun',
				data : $("#frm").serialize(),
				dataType : 'json',
				type : 'post',
				success : function(data) {
					$("#scode").find("option").remove();
					$("#scode").append("<option value='0'>==선택하세요==</option>");
					var	cate = data.scate;
					$.each(cate, function(index, item){
						var	code = item.code;
						var	name = item.name;
						$("#scode").append("<option value='"+code+"'>"+name+"</option>");
					});
				},
				error : function() {
					alert("나오지마라");
				}
			});
		});
	});
	
	
	
	
</script>
<body>
	<!-- 	상품 등록을 위한 폼을 만든다. -->
	<form method="post" id="frm" action="../Shop/GoodsRegProc.sun" enctype="multipart/form-data">
		<table width="800" border="1" align="Center">
			<tr>
				<td>카테고리</td>
				<td>
					<select id="lcode" name="lcode">
						<option value="0">==선택하세요==</option>
						<c:forEach var="cate" items="${LCATE}">
							<option value="${cate.code}">${cate.name}</option>
						</c:forEach>
					</select>
					<!-- 	다음 카테고리를 Ajax를 이용해서 처리할 예정이다. -->
					<select id="mcode" name="mcode">
						<option value="0">==선택하세요==</option>
					</select>
					<select id="scode" name="scode">
						<option value="0">==선택하세요==</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>상품명</td>
				<td><input type="text" id="name" name="name"></td>
			</tr>			
			<tr>
				<td>제조사</td>
				<td><input type="text" id="maker" name="maker"></td>
			</tr>			
			<tr>
				<td>상품 단가</td>
				<td><input type="text" id="price" name="price"></td>
			</tr>			
			<tr>
				<td>상품 이미지</td>
				<td><input type="file" id="img" name="img"></td>
			</tr>			
			<tr>
				<td colspan="2" align="center">
					<textarea id="detail" name="detail" rows="20" cols="109"></textarea>
				</td>
			</tr>			
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="상품등록" id="rBtn">
				</td>
			</tr>			
		</table>
	</form>
</body>
</html>
