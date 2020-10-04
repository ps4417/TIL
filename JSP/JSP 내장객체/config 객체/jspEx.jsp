<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
		String adminId;
		String adminPw;
	%>
		<!-- 이렇게 써도됨  - getServletConfig().getInitParameter("adminId"); -->
	<%
		adminId = config.getInitParameter("adminId");
		adminPw = config.getInitParameter("adminPw");
	%>
	<p>adminId:<%=adminId %></p>
	<p>adminPw:<%=adminPw %></p>
</body>
</html>