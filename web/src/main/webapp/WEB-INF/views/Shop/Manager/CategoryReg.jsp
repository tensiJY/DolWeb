<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<script src="../se2/js/HuskyEZCreator.js" charset="UTF-8"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#lcode").change(function(){
			var	code = $("#lcode option:selected").val();
			if(code == '0') {
				alert("대카테고리를 선택하세요");
				return;
			}
			$.ajax({
				url : '../Shop/MCategorySearch.sun',
				data : $("#frm").serialize(),
				dataType : 'json',
				type : 'post',
				success : function(data) {
					$("#mcode").find("option").remove();
					$("#mcode").append("<option value='0'>==중카테고리를 선택하세요==</option>");
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
		$("#lBtn").click(function(){
			$("#frm").attr("action", "../Shop/LCategoryReg.sun").submit();
		});
		$("#mBtn").click(function(){
			var	lcate = $("#lcode option:selected").val();
			if(lcate == '0') {
				alert("대카테고리를 선택하세요");
				return;
			}
			$("#frm").attr("action", "../Shop/MCategoryReg.sun").submit();
			
		});
		$("#sBtn").click(function(){
			$("#frm").attr("action", "../Shop/SCategoryReg.sun").submit();
		});
		$("#gBtn").click(function(){
			$(location).attr("href", "../Shop/GoodsRegForm.sun");
		});
	});
</script>
<body>
	<form method="POST" id="frm" action="">
	<table width="900" border="1" align="center">
		<tr>
			<td colspan="2" align="right">
				<a href="#" id="gBtn">상품 등록</a>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2">
				<select name="lcode" id="lcode">
					<option value="0">==대카테고리를 선택하세요==</option>
					<c:forEach var="cate" items="${LLIST}">
						<option value="${cate.code}">${cate.name}</option>
					</c:forEach>					
				</select>
				<select name="mcode" id="mcode">
					<option value="0">==중카테고리를 선택하세요==</option>
				</select>
				<select name="scode" id="scode">
					<option value="0">==소카테고리를 선택하세요==</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>대카테고리</td>
			<td>
				<input type="text" name="lname" id="lname">
				<input type="button" id="lBtn" value="중카테고리 등록">
			</td>
		</tr>
		<tr>
			<td>중카테고리</td>
			<td>
				<input type="text" name="mname" id="mname">
				<input type="button" id="mBtn" value="중카테고리 등록">
			</td>
		</tr>
		<tr>
			<td>소카테고리</td>
			<td>
				<input type="text" name="sname" id="sname">
				<input type="button" id="sBtn" value="소카테고리 등록">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>






