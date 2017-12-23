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
			//	��۾��� ��û�� �Ѵ�.
			$(location).attr("href", "../AnBoard/AnWriteForm.sun?oriNo=${MAP.VIEW.no}&nowPage=${nowPage}");
		});
		
		$("#mBtn").click(function(){
			$(location).attr("href", "../AnBoard/ModifyForm.sun?oriNo=${MAP.VIEW.no}&nowPage=${nowPage}");
		});
		
		$("#dBtn").click(function() {
			//	���� �����ȣ �Է� �ް�
			var	pw = prompt("��й�ȣ�� �Է��� �ּ���");			
			//	�� ��й�ȣ�� ���� �Է��� ��
			$("#password").val(pw);
			//	���� ����� ��Ų��.
			$("#ifrm").submit();
		});
	});
</script>
<body>
<!-- 	�󼼺��� �����ְ�	

		�Ѿ�� �����ʹ� MAP �̶�� Ű������ Map ���·� �Ѿ�԰�
		���߿��� �󼼺��� ������ VIEW��� Ű������ ���� �ִ�.
		
		${MAP.VIEW}		<==		���� AnBoardVO	�� ���´�.
		
		�� �߿��� ��ȣ�� getNo() �Լ��� �����ǰ� �����Ƿ�...
		
		��ȣ�� ����Ϸ���		${MAP.VIEW.no}
-->
	<table width="800" border="1" align="center">
		<tr>
			<td>�۹�ȣ</td>
			<td>${MAP.VIEW.no}</td>
			<td>��ȸ��</td>
			<td>${MAP.VIEW.hit}</td>
		</tr>
		<tr>
			<td>�ۼ���</td>
			<td>${MAP.VIEW.writer}</td>
			<td>�ۼ���</td>
			<td>${MAP.VIEW.wday}</td>
		</tr>
		<tr>
			<td>����</td>
			<td colspan="3">${MAP.VIEW.title}</td>
		</tr>
		<tr>
			<td>����</td>
			<td colspan="3">${MAP.VIEW.body}</td>
		</tr>
		<tr>
			<td>�±�</td>
			<td colspan="3">${MAP.VIEW.tags}</td>
		</tr>
	</table>
<!-- 	��� ��� �����ְ� -->
	<table width="800" border="1" align="center">
		<tr>
			<th>�۹�ȣ</th>
			<th>����</th>
			<th>�ۼ���</th>
			<th>�ۼ���</th>
			<th>��ȸ��</th>
		</tr>
	<c:forEach var="data" items="${MAP.LIST}">
		<tr>
			<td>${data.no}</td>
<!-- 	����� �Խ����� ���ۿ� ���� ����� ���������� ǥ�õǴ� ���� ����.
		(���ۺ��ٴ� ���� �� ������� ǥ���ϴ� ���� ����.)
		
		�׷��� �츮�� Step �̶�� ������ ������ ���Ұ�
		Step �̶�� ������ ���° �ܰ��� ��������� ����� ���̴�.
		
		���	Step�� �̿��ϸ� ���� ����� �����Ͱ� ���° �ܰ������� ǥ���� �� 
				���� ���̴�.
 -->
			<td>
	<c:if test="${data.bstep ne 0}">	<!-- 	����� ��쿡�� �鿩���Ⱑ �Ǿ�� �ϰڴ�. -->
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
<!-- 	��Ÿ ��� ����� 

		JSP�ð��� �����ߴ� ������ �̿��� ���� ������ �ؼ� 
		�ʿ��� ������Ը� ������ �ֵ��� ó���ؾ� �Ѵ�.
-->
	<table width="800" border="1" align="center">
		<tr>
			<td align="center">
				<input type="button" id="anBtn" value="��۾���">
				<input type="button" id="mBtn" value="�����ϱ�">
				<input type="button" id="dBtn" value="�����ϱ�">
			</td>
		</tr>
	</table>
	<!-- 	
			������ �ϱ����ؼ��� �翬�� ��й�ȣ�� �޾Ƽ� �˷��־�� �Ѵ�.
			��ó�� �߿��� ������ GET ������� ������ ���ȿ� ����ϴ�.
			
			�ӽ� ���� ���� ������ ���� ��� �� �ȿ� �Է��� ��
			�� ���� submit ������� �����Ͽ� ���ȿ� ���� �ؾ� �Ѵ�,
	-->
	<form method="POST" id="ifrm" action="../AnBoard/DeleteProc.sun">
		<input type="hidden" id="password" name="password">
		 <!-- 	��ó�� ���� ���� ������ ���� �ִ� ���븸 ������ ���Ƿ�
		 		���� ������ �ʿ䰡 �ִ� �����ʹ� ��� �� �ȿ� �Է��� ���ƾ� �Ѵ�.
		 -->
		 <input type="hidden" id="oriNo" name="oriNo" value="${MAP.VIEW.no}">
		 <input type="hidden" id="nowPage" name="nowPage" value="${nowPage}">
	</form>	
</body>
</html>
