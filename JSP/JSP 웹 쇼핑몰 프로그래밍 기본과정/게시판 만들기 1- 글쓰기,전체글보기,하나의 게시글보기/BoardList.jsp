<%@page import="model.BoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h2{
	text-align: center;
	}

	table{
	margin-left: auto; 
	margin-right: auto;
	background-color: skyblue;
	border:1px ridge black; 
	width:700;
	text-align: center;

	}	
	
</style>
</head>
<body>
<%
	// 전체 게시글의 내용을 jsp쪽으로 가져와야 함
	BoardDAO bdao = new BoardDAO();
	
	// 전체 게시글을 리턴 받아주는 소스
	ArrayList<BoardBean> arr = bdao.getAllBoard();	
%>

	<h2> 전체 게시글 보기 </h2>
	<table border="1">
		<tr height="40">
			<td align="center" width="50"> 번호 </td>
			<td align="center" width="320"> 제목 </td>
			<td align="center" width="100"> 작성자 </td>
			<td align="center" width="150"> 작성일 </td>
			<td align="center" width="80"> 조회수 </td>
		</tr>
		
<%
	for(int i=0;i<arr.size();i++){
		BoardBean bean = arr.get(i); // ArrayList에 저장되어 있는 빈클래스를 하나씩 추출		
%>
	<tr height="40">
			<td align="center" width="50"> <%=i+1 %> </td>
			<td align="left" width="320"> <a href="BoardInfo.jsp?num=<%= bean.getNum() %>"><%= bean.getSubject() %></a> </td>
			<td align="center" width="100"> <%=bean.getWriter() %> </td>
			<td align="center" width="150"> <%=bean.getReg_date() %> </td>
			<td align="center" width="80"> <%=bean.getReadcount() %> </td>
		</tr>
		<%} %>
		
		<tr height="40">		
			<td align="center" colspan="5">
			<input type="button" value="글쓰기" onclick="location.href='BoardWriteForm.jsp'">
			</td>
		</tr>
	</table>
</body>
</html>