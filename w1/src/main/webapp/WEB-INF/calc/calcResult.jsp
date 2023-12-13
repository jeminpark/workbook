<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>title</title>
</head>
<body>
	<h1>NUM1 ${param.num1}</h1>		<!--  el기술 jsp에서 사용하는 라이브러리 따로 추가할필요없는거같음. -->
	<h1>NUM2 ${param.num2}</h1>		<!--  주석에 el태그 사용하지말것. 컴파일러가 읽음 -->	
	
	<h1>SUM ${Integer.parseInt(param.num1) + Integer.parseInt(param.num2)}</h1>
</body>
</html>