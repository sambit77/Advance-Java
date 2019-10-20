<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:out value="${'hello welcome to jstl ' }"></c:out>    //printing to web page
<c:set var="x" value="10000"></c:set>   //variable declaration

<br>

x = <c:out value="${x }"></c:out> <br>
<c:remove var="x"/>
x = <c:out value="${x }"></c:out> <br>
<br>
<c:set var="mark" value="50"></c:set>
	//if example
<c:if test="${mark > 30 }">		
<c:out value="${'yes passed' }"></c:out>
</c:if>
//if-else example
<c:choose>					
<c:when test="${mark > 60 }">
 <c:out value="${'yes first' }"></c:out>
</c:when>
<c:otherwise>
<c:out value="${'just pass' }"></c:out>
</c:otherwise>
</c:choose>
<br>
//for each loop
<c:forEach var="i" begin="10" step="2" end="15">	
<c:out value="${i }"></c:out>
</c:forEach>


</body>
</html>