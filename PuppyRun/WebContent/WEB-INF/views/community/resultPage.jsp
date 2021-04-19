<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int comNo = (int)request.getAttribute("comNo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta http-equiv="refresh" content="0.0001 url=/community/detail?comNo=<%= comNo %>">
</head>
<body>

</body>
</html>