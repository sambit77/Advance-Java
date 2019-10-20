<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:set var="s" value="${'hello' }"></c:set>
length of string is : <c:out value="${fn:length(s) }"></c:out> <br>

<c:if test="${fn:startsWith(s,'h') }">
<c:out value="${'yes starets with h' }"></c:out>
</c:if>
<c:choose>
<c:when test="${fn:endsWith(s,'E') }">
<c:out value="${'yes ends with e' }"></c:out>
</c:when>
<c:otherwise>
<c:out value="${'does not ends with e '}"></c:out>
</c:otherwise>
</c:choose>

</body>
</html>