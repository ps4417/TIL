# JSP 주요 스크립트

## 지시어

> 서버에서 jsp 페이지를 처리하는 방법에 대한 정의



1. page : 페이지 기본 설정 => <% page 속성=“속성 값”>

```jsp
<%@ page language="java" contentType="text/html; charset=EUC-KR“ pageEncoding="EUC-KR"%>
```



2. include : include file 설정 =>   <% include file=“파일명”>

```jsp
<%@ include file=“header.jsp"%>
```



3. taglib : 외부라이브러리 태그 설정 =>  <% taglib uri=“uri” prefix=“네임스페이스명”>

```jsp
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix=“c"%>
```

