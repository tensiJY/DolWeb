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
				alert("비밀번호가 달라서 수정할 수 없습니다.");
			</script>
		</c:if>
		<c:if test="${count ne 0}">
			<script>
				alert("수정이 완료되었습니다.");
			</script>
		</c:if>
	</c:if>
	<c:if test="${from eq 'delete'}">
		<c:if test="${count eq 0}">
			<script>
				alert("비밀번호가 달라서 삭제할 수 없습니다.");
			</script>
		</c:if>
		<c:if test="${count ne 0}">
			<script>
				alert("삭제가 완료되었습니다.");
			</script>
		</c:if>
	</c:if>
	<!-- 	상세보기로 리디렉트 시킨다. -->
	<!-- 
	redirect는 안된다.
	왜냐하면 redirect를 시키면 이전에 사용한 모든 기능이 무효화 되고 리다이렉트 된다.
	
	redirct 방식이 아니고 일반 요청 방식으로 처리해야 한다.			

		수정이면 수정 여부에 관계없이 상세보기로 보내면 된다.
		
		삭제이면 	삭제가 성공되면 목록보기로 보내면 되고
					삭제가 실패되면 상세보기로 보내고 싶다. 

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




