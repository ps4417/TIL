<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- form태그 이전에 기존의 세션 유무를 판단하기 위한 코드 작성 -->
	<%
		if(session.getAttribute("memberId") != null)
			response.sendRedirect("loginOk.jsp");
	%>


	<form action="loginCon" method="post">
		ID: <input type="text" name="mID"><br>
		PW: <input type="password" name="mPW"><br>
		<input type="submit" value="login">
	</form>
	
	
</body>
</html>