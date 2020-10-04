<!-- 지시어 : 서버에서 JSP 페이지를 처리하는 방법에 대한 정의 -->
<!--  
1) page : 페이지 기본 설정 
2) include : include file 설정
3) taglib : 외부 라이브러리 태그 설정
-->
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="header.jsp" %>

	<!-- 선언태그 : JSP페이지에서 Java의 멤버변수 또는 메서드를 선언--> 	
	<%!
		int num = 10;
		String str = "jsp";
		ArrayList<String> list = new ArrayList<String>();
		
		public void jspMethod(){
			System.out.println("--jspMethod()--");
		}

	%>
	<!-- 주석태그 -->
	<!-- 여기는 HTML 주석입니다. -->
	<%-- 여기는 jsp 주석입니다. --%>	
	
	
	<!-- 스크립트릿 태그: JSP 페이지에서 Java코드를 넣기 위한 태그 -->
	<%
		if(num>0){
	%>	
	<p> num > 0 </p>
	
	<% 
		}else{
	%>
	<p> num <=0 </p>
	
	<%
	}
	%>
		
	<!-- 표현식 태그 -->
	num is <%= num %>	
	
	<%@ include file="footer.jsp" %>
	
</body>
</html>