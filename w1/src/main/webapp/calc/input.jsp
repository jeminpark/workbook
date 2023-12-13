<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Title</title>
</head>
<body>

<form action="calcResult.jsp" method="post"> <!--  //form태그의 action을 'calcResult.jsp' 로 전송하고 전송방식은 post로 한다. -->
	<input type="number" name="num1"> 
	<input type="number" name="num2">
	<button type="submit">SEND</button>
</form>

</body>
</html>