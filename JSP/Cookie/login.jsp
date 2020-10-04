<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 로그인해서 들어갔을 때 쿠키가 있는지 확인해야하고 로그인 된 상태이기 때문에 form이 먼저 보여서는 안된다. 
그러므로 아래와 같은 코드를 작성해준다. -->
	<%
		Cookie[] cookies = request.getCookies();
		System.out.println("cookies: "+cookies);
		/* 쿠키가 null이 아니면 조회를 해본다.-> 조회하다가 쿠키중 memberId가 있으면 이미 로그인 된 상태인 것으로 보고 loginOk.jsp로 보낸다. */
		if(cookies !=null){
			for(Cookie c: cookies){
				if(c.getName().equals("memberId")){
					response.sendRedirect("loginOk.jsp");
				}
			}
		}
	%>


	<form action="loginCon" method="post">
		ID: <input type="text" name="mID"><br>
		PW: <input type="password" name="mPW"><br>
		<input type="submit" value="login">		
	</form>
</body>
</html>