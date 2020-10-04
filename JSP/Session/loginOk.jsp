<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 세션이 넘어왔는지 확인한다. -->
	<%
		session = request.getSession();
		out.print("memberId : "+session.getAttribute("memberId")+"<br>");
	%>
	
	<!-- logout시 session을 날려버리는 코드 작성 -->
	<form action="logoutCon" method="post">
		<input type="submit" value="logout">
	</form>
</body>
</html>