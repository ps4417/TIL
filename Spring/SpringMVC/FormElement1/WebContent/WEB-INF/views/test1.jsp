<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>test1</h1>
	<form:form modelAttribute="dataBean" action="result" method="get">
		<form:hidden path="a1"/>
		text : <form:input path="a2"/><br/>
		password: <form:password path="a3"/><br/>
		textarea: <form:textarea path="a4"/><br/>
		<form:button>확인</form:button> <!-- disabled="true" 로 지정하면 버튼이 안눌림 -->
	
	</form:form>
</body>
</html>