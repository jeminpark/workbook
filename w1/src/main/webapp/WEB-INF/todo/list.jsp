<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Title</title>
</head>
<body>
	<h1>List Page</h1>
	
	<ul>
		<c:forEach var="dto" items="${list}">
			<li>${dto}</li>
		</c:forEach>
	</ul>
	
	<ul>
		<c:forEach var="num" begin="1" end="10">
			<li>${num}</li>
		</c:forEach>
	</ul>
	
	<h3>list 사이즈는 짝수 홀수? :</h3>
	
	<!-- if비교 -->
	<c:if test="${list.size() % 2 == 0 }">
		짝수
	</c:if>
	<c:if test="${list.size() % 2 != 0 }">
		홀수
	</c:if>
	
	
	<!-- if-else비교 -->
	<c:choose>
		<c:when test="${list.size() % 2 == 0 }">
			짝수
		</c:when>
		<c:otherwise>
			홀수
		</c:otherwise>
	</c:choose>
	
	<c:set var="target" value="5"></c:set>
	
	<ul>
		<c:forEach var="num" begin="1" end="10">
			<c:if test="${num == target}">
				num is target
			</c:if>
		</c:forEach>
	</ul>

	
	<!-- el태그 연습 -->
	<ul>
		<li>${list}</li>
	</ul>
	${list[0].tno} --- ${list[0].title}
	<%! String str = "AAA"; %>
	<h3>${1 + 2 + 3}</h3>
	<h3>${"AAA" += "BBB"}</h3>
	<h3>${"AAA".equals("AAA")}</h3>
	<h4>${list[0].title}</h4>
	<h4>${list[0].getTitle()}</h4>
</body>
</html>