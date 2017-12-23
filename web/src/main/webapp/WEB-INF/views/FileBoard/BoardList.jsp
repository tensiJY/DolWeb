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
	function Search() {
		//	���Ἲ �˻� �ϰ�
		
		$("#sfrm").submit();
	}
	$(document).ready(function(){
		$("#wBtn").click(function(){
			$(location).attr("href", "../FileBoard/WriteForm.sun");
		});
		
		$("#sBtn").click(Search);
	});
</script>
<body>
<!-- 	�˻�â ����� -->
	<form method="POST" id="sfrm" action="../FileBoard/BoardSearch.sun">
		<input type="hidden" name="nowPage" value="1">
		<table width="1000" border="1" align="center">
			<tr>
				<td align="center">
					<select id="target" name="target">
						<option value="writer">�۾���</option>
						<option value="title">����</option>
						<option value="body">����</option>
						<option value="both">���� + ����</option>
					</select>
					<input type="text" id="word" name="word">
					<input type="button" id="sBtn" value="�˻��ϱ�">					
				</td>
			</tr>
		</table>
	</form>
<!-- 	��Ϻ��� �����ְ� -->
	<table width="1000" border="1" align="center">
		<tr>
			<td colspan="6">${PINFO.nowPage} / ${PINFO.totalPage}(${PINFO.totalCount})</td>
		</tr>
		<tr>
			<th>��ȣ</th>
			<th>����</th>
			<th>�۾���</th>
			<th>�ۼ���</th>
			<th>��ȸ��</th>
			<th>÷������</th>
		</tr>
		<c:forEach var="data" items="${LIST}" varStatus="status">
		<tr>
			<%--
				�����ͺ��̽��� ��ȣ�� �����ϰ�
				��� ������� ��ȣ�� �����ϴ� ���
			--%>
			<td>${(PINFO.nowPage - 1) * PINFO.listCount + status.count}</td>
			<td>
				<a href="../FileBoard/BoardView.sun?oriNo=${data.no}&nowPage=${PINFO.nowPage}&kind=list">${data.title}</a>
			</td>
			<td>${data.writer}</td>
			<td>${data.wday}</td>
			<td>${data.hit}</td>
			<td>${data.cnt}</td>
<%--			
			<c:if test="${data.cnt ne 0}">
				ø�������� �����Ѵٴ� �ǹ��̰� 
			</c:if>
			<c:if test="${data.cnt eq 0}">
				ø�������� �������� �ʴ´ٴ� �ǹ��̴�. 
			</c:if>
 --%>			
		</tr>
		</c:forEach>		
	</table>
<!-- 	����
		������ �̵� ��� ���� �׽�Ʈ �� ���ÿ�.
-->

<!-- 	��Ÿ ��� ����� -->
	<table width="1000" border="1" align="center">
		<tr>
			<td align="center">
				<input type="button" id="wBtn" value="�۾���">
			</td>
		</tr>
	</table>
</body>
</html>