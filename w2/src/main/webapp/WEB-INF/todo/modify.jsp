<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo Modify/Remove</title>
</head>
<body>
<!-- 2개의 form태그를 이용해서 수정/삭제 작업을 분리한다. 삭제의 경우에는 tno값이 보이지 않도록 input태그를 hidden 으로 처리한다. -->
	<form id="form1" action="/todo/modify" method="post">
		<div>
			<input type="text" name="tno" value="${dto.tno }" readonly>
		</div>
		<div>
			<input type="text" name="title" value="${dto.title }" readonly>
		</div>
		<div>
			<input type="date" name="dueDate" value="${dto.dueDate}" readonly>
		</div>
		<div>
			<input type="checkbox" name="finished" ${dto.finished ? "checked" : "" } readonly>
		</div>


		<div>
			<button type="submit">Modify</button>
		</div>
	</form>

	<form id="form2" action="/todo/remove" method="post">
		<input type="hidden" name="tno" value="${dto.tno }" readonly>
		<div>
			<button type="submit">Remove</button>
		</div>
	</form>

</body>
</html>