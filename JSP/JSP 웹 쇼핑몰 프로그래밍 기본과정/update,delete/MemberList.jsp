<%@page import="model.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 1. 데이터베이스에서 모든 회원의 정보를 가져온다. 2. table 태그를 이용해 화면에 회원들의 정보를 출력 -->
<%
	MemberDAO mdao = new MemberDAO();
	// 회원들의 정보가 얼마나 저장되어있는지 모르기에 가변길이인 ArrayList를 이용하여 데이터를 저장해준다.
	ArrayList<MemberBean> arr = mdao.allSelectMember();
	
	
%>
<h2> 모든 회원 보기 </h2>
	<table border="1">
		<tr height="50">
			<td align="center" width="150">아이디</td>
			<td align="center" width="250">이메일</td>
			<td align="center" width="200">전화번호</td>
			<td align="center" width="200">취미</td>				
		</tr>
	
	
	<% 
		for(int i=0; i<arr.size();i++){
			MemberBean bean = arr.get(i); // ArrayList에 담긴 Bean 클래스를 하나씩 추출
		
	%>
	
	
		<tr height="50">
			<td align="center" width="150">
			<a href="MemberInfo.jsp?id=<%=bean.getId()%>">
			<%=bean.getId() %></a></td>
			<td align="center" width="250"><%=bean.getEmail() %></td>
			<td align="center" width="200"><%=bean.getTel() %></td>
			<td align="center" width="200"><%=bean.getHobby() %></td>				
		</tr>
		<%} %>
	</table>

</body>
</html>