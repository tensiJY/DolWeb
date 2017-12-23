<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
//	�߰��� ���� ÷�� �Է»����� ������ ����� ����
	var	count = 1;
	$(document).ready(function(){
		$("#wBtn").click(function(){
			//	���Ἲ �ϰ�
			
			$("#wFrm").submit();
		});
		$("#aBtn").click(function(){
			//	����
			count++;
			if(count > 5) {
				count = 5;
				return;
			}
			//		�߰��� ������ �Ʒ��� ���� �κп��� �˾Ƴ���.
			var		div = $("#copy").find("tr");
			//		���� �߰��� ������ div �ȿ� �ִ� tr �κ��̹Ƿ�
			//		target ���ʿ� �ٿ��ֱ⸦ ���ش�.
			var copy = div.clone();
			$("#target").before(copy);
		});
	});
	
	//	JQuery�� ������ �ϼ��� �� ó���ϴ� ����� ���α׷� ����̴�.
	//	������ ������ �߰��̱� ������ JQuery ó���� ���� �ʰ� �ִ�.
	//	��ó�� ������ �߰��� ��쿡�� JavaScript�� ����ؾ߸� �Ѵ�.
	function delete1(my) {
		//	�� �Լ��� ȣ���� �� �̺�Ʈ�� �Ͼ ���߸� �˷��ָ�
		var	tr = $(my).parents("tr");
		//	�� ������ ���� tr�� ã�Ƽ�
		tr.remove();
		//	�� tr�� �����.
		count--;
	}
</script>
<body>
	<!-- 	�۾��� ���� �����. 
			���� ������ ���� ÷�ΰ� ���� ��쿡�� parameter Ÿ������ �����ϸ� �ȵǰ�
			�ݵ�� ��Ʈ�� ������� ó���ؾ� �Ѵ�.
			
			���		enctype="multipart/form-data"		������ �Ѵ�.
	-->
	<form method="POST" id="wFrm" action="../FileBoard/WriteProc.sun" enctype="multipart/form-data">
	<table width="800" border="1" align="center">
		<tr>
			<td>�۾���</td>
			<td>
				<input type="text" name="writer" id="writer" value="${sessionScope.UID}" readonly>
<!-- 	��	 
			�Է»��ڸ� ������� ���ϰ� �ϴ� ���
			1.	readonly
				==>		�̷��� �ϸ� �� �ȿ� �����Ͱ� ������ ���޵ȴ�.
			2.	disabled="disabled"
				==>		�̷��� �ϸ� �� �ȿ� �����ʹ� ������ ���޵��� �ʴ´�.
-->
			</td>
		</tr>
		<tr>
			<td>����</td>
			<td><input type="text" name="title" id="title"></td>
		</tr>
		<tr>
			<td>����</td>
			<td><textarea name="body" id="body"></textarea></td>
		</tr>
		<tr>
			<td>÷������ <input type="button" id="aBtn" value="�߰�"></td>
			<td><input type="file" name="files" id="files"></td>
		</tr>
		<tr id="target">
			<td>��й�ȣ</td>
			<td><input type="password" name="pw" id="pw"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" id="wBtn" value="�۾���">
			</td>
		</tr>	
	</table>
	</form>
	<!-- 	�� �κ��� display�� none���� �� �������Ƿ� ȭ�� ������ �ʴ´�. -->
	<div style="display:none" id="copy">
		<table>
		<tr>
			<td>÷������ <input type="button"value="����" onClick="delete1(this);"></td>
			<td><input type="file" name="files" id="files"></td>
		</tr>
		</table>
	</div>	
</body>
</html>






