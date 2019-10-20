<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.Date" %>
    <%@page info="about us page" %>
    <%@page errorPage="s15.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Today : <%=new Date() %>
<hr>
Page information : <%=getServletInfo() %>
<hr>
<%=10/0 %>

</body>
</html>