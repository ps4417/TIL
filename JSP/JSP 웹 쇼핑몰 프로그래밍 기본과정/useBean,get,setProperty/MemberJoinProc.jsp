<%@page import="bean.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2> 회원 정보 보기</h2>
	<!-- request로 넘어온 데이터를 자바 빈즈랑 매핑시켜주는  useBean -->
	<jsp:useBean id="mbean" class="bean.MemberBean"> <!-- 객체생성  MemberBean mbean = new MemberBean(); -->
	<!--JSP 내용을 자바빈 클래스(MemberBean의미)에 데이터 매핑(넣어줌) -->
		<jsp:setProperty name="mbean" property="*"/>		
	</jsp:useBean> 
	
	<h2>당신의 아이디는 <jsp:getProperty property="id" name="mbean"/> </h2>
	<h2>당신의 비밀번호는 <jsp:getProperty property="pass" name="mbean"/> </h2>
	<h2>당신의 이메일은 <jsp:getProperty property="email" name="mbean"/> </h2>
	<h2>당신의 전화번호는 <%=mbean.getTel() %></h2> <!-- 이렇게 화면에 출력할 수도 있다. -->

</body>
</html>