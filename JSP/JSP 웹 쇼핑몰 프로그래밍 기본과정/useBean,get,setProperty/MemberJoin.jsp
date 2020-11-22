<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<h2>회원가입</h2>
		<form action="MemberJoinProc.jsp" method="post">
			<table  border=1>
				<tr height="50">
					<td width="150" align="center" >아이디</td>
					<td width="350"><input type="text" name="id" size="40" placeholder="아이디를 입력하세요"></td>
				</tr>
				<tr height="50">
					<td width="150" align="center" >패스워드</td>
					<td width="350"><input type="password" name="pass" size="40" placeholder="비밀번호를 입력하세요"></td>
				</tr>				
				<tr height="50">
					<td width="150" align="center">이메일</td>
					<td width="350"><input type="email" name="email" size="40"></td>
				</tr>
				<tr height="50">
					<td width="150" align="center">전화번호</td>
					<td width="350"><input type="tel" name="tel" size="40"></td>
				</tr>
				<tr height="50">
					<td width="150" align="center">주소</td>
					<td width="350"><input type="text" name="address" size="40"></td>
				</tr>
				<tr height="50">
					<td align="center" colspan="2">
					<input type="submit" value="회원가입"></td>
				</tr>
			</table>
		</form>
		
</body>
</html>