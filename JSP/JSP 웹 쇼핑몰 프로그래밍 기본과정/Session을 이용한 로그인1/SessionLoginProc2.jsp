<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	
	<h2> 세션 로그인 처리 2</h2>
<%
	// 세션을 이용하여 데이터를 불러옴
	String id = (String)session.getAttribute("id");
	String pass = (String)session.getAttribute("pass");
	



	// URL로 넘어온게 아니기 때문에 밑에 3줄이 필요가 없다.
	/* request.setCharacterEncoding("UTF-8");
	
	String id = request.getParameter("id");
	String pass = request.getParameter("pass"); */

%>
	
	<h2>당신의 아이디는 <%=id %>입니다. 패스워드는 <%=pass %></h2>
	

</body>
</html>