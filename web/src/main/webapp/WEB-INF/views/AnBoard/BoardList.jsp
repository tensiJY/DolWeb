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
		$("#wBtn").click(function(){
			$(location).attr("href", "../AnBoard/WriteForm.sun");
		});
		
		$("#sBtn").click(function(){
			//	
			$("#sfrm").submit();
		});
	});
</script>
<body>
<%--	�˻� ���ڸ� ����� --%>
	<form method="POST" id="sfrm" action="../AnBoard/BoardSearch.sun">
	<table width="1000" border="1" align="center">
		<tr>
			<td align="center">
				<select id="kind" name="kind">
					<option value="title">����</option>
					<option value="body">����</option>
					<option value="writer">�۾���</option>
					<option value="tags">�±�</option>
				</select>
				<input type="text" id="word" name="word">
				<input type="button" id="sBtn" value="�˻��ϱ�">
			</td>
		</tr>
	</table>
	</form>
<%--	����� �����ְ� --%>
	<table width="1000" border="1" align="center">
		<tr>
			<th>��ȣ</th>
			<th>����</th>
			<th>�۾���</th>
			<th>�ۼ���</th>
			<th>��ȸ��</th>
		</tr>
		<c:forEach var="data" items="${LIST}">
<%--
		��� ���ϱ� ���� ����� �����ϸ� ������ �����Ͱ� VO Ŭ���� ������
		�̰��� �ٽ� ArrayList�� ������ �����ȴ�.
--%>		
			<tr>
				<td>${data.no}</td>
				<td>
					<a href="../AnBoard/HitProc.sun?nowPage=${PINFO.nowPage}&oriNo=${data.no}">${data.title}</a>
				</td>
				<td>${data.writer}</td>
				<td>${data.wday}</td>
				<td>${data.hit}</td>
			</tr>
		</c:forEach>
	</table>
<%--	������ �̵� ��� --%>
	<table width="1000" border="1" align="center">
		<tr>
			<td align="center">
				<%--	[����] --%>
				<c:if test="${PINFO.startPage eq 1}">
					[����]
				</c:if>
				<c:if test="${PINFO.startPage ne 1}">
					<a href="">[����]</a>
				</c:if>
				<%--	[1][2][3] --%>
				<c:forEach var="page" begin="${PINFO.startPage}" end="${PINFO.endPage}">
					<a href="">[${page}]</a>
				</c:forEach>
				<%--	[����] --%>
				<c:if test="${PINFO.endPage eq PINFO.totalPage}">
					[����]
				</c:if>
				<c:if test="${PINFO.endPage ne PINFO.totalPage}">
					<a href="">[����]</a>
				</c:if>
			</td>
		</tr>
	</table>
<%--	��Ÿ ����� �����. --%>
	<table width="1000" border="1" align="center">
		<tr>
			<td align="right">
				<input type="button" id="wBtn" value="�۾���">
			</td>
		</tr>
	</table>
</body>
</html>