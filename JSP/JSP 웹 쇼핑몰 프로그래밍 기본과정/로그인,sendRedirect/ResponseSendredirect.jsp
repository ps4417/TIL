<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>로그인 페이지</h2>
		
		<form action="ResponseLoginProc.jsp" method="post">
		<table width="400" border="1">
		<tr height="60">
			<td width="150" align="center">아이디</td>
			<td width="250" align="left">
				<input type="text" name="id">
			</td>
		</tr>
		<tr height="60">
			<td width="150" align="center">패스워드</td>
			<td width="250" align="left">
				<input type="password" name="pass">
			</td>
		</tr>
		<tr height="60">
			<td colspan="2" align="center">
				<input type="submit" value="전송">
			</td>
		</tr>
			
		</table>
		</form>
	</center>
</body>
</html>