JSP (Java Server Pages)
------------------------

-> jsp is a server side web technology which is used to create a web application.
-> it is similar to servlet, in otherwords it is an extension to Servlet.
->in Servlet,Presentation / UI development is not flexible 
-> Servlet is good for buisness logic.
-> JSP is good for Presentation . it is  a scripting language like javascript , but the difference is 
   javascript runs in client browser , JSP execute in server machine.


   Advantages of JSP
   -----------------
   1. less coding 

       compare to Servlet JSP application can be developed in less code.

   2.easy to maintain

       here buisness logic and Presentation logic can be scripted easily.

   3.fast development

       it is not required  to compile and deploy after each and every change in java.

   4.Exception free

       here it is not required to handle unchecked Exception.




LIFECYCLE OF JSP
-----------------

    1.translation
    2.compillation
    3.class loading
    4.object creation
    5.initialization
    6.request processing
    7.memory deallocation



    1.translation
    -------------

       JSP engine(like Servlet container) is responsible to translate jsp page into Servlet.each and every page 
       is intrernally a Servlet.

       Example
       -------
         hello.jsp will be converted into hello_jsp.java

         location of servlet file (.java and .class) 
         -------------------------------------------
          (inside workspace folder)

         .metadata\plugins\org.eclipse.wst.server.core\tmp0\work\catalina\localhost\LOGIN_MVC
         \org\apache\jsp

    2.compillation
    --------------

        Servlet container compile the .java file and create .class file.

    3.class loading
    ---------------

       class loader load the .class file into web-server

    4.object creation
    -----------------

       Servlet container call the constructor to create the object, here object is used to 
       call all the lifecycle methods of jsp.

    5.initialization
    ----------------

       Servlet container call _jspInit() method to intialize the servlet. it executes single time 
       during lifecycle.

    6.request processing
    --------------------

      here Servlet container call _jspService() method to process the request . it will execute for
      each user request.

    7.memory deallocation
    ---------------------

      servlet container call _jspDestroy() method to release the web application from web server.
      it will execute for single time only.


----------------------------------------------------------------------------------------------------

  -> jsp support 9 implict objects and 5 predefined tags to develop a  web application


  9 objects
  ----------

  object name            class name                 Description
  -----------            ----------                 ------------

  out                    JspWriter                	it is used to wtrite the text into web browser

  request 				 HttpServletRequest         this object is available in each jsp page to handle request

  response               HttpServletResponse        it is used to generate the response.

  session                HttpSession                it is used to handle the session

  config                 ServletConfig              it is used to set local initialization parameter.

  application            ServletContext             it is used to set the global initialization parameter.

  page                   Object                     it is just like this in java

  pageContext            PageContext                it is used to set the parameter in different scope.

  exception 			 Throwable                  it is used to handle the Exception.



  5 tags
  ------

  1.scriptlet
  2.declaration
  3.expression
  4.action
  5.directive

  		 1.scriptlet
 		 -----------

 		 ->  in jsp,we can write java code using scriptlet tag 
 		     syntax:
 		      <%
 		        //java codes
 		      %>
 		 -> the codes which present within scriptlet tag , it will be placed into _jspService() method 
 		    of Servlet.

 		 2.declaration
 		 -------------

 		 -> to declare a varibale/function , we can use declaration tag

 		    syntax:
 		    -------

 		     <%!
 		       //declaration codes
 		     %>

 		 -> the codes which  present in declaration tag , it will be placed outside _jspService() method 
 		    of Servlet.

 		 3.expression
 		 ------------

 		   -> this tag is self expressive
 		   ->it is used to display output without using any object.

 		    syntax:
 		    -------
 		      <%=expression%>

 		   -> the codes which present within expression atg , it will be placed within write() method.

 		 4.action
 		 --------

 		   -> it is used to control the flow between web components


 		   <jsp:forward>  it is used to redirect the current request and response to another jsp 
 		                  or any other web components like forward() method of RequestDispatcher.

 		   <jsp:include>  it is used to add the response of any other web components within current
 		                  jsp page like include() method of RequestDispatcher.

 		   <jsp:useBean>  it is used to create the object of bean class.

 		   <jsp:setProperty> it is used to set current page request into bean class.

 		 5.directive
 		 -----------
 		   
 		   -> it indicates to jsp engine , how to translate jsp page into Servlet.

 		         3 types of directive
 		         --------------------
 		         1.page directive
 		         2.include directive
 		         3.taglib directive


 		         1.page directive
 		         ----------------

 		           generally page directive always placed in top of the jsp page , but it is not mandatory

 		           syntax:

 		           <%@page attribute=value%>

 		         2.include directive
 		         -------------------

 		         it is used to include a web component into current jsp.
 		         syntax:

 		           <%@include attribute=value%>

 		         3.taglib directive
 		         ------------------

 		         -> tag lib directive is used to define the tag library.
 		         -> jstl.jar is required to use tag library.


 		         (JSTL) jsvascript standard tag library 

 		         syntax:

 		          <%@taglib uri="" prefix=""%>


 		          				JSP supports following tag library
 		          				------------------------------------
 		          				1.core tags
 		          				-----------
 		          				  core tags are generally used to declare variable , manage URL,
 		          				   flow of control..

 		          				     uri of core tag
 		          				     ---------------

 		          				     http://java.sun.com/jsp/jstl/core

 		          				     prefix of core tag is "c" , but it is not mandatory.
 		          				2.function tags
 		          				---------------
 		          				   function tags are generally used to use the String function 

 		          				   uri of function tags 
 		          				   ---------------------

 		          				  http://java.sun.com/jsp/jstl/functions

 		          				  prefix is "fn"
 		          				3.sql tags
 		          				----------

 		          				  it supports DBMS operations.

 		          				  uri is 
 		          				  ------

 		          				   http://java.sun.com/jsp/jstl/sql

 		          				   prefix is sql
------------------------------------------------------------------------------------------------------------
EXAMPLE PROGRAMS OF JSP 
-----------------------
1. exapmle of (scriptlet,declaration,expression tag)

//index.jsp
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
hello hi  bye java
<%!
   int x = 90;
   String getMessage()
   {
	   return "java is simple";
   }
%>
<h2>using scriptlet tag</h2>
<%
 
out.println("Jsp is simple <br>");
out.println("X is : <br>" +x);
out.println("<br> Message is "+getMessage());

%>
<hr>

<h2>using expression tag</h2>
<br> x is : <%=x %>
<br> message is : <%=getMessage() %>

</body>
</html>

-----------------------------------------------------------------
2.(to use request and response object of jsp)

   //s2.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="s3.jsp">
  enter username : <input type="text" name="uname"> <br>
  enter password : <input type="password" name="pass"> <br>
  
           <input type="submit" value="login">
</form>

</body>
</html>


//s3.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <%=request.getParameter("uname") %> <br>
  <%=request.getParameter("pass") %>
  
  <%
  response.sendRedirect("s4.jsp");
  %>
</body>
</html>

//s4.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<marquee>ok i am in s4</marquee>

</body>
</html>

---------------------------------------------------------------------------------------------------`````
3.config and context in jsp
---------------------------

//index.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="s5.jsp">click for s5</a>
<a href="s6.jsp">click for s6</a>
</body>
</html>

//s5.jsp

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

//s6.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>within s6.jsp</h1>
city is : <%=config.getInitParameter("city") %>
state is : <%=application.getInitParameter("state") %>

</body>
</html>


//web.xml

<?xml version="1.0" encoding="UTF-8"?>
<web-app>
 <servlet>
    <servlet-name>s5.jsp</servlet-name>
    <jsp-file>/s5.jsp</jsp-file>
    <init-param>
 <param-name>city</param-name>
 <param-value>bbsr</param-value>
 </init-param>
  </servlet>
  <servlet-mapping>
    <url-pattern>/s5.jsp</url-pattern>
    <servlet-name>s5.jsp</servlet-name>
  </servlet-mapping>
   <servlet>
    <servlet-name>s6.jsp</servlet-name>
    <jsp-file>/s6.jsp</jsp-file>
    <init-param>
 <param-name>city</param-name>
 <param-value>puri</param-value>
 </init-param>
  </servlet>
  <servlet-mapping>
    <url-pattern>/s6.jsp</url-pattern>
    <servlet-name>s6.jsp</servlet-name>
  </servlet-mapping>
  
  <context-param>
    <param-name>state</param-name>
    <param-value>odisha</param-value>
  </context-param>
</web-app>


------------------------------------------------------------------------------------------------------------
4.session objects in jsp
-------------------------

//s7.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <%
  session.setAttribute("email", "sambitgulu@gmail.com");
  session.setAttribute("phone","8328898651");
  %>
  
  <a href="s8.jsp">click for s8</a>

</body>
</html>

//s8.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
email : <%=session.getAttribute("email")%>
phone : <%=session.getAttribute("phone") %>
<%
  session.removeAttribute("email");
%>

<a href="s9.jsp">click for s9</a>

</body>
</html>

//s9.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
email : <%=session.getAttribute("email")%>
phone : <%=session.getAttribute("phone") %>
<%
  session.invalidate();
%>

<a href="s10.jsp">click for s10</a>


</body>
</html>

//s10.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
email : <%=session.getAttribute("email")%>
phone : <%=session.getAttribute("phone") %>

</body>
</html>

-------------------------------------------------------------------------------------------------------------
5.example of forward and include in jsp
---------------------------------------

//s11.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
hello
<jsp:forward page="s12.jsp"></jsp:forward>
hi

</body>
</html>

//s12.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
ok i am in s12.jsp
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="body.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

//header.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div style="color: aqua">
<h1>i am the header</h1>
<br>
<br>
</div>

</body>
</html>

//body.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br>
<marquee>i am the body bruh</marquee>
<br>
</body>
</html>

//footer.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<br>
hellow  i am the footer
</body>
</html>

---------------------------------------------------------------------------------------------------------
//example of jsp:useBean tag in jsp

//s13.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="obj" class="p1.Test"></jsp:useBean>
value is : <%=obj.cube(4) %>

</body>
</html>

//note
//create an user defined package p1 and inside that create a class Test
java resources -> src -> right click -> new -> package -> p1 -> right click p1 -> new -> class -> Test

//Test.java

package p1;

public class Test {
	public static int cube(int x)
	{
		return x*x*x;
	}

}

------------------------------------------------------------------------------------------------------------
8.example of page directive tag


//s14.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.Date" %>  //importing a java class
    <%@page info="about us page" %>     //setting the page information
    <%@page errorPage="s15.jsp" %>      //browser will redirect to this page if error or exception occurs
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Today : <%=new Date() %>
<hr>
Page information : <%=getServletInfo() %>  //it will show the content set by info attribute of page directive
<hr>
<%=10/0 %>  //it generates arithmetic exception hence the broswer will redirect to the page specified
            //in errorPage attribute of page directive tag

</body>
</html>


//s15.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<font color="red">
  Exception is : <hr>
  <%=exception %>
</font>
<br>
<font color="green">welcomr to error page</font>

</body>
</html>

-------------------------------------------------------------------------------------------------------------
10.example of include directive tag
------------------------------------

//s16.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file="s17.jsp" %>
    <hr>
    <%@include file="s18.jsp" %>
    <hr>
    <%@include file="s19.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

//s17.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%!
  int c = 0;
int pageVisit()
{
	return c++;
}
%>
 
 Page Visited : <%=pageVisit() %>
</body>
</html>

//s18.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <center><img alt="" src="user.jpg" width="200" height="200"></center>
</body>
</html>

//s19.jsp

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>Copyright @ RHR 2019</center>
</body>
</html>

Note :- add an image into web content folder and rename it to user.jpg

----------------------------------------------------------------------------------------------------------------



---------------------------------------------------------------------------------------------------------------

PROGRAMS ON JSTL (JSP STANDARD TAG LIBRARY)
--------------------------------------------

-> to use the library we need a jar file jstl-1.2.jar

-> add jstl-1.2.jar into your project
     (place it in WEB-INF -> lib -> jstl-1.2.jar -> right click -> build path -> add to build path)

 -----------------------------------------
 11.example of core tags of JSTL


 //s20.jsp

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
 -----------------------------------------
 12.example of functions tag


 //s21.jsp

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
 ------------------------------------------
13.programs on sql tag

 -> add ojdc14.jar to builf classpath

       (place it in WEB-INF -> lib -> ojdbc.jar -> right click -> build path -> add to build path)




 //s22.jsp

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
--------------------------------------------
      

