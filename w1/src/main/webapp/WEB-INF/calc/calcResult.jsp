<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>NUM1 ${param.num1} </h1>
	<h1>NUM2 ${param.num2} </h1>
	
	<h1>SUM ${Integer.parseInt(param.num1) + Integer.parseInt(param.num2) }</h1>
	<!-- JSP파일내에서 데이터처리를 할경우 사용자가 GET방식으로 현재 JSP파일을 호출하였을때 500에러 발생함. -->

</body>
</html>