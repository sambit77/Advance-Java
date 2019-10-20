<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>within s5.jsp</h1>
city is : <%=config.getInitParameter("city") %>
state is : <%=application.getInitParameter("state") %>

</body>
</html>