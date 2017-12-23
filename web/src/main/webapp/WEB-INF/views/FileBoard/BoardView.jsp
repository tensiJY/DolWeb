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
		$("#lBtn").click(function(){
			$(location).attr("href", "../FileBoard/BoardList.sun?nowPage=${nowPage}");
		});		
		$("#sBtn").click(function(){
			$(location).attr("href", "../FileBoard/BoardSearch.sun?nowPage=${nowPage}");
		});
	});
</script>
<body>
	<!-- 	�󼼺��� �����ְ� -->
	<table border="1" width="800" align="center">
		<tr>
			<td>�۹�ȣ</td>
			<td>${VIEW.no}</td>
			<td>��ȸ��</td>
			<td>${VIEW.hit}</td>
		</tr>
		<tr>
			<td>�ۼ���</td>
			<td>${VIEW.writer}</td>
			<td>�ۼ���</td>
			<td>${VIEW.wday}</td>
		</tr>
		<tr>
			<td>����</td>
			<td colspan="3">${VIEW.title}</td>
		</tr>
		<tr>
			<td>����</td>
			<td colspan="3">${VIEW.body}</td>
		</tr>
	</table>
	<!-- 	÷������ �����ְ� -->
	<table border="1" width="800" align="center">
		<tr>
			<td align="center">÷������</td>
		</tr>
		<c:forEach var="files" items="${FILE}">
			<tr>
				<td>
					<a href="../FileBoard/FileDownload.sun?oriNo=${files.no}">${files.oriName}</a> (${files.len}) �ٿ�ε�� : ${files.download}
				</td>
			</tr>
		</c:forEach>
	</table>	
	<!-- 	������ ������ �����ְ� -->
	<table width="800" border="1" align="center">
		<tr>
			<td>������</td>
			<td>
				<c:if test="${PRENEXT.PRENO ne 0}">
					<a href="../FileBoard/BoardView.sun?oriNo=${PRENEXT.PRENO}&nowPage=${nowPage}&kind=${kind}">${PRENEXT.PRETITLE}</a>
				</c:if>
				<c:if test="${PRENEXT.PRENO eq 0}">
					${PRENEXT.PRETITLE}
				</c:if>
			</td>
		</tr>
		<tr>
			<td>������</td>
			<td>
				<c:if test="${PRENEXT.NEXTNO ne 0}">
					<a href="../FileBoard/BoardView.sun?oriNo=${PRENEXT.NEXTNO}&nowPage=${nowPage}&kind=${kind}">${PRENEXT.NEXTTITLE}</a>
				</c:if>
				<c:if test="${PRENEXT.NEXTNO eq 0}">
					${PRENEXT.NEXTTITLE}
				</c:if>
			</td>
		</tr>
	</table>	
	<!-- 	��Ÿ ��� ����� -->
	<table width="800" border="1" align="center">
		<tr>
			<td>
				<c:if test="${kind eq 'list'}">
					<input type="button" id="lBtn" value="��Ϻ���">
				</c:if>
				<c:if test="${kind eq 'search'}">
					<input type="button" id="sBtn" value="��Ϻ���">
				</c:if>
			</td>
		</tr>
	</table>
</body>
</html>





