<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>First Page!!</p>
	
	<!-- sendRedirect: 다른 곳으로 페이지를 바로 보내버림 -->
	<%
		response.sendRedirect("secondPage.jsp");
	%>
</body>
</html>