<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<sql:setDataSource driver="oracle.jdbc.OracleDriver" url="jdbc:oracle:thin:@localhost:1521"
user="system" password="lit" 
var="db"></sql:setDataSource>

<sql:update dataSource="${db }">
insert into student values(99,'alisha',9.0)
</sql:update>
<sql:update dataSource="${db }">
insert into student values(98,'monali',9.0)
</sql:update>

<sql:transaction dataSource="${db }">
<sql:update>
  insert into student values(6397,'sam',8.2)
</sql:update>
<sql:update>
  insert into student values(5801,'tanya',8.2)
</sql:update>
<sql:update >
  insert into student values(5521,'shardha',8.2)
</sql:update>
</sql:transaction>
<sql:update dataSource="${db }">
update student set name='gupta' where roll=5801
</sql:update>
<sql:update dataSource="${db }">
delete from student where roll=5521
</sql:update>

//fetching data from database 

<sql:query var="rs" dataSource="${db }">
select * from student
</sql:query>
<table width="100%" border="2">
<tr>
<th>roll</th>
<th>name</th>
<th>cgpa</th>
</tr>
<c:forEach var="table" items="${rs.rows }">
<tr>
<td><c:out value="${table.name }"></c:out> </td>
<td><c:out value="${table.roll }"></c:out> </td>
<td><c:out value="${table.cgpa }"></c:out> </td>
</tr>
</c:forEach>
</table>

</body>
</html>