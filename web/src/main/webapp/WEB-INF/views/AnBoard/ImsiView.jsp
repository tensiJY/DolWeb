<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<body>
	<c:if test="${from eq 'update'}">
		<c:if test="${count eq 0}">
			<script>
				alert("��й�ȣ�� �޶� ������ �� �����ϴ�.");
			</script>
		</c:if>
		<c:if test="${count ne 0}">
			<script>
				alert("������ �Ϸ�Ǿ����ϴ�.");
			</script>
		</c:if>
	</c:if>
	<c:if test="${from eq 'delete'}">
		<c:if test="${count eq 0}">
			<script>
				alert("��й�ȣ�� �޶� ������ �� �����ϴ�.");
			</script>
		</c:if>
		<c:if test="${count ne 0}">
			<script>
				alert("������ �Ϸ�Ǿ����ϴ�.");
			</script>
		</c:if>
	</c:if>
	<!-- 	�󼼺���� ����Ʈ ��Ų��. -->
	<!-- 
	redirect�� �ȵȴ�.
	�ֳ��ϸ� redirect�� ��Ű�� ������ ����� ��� ����� ��ȿȭ �ǰ� �����̷�Ʈ �ȴ�.
	
	redirct ����� �ƴϰ� �Ϲ� ��û ������� ó���ؾ� �Ѵ�.			

		�����̸� ���� ���ο� ������� �󼼺���� ������ �ȴ�.
		
		�����̸� 	������ �����Ǹ� ��Ϻ���� ������ �ǰ�
					������ ���еǸ� �󼼺���� ������ �ʹ�. 

	 -->
	<c:if test="${from eq 'update'}"> 
	<script>
		document.location.href = 
			"../AnBoard/HitProc.sun?nowPage=${nowPage}&oriNo=${oriNo}";
	</script>
	</c:if>
	<c:if test="${from eq 'delete'}">
		 <c:if test="${count ne 0}">
			<script>
				document.location.href= "../AnBoard/BoardList.sun";
			</script>
		 </c:if>
		 <c:if test="${count eq 0}">
			 <script>
			document.location.href = 
				"../AnBoard/HitProc.sun?nowPage=${nowPage}&oriNo=${oriNo}";
			</script>
		 </c:if>
	</c:if>	
</body>
</html>




