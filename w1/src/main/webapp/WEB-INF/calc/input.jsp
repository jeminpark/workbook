<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Title</title>
</head>
<body>
<!-- 주석에 el태그 사용금지 컴파일러가 읽음 -->
<!-- WEB-INF 경로 밑으로 이동한 jsp 파일들은 브라우저에서 jsp를 직접호출 불가 -->
<!-- 브라우저에서 /calc/input 호출할경우 InputController.java 에서 중간에 실행되어 doGet을 타고 input.jsp 뷰를 응답할것임. -->
<!-- 이경우엔 urlPatterns에 적힌 그대로 쳐야한다. -->


<!--  <form action="calcResult.jsp" method="post">   //form태그의 action을 'calcResult.jsp' 로 전송하고 전송방식은 post로 한다. jsp파일 직접호출 WEB-INF 에 두면 브라우저가 찾을수없다. -->

<form action="/calc/makeResult" method="post"> <!--  //form태그의 action을 '/calc/makeResult' 로 전송하고 전송방식은 post로 한다. -->
	<input type="number" name="num1"> 
	<input type="number" name="num2">
	<button type="submit">SEND</button>
</form>

</body>
</html>