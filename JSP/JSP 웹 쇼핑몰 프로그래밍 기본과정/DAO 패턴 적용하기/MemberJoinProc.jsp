<%@page import="model.MemberDAO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%
		request.setCharacterEncoding("UTF-8");
		// 취미 부분은 별도로 다시 빈클래스에 저장
		String [] hobby = request.getParameterValues("hobby");
		
		// 배열에 있는 내용을 하나의 스트림으로 저장
		String texthobby = "";
		for(int i=0;i<hobby.length;i++){
			texthobby += hobby[i]+ " ";
		}
	%>
	
	<!-- useBean을 이용해 한꺼번에 데이터를 받아옴 -->
	<jsp:useBean id="mbean" class="model.MemberBean">
		<jsp:setProperty name="mbean" property="*"/> <!-- 매핑시키시오 -->
	</jsp:useBean>
	
	<%
		mbean.setHobby(texthobby); //기존 취미는 주소번지가 저장되기에 위에 배열의 내용을 하나의 스트림으로 저장한 변수를 다시 입력
		
		// 데이터 베이스 클래스 객체 생성
		MemberDAO mdao = new MemberDAO();
		mdao.insertMember(mbean);
	%>
	오라클에 완료
	
	
</body>
</html>