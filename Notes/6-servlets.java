SERVLET
-------

  -> servlet is a dynamic web-component , processes the requests issued from client side application.
  -> servlet is an api (servlet-api.jar) which contains all the classes and interfaces related
     to servlet application.

  ->servlet-api.jar is present in Tomcat9.0\lib.

     install Tomcat9
     ---------------
        -> do not install in c drive (as we are going to do lot many changes many a time so ....)
        -> change 2nd port no from 8080 to 9090 |(as 8080 is for oracle)

  ->Servlet is a predefined interface present in javax.servlet package which is the root interface of all classes
    and interfaces related to servlet application.

  -> servlet interface contains 5 abstarct methods
         1.init()
         2.service()
         3.destroy()
         4.getServletConfig()
         5.getServletInfo()


   -> Tom cat is a web-server,it is also known Servlet Container.
   -> Servlet Container is responsible to manage the entire web application.
   -> Tom cat Server provides a pre-defined directory architecture.

       DIRECTORY ARCHITECTURE 
       ----------------------
         (INSIDE WEBAPPS FOLDER OF TOMCAT)
         ---------------------------------
         \yourappname
             \view resources / pages
             \WEB-INF
                \classes  (contains .class files)
                \lib      (contains .jar file)
                 web.xml

  How to access webapps from broswer ?
  ------------------------------------
   http://localhost:9090/yourappname/x

   http:// : as browser only understands http request.
   localhost if server is running in local machine  then localhost or 127.0.0.1 
             otherwise ip address of server machine.
   9090      port no , now request will enter into particular server
   yourappname  web application name 
   x          finally give the servlet name , which is responsible to process the request.


   -> each servlet is iadentified by unique url pattern for security issue.
   -> mapping between servlet class  and url pattern should be placed within web.xml file.
   -> web.xml is known as deployment descriptor.

   web.xml (how to do mapping between servlet class xservlet and url /x)
   -------
    <web-app>
     <servlet>
       <servlet-name>lit</servlet-name>
       <servlet-class>xservlet</servlet-class>
     </servlet>
     <servlet-mapping>
      <url-pattern>http://localhost:9090/yourappname/x</url-pattern>
      <servlet-name>lit</servlet-name>
     </servlet-mapping>     
    </web-app>

----------------------------------------------------------------------------------------------------------
how to create a servlet ?
--------------------------

  1.design an user defined class and it should be public.
  2.user-deefined class should be sub class of Servlet directly or indirectly.
  3.override the abstract method.
  4.put request processing logic in service() method.

  Steps to create a servlet application
  -------------------------------------

  -> create a directory structure.
  -> create a client side application  (hello.html)
  -> create a configurfation file (web.xml)
  -> create a server side component i.e servlet class (XServlet.java)
  -> set the classpath of servlet-api.jar
  -> compile the java program
  -> put all the resources (web components) according to tomcat directory structure
  -> copy the project folder into web-apps folder of tomcat.
  -> start the web server (tomcat.exe)
  -> request client side page in web browser.
      http://localhost:9090/yourappname


      Note:
       to check if tomcat server is already running or not
         ->apache -> bin -> run tomcat9w.exe -> set -> startup type -> manual
                                             service status -> stopped -> ok

------------------------------------------------------------------------------------------------------

webapp1 :- MyFirstApp


index.html
----------
<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
<a href="x">Click me</a>
</body>
</html>

web.xml
-------

<web-app>
     <servlet>
       <servlet-name>lit</servlet-name>
       <servlet-class>XServlet</servlet-class>
     </servlet>
     <servlet-mapping>
      <url-pattern>/x</url-pattern>
      <servlet-name>lit</servlet-name>
     </servlet-mapping>     
 </web-app>


 XServlet.java
 -------------

import javax.servlet.*;
import java.io.*;

public   class XServlet implements Servlet
{
  public  void init(ServletConfig paramServletConfig)
    throws ServletException{}

  public  ServletConfig getServletConfig(){return null;}

  public  void service(ServletRequest paramServletRequest, ServletResponse paramServletResponse)
    throws ServletException, IOException
    {
    	System.out.println("Request processed by XServlet class"); //prints output to console

    	//prints output to browser
    	PrintWriter out = paramServletResponse.getWriter();
    	paramServletResponse.setContentType("text/html");
    	out.println("Request processed by XServlet class");

    }

  public  String getServletInfo(){return null;}

  public  void destroy(){}
}


How to compile?
---------------

E:\Storage\AdvanceJava\Notes@Autumn\MyFirstApp\WEB-INF\classes>set path=;C:\Program Files\Java\jdk1.8.0_20\bin;

E:\Storage\AdvanceJava\Notes@Autumn\MyFirstApp\WEB-INF\classes>set classpath=;E:\Apache\lib\servlet-api.jar;

E:\Storage\AdvanceJava\Notes@Autumn\MyFirstApp\WEB-INF\classes>javac XServlet.java


now follow the tomcat directory structure and place it inside webapps folder inside tomcat 
and run the tomcat9.exe

request  client side page in browser
   
    go to browser type url ; http://localhost:9090/MyFirstApp

    Output:
    -------------

    when we click on the hyperlink "click me ", it send request to the server , the service method of particular
    servlet class handles the request (the web.xml contains the url mapping i.e which servlet class will
    	process the incomin request)

    in browser we see the output Request processed by XServlet class
    as well as in tomcat server console Request processed by XServlet class

 ----------------------------------------------------------------------------------------------------------------
 SERVLET ANNOTATION
 ------------------

 the web.xml is not the only way to map to a particular servlet class.the servlet mapping can also
 be done using ANNOTATION.

   -> no nned of web.xml file
   -> we need to import javax.servlet.annotation.*;
   -> @WebServlet("/x") or @WebServlet(urlPatterns="/x")   : this line is enough for mapping.
   -> Servlet 3.0 onwards java introduced the concept of annotation.
   -> annotation is the replacement of web.xml


   Example
   -------

   web-app2 (Servlet annotation)
   -----------------------------
  index.html
  ----------
  <!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
<a href="x">Click me</a>
</body>
</html>

web.xml
-------
 (not required)
  
    XServlet.java
    -------------

import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.*;

@WebServlet("/x")  //or we can write @WebServlet(urlPatterns="/x") 

public   class XServlet implements Servlet
{
  public  void init(ServletConfig paramServletConfig)
    throws ServletException{}

  public  ServletConfig getServletConfig(){return null;}

  public  void service(ServletRequest paramServletRequest, ServletResponse paramServletResponse)
    throws ServletException, IOException
    {
    	System.out.println("Request processed by XServlet"); //prints output to console

    	//prints output to browser
    	PrintWriter out = paramServletResponse.getWriter();
    	paramServletResponse.setContentType("text/html");
    	out.println("Request processed by XServlet class");

    }

  public  String getServletInfo(){return null;}

  public  void destroy(){}
}


How to compile?
---------------

E:\Storage\AdvanceJava\Notes@Autumn\MyFirstApp\WEB-INF\classes>set path=;C:\Program Files\Java\jdk1.8.0_20\bin;

E:\Storage\AdvanceJava\Notes@Autumn\MyFirstApp\WEB-INF\classes>set classpath=;E:\Apache\lib\servlet-api.jar;

E:\Storage\AdvanceJava\Notes@Autumn\MyFirstApp\WEB-INF\classes>javac XServlet.java


now follow the tomcat directory structure and place it inside webapps folder inside tomcat 
and run the tomcat9.exe

request  client side page in browser
   
    go to browser type url ; http://localhost:9090/MySecondApp

    Output:
    -------------

    when we click on the hyperlink "click me ", it send request to the server , the service method of particular
    servlet class handles the request (using annotation url mapping is done)

    in browser we see the output Request processed by XServlet class
    as well as in tomcat server console Request processed by XServlet class
-------------------------------------------------------------------------------------------------------------

Lifecycle of Servlet
---------------------

 -> Servlet Container is responsible to control the entire lifecycle of Servlet.
 ->following are the stages of servlet lifecycle.
      1.class loading
      2.object creation
      3.servlet initialization
      4.request processing.
      5.memory deallocation.

      1.class loading
      ----------------
       -> servlet Container first load the class into web server which are present  in classes folder.

      2.object creation
      -----------------
       -> servlet Container create sthe object o f servlet-class by calling the constructor 
       -> object is required to call other methods of servlet

      3.servlet initialization
      ------------------------
       -> all the initialization codes must be placed within init() method of servlet
       -> here servlet Container call the init() method.

      4.request processing
      --------------------
       -> for each user request , service() method will be called by servlet container by
          passing request and response object.

      5.memory deallocation
      ---------------------
       -> to release memory servlet container call destroy() method of Servlet.

       Note:
       -----

       -> only service() method will execute for each user request i.e multiple times but other stage will 
          execute for a single time during the lifecycle of servlet.

      --Switching to eclipse

         directory structure in eclipse
         ------------------------------
         java resources
             src 
               ______.java
               ______.java
         build 
               ______.class
               ______.class
         webcontent
             view resources
             WEB-INF
                lib
                  ______.jar
                  ______.jar
                web.xml

      create a new workspace for Servlet
      -----------------------------------
        run eclipse -> switch workspace -> other -> SERVLET_HOME -> launch

        then

        File -> new -> dynamic web project -> give project name -> add new runtime -> apache 9.0 -> 
        give tomcat installation directory (need to be done only once for entire workspace) -> finish

        Example program demonstrating life cycle of servlet
        ---------------------------------------------------

        right click on webcontent(inside your project) -> new -> html file -> index.html

        //index.html
        <!DOCTYPE html>
        <html>
        <head>
        <meta charset="ISO-8859-1">
        <title>Insert title here</title>
        </head>
        <body>
        <a href="XServlet">Click me bitch</a>
        </body>
        </html>

        right click on src(inside project name -> java resources ) -> new -> Servlet -> classname (XServlet)
        -> super class(javax.servlet.Servlet) -> finish

        //XServlet.java



import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class XServlet
 */
@WebServlet("/XServlet")
public class XServlet  implements Servlet {

    /**
     * Default constructor. 
     */
    public XServlet() {
        // TODO Auto-generated constructor stub
    	System.out.println("I am in constructor");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("i ma iin init()");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("i am in destroy");
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("i am in service");
	}

}

Output: (in server console)

I am in constructor //called when first time user sends request
i ma iin init()
i am in service
i am in service
i am in service...   (service is called each time for uuser requests)
i ma in destroy (when we close the server)

------------------------------------------------------------------------------------------------------------------
load-on-startup
---------------

 -> generally object creation , init() method invokation always happens at the time of first user request.
 -> so for the first time , response will be slower
 -> to avoid that drawback , load-on-startup can be used 
 -> load on startup is responsible to create object  and initialize the Servlet at the time of deployment.

 Example:
 -----------
    //load on startup using web.xml

 //index.html
   <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  hello
</body>
</html>

//XServlet.java


import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet implementation class XServlet
 */
public class XServlet  implements Servlet {

    /**
     * Default constructor. 
     */
    public XServlet() {
        // TODO Auto-generated constructor stub
    	System.out.println("I am in constructor");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
    	System.out.println("I am in init ");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}


//web.xml

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE web-app PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN" "http://java.sun.com/j2ee/dtds/web-app_2_2.dtd">
<web-app id="WebApp_ID">
	<display-name>losws</display-name>
	<servlet>
		<servlet-name>XServlet</servlet-name>
		<display-name>XServlet</display-name>
		<description></description>
		<servlet-class>XServlet</servlet-class>
		<load-on-startup>4</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>XServlet</servlet-name>
		<url-pattern>/XServlet</url-pattern>
	</servlet-mapping>
	
</web-app>


output : resources loaded at the time of server start up

Oct 09, 2019 9:15:35 PM org.apache.catalina.util.SessionIdGeneratorBase createSecureRandom
WARNING: Creation of SecureRandom instance for session ID generation using [SHA1PRNG] took [247] milliseconds.
I am in constructor
I am in init 
Oct 09, 2019 9:15:35 PM org.apache.coyote.AbstractProtocol start


-------------------------

Example:
load-on-startup using annotation

//index.html
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  hello
</body>
</html>

//XServlet.java


import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class XServlet
 */
@WebServlet(urlPatterns="/XServlet",loadOnStartup = 4)
public class XServlet implements Servlet {

    /**
     * Default constructor. 
     */
    public XServlet() {
        // TODO Auto-generated constructor stub
    	System.out.println("i am in constructor");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("i am in init");
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

output : resources loaded at the time of server start up

Oct 09, 2019 9:15:35 PM org.apache.catalina.util.SessionIdGeneratorBase createSecureRandom
WARNING: Creation of SecureRandom instance for session ID generation using [SHA1PRNG] took [247] milliseconds.
I am in constructor
I am in init 
Oct 09, 2019 9:15:35 PM org.apache.coyote.AbstractProtocol start

-------------------------------------------------------------------------------------------------------------
TYPES OF SERVLET
----------------

 -> to create Servlet class , the user defined class must be sub class of Servlet interface directly
    or indirectly

    Types 
    ------
    	->Servlet interface
    			|
    	->GenericServlet abstract class
    			|
    	->HttpServlet  abstract class



    1.Servlet interface
    -------------------

      -> if user defined class is sub class of Servlet interface,then it is required to override all
         the five abstarct methods.

         Example:
         --------


import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet implements Servlet {

    /**
     * Default constructor. 
     */
    public MyServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
	}

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}


    2.GenericServlet 
    ----------------

      -> it is the sub class of Servlet interface & already overrides 4 abstract methods except service()
      -> so here we need to override service() method only

      Example
      -------


import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class MyGenericServlet
 */
@WebServlet("/MyGenericServlet")
public class MyGenericServlet extends GenericServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public MyGenericServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}


    3.HttpServlet
    -------------

      -> it is the sub class of GenericServlet
      -> here service method is already over-ridden,so no need to override any method of Servlet

      -> according to request i.e get() or post() , it is required to override doGet() or doPost()
      ->this two method already called from service() method of HttpServlet


      Example
      --------


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyHttpServlet
 */
@WebServlet("/MyHttpServlet")
public class MyHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyHttpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


-----------------------------------------------------------------------------------------------------------------

Request processing
-------------------
		Request can be processed by 4 ways in java.

		  1.getParameter() method
		  -----------------------
		    syntax: String param_value = request.getParameter("param_name");

		    -> getParameter() always return value based on input parameter name 
		    -> if parameter name does not exists it returns null.

		  2.getParameterNames() method
		  ----------------------------
		    syntax: Enumeration e = request.getParameterNames();

		    ->getParameter() always return value based on input parameter name , here it is difficult to 
		      remember all input parameter name.
		    ->to avoid that drawback , we can use getParameterNames();
		      this method return all the parameter name of a form.

		  3.getParameterValues() method
		  -----------------------------
		    syntax: String[] param_values = request.getParameterValues("param_name");

		    -> the above two technique does not work if input field returns more than one values.
		    -> suppose to fetch data from checkbox , we have to use getParameterValues()

		  4.getParameterMap() method
		  --------------------------
		    synatx: Map<String,String[]> map = request.getParameterMap();
		    				|       |
		    			PARAMNAME  PARAMVALUES  

		    -> it is combination of getParameterNames() and getParameterValues();

-------------------------------------------------------------------------------------------------------------
PROGRMAS ON REQUEST PROCESSING
-------------------------------

1.getParameter() method
------------------------

//index.html
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="XServlet">
   Roll : <input type="text" name="roll">
   Name : <input type="text" name="name">
   Age  : <input type="text" name="age">
   
   <input type="submit" value="Go">
</form>

</body>
</html>


//XServlet.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class XServlet
 */
@WebServlet("/XServlet")
public class XServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/html");
	    
	    String roll = request.getParameter("roll");
	    String name = request.getParameter("name");
	    String age = request.getParameter("age");
	    
	    out.println(roll+"\t"+name+"\t"+age);
		
		
	}

}
----------------------------------------------------
2.by getParameterNames() method

//index.html

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="XServlet">
   Roll : <input type="text" name="roll">
   Name : <input type="text" name="name">
   Age  : <input type="text" name="age">
   
   <input type="submit" value="Go">
</form>

</body>
</html>


//XServlet.java



import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class XServlet
 */
@WebServlet("/XServlet")
public class XServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/html");
	    
	    Enumeration e = request.getParameterNames();
	    while(e.hasMoreElements())
	    {
	    	String paramname = (String) e.nextElement();
	        out.println(request.getParameter(paramname));
	    }
	    
	   
		
	}

}


-------------------------------------------------
3.by getParameterValues() method

//index.html
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="XServlet">
   Roll : <input type="text" name="roll">
   Name : <input type="text" name="name">
   Age  : <input type="text" name="age"> <br>
   Crush : <input type="checkbox" name="crush" value="shirley"> shirley<br>
           <input type="checkbox" name="crush" value="Anannya"> Anannya<br>
           <input type="checkbox" name="crush" value="simran">  Simran<br>
           
   
   
   <input type="submit" value="Go">
</form>

</body>
</html>

//XServlet.java


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class XServlet
 */
@WebServlet("/XServlet")
public class XServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/html");
	    
	    Enumeration e = request.getParameterNames();
	    while(e.hasMoreElements())
	    {
	    	String paramname = (String) e.nextElement();
	        String[] param_values = request.getParameterValues(paramname);
	        for(String s : param_values)
	        {
	        	out.print(paramname+"  "+s);
	        }
	    }
	    
	   
		
	}

}


------------------------------------------
4.getParameterMap() method

//index.html

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="XServlet">
   Roll : <input type="text" name="roll">
   Name : <input type="text" name="name">
   Age  : <input type="text" name="age"> <br>
   Crush : <input type="checkbox" name="crush" value="shirley"> shirley<br>
           <input type="checkbox" name="crush" value="Anannya"> Anannya<br>
           <input type="checkbox" name="crush" value="simran">  Simran<br>
           
   
   
   <input type="submit" value="Go">
</form>

</body>
</html>

//XServlet.java



import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class XServlet
 */
@WebServlet("/XServlet")
public class XServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    PrintWriter out = response.getWriter();
	    response.setContentType("text/html");
	    
	   Map<String,String[]> map = request.getParameterMap();
	   Set<String>  keys = map.keySet();
	   for(Object obj: keys)
	   {
		   out.print(obj+"<hr>");
		   String[] values = map.get(obj);
		   for(String v : values)
		   {
			   out.print("<br>"+v);
		   }
	   }
	    
	   
		
	}

}


---------------------------------------------------------------------------------------------------------------

ServletConfig and ServletContext
--------------------------------

-> both are pre-defined interface present in javax.servlet package
-> according to scope and availability ServletConfig and ServletContext are different
-> both are used to set initialization parameter.

 	ServletConfig
 	-------------

 		-> getServletConfig() is used to retrun the object of ServletConfig.
 		-> An object of ServletConfig is created by Servlet Container for each Servlet and it  
 		   is used to get information about configurfation parameter related to a particular
 		   Servlet from web.xml or annotation

 		-> the advantage of ServletConfig is that without modifying the Servlet file , information 
 		   can be changed.

 		->   syntax to set ServletConfig parameter

 		        <init-param>
 		            <param-name>variable_name</param-name>
 		            <param-value>value</param-value>
 		        </init-param>

    ServletContext
 	--------------

 	    -> it is a predefined interface present in javax.servlet package
 	    -> getServletContext() method is used to create the object of ServletContext interface
 	    -> an object of ServletContext is created by Servlet Container for a single time at the time
 	       of deployment i.e for entire web application ,  ServletContext object is created for single time.


 	    -> main advantage of ServletContext is to share an information to all Servlet , it can be used.
 	    -> no need to modify each Servlet.


 	    syntax to set ServletContext parameter
 	    --------------------------------------

 	     <context-param>
 	      <param-name>variable_name</param-name>
          <param-value>value</param-value>
 	     </context-param>


 	     -----------------------------------------------------------------------------------
 	     Diagram

(local to Servlet)
 	     config area    |       context area   (common for entire web application)
 	     				|
 username   system   	|			-> driver = oracle.jdbc.OracleDriver
 password  	lit			|           -> url = jdbc:oracle:thin:@localhost:1521:xe
 	     				|
 (student registration)	|
------------------------| 	     			
 	       config area	|
 	     				|
			        	|
 	     				|
 username 	   bbsr		|
 password	   smartcity|
 	     				|
 (Employee registration)|
 	     				|

    Program description
    --------------------

      -> there are 2 types of registration 
      		1.student registration
      		2.Employee registration

      -> the roll,name and cgpa of student are stored under student table in oracle database where
         user = "system" password="lit" 

      -> the eid,ename,salary of Employee are stored under Employee table in oracle database where
         user="bbsr" password="smartcity"


         ------------------------------------------------------------------------------------------
    Program codes  (using web.xml no annotation)
    -------------

    1.database part
        run sql commandline
        -------------------
          -> log in as user="system" password="lit"
          -> conn system/lit
          -> create a table student
             syntax :
               create table student(roll number(10),name varchar2(20),cgpa number(3,1));
          ->create an user bbsr with password=smartcity
             syntax:create user bbsr iadentified by bbsr
                   :grant dba to bbsr;
          -> then log in as bbsr user
             syntax: conn bbsr/smartcity
          -> create a table Employee
             syntax: create table employee(eid number(10),ename varcahr2(10),sal number(10));

         --------------------------------------------------------------------------------------

     2.Client side pages
     -------------------         

//index.html

     <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <pre>
    <a href="StudentRegistration.html">StudentRegistration</a>
    <a href="EmployeeRegistration.html">EmployeeRegistration</a>
   </pre>
</body>
</html>


//StudentRegistration.html

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<pre>
  <form action="StudentServlet">
    Enter roll :<input type="text" name="roll">
    Enter name :<input type="text" name="name">
    Enter cgpa :<input type="text" name="cgpa">
    
            <input type="submit" value="store to student">
  </form>
</pre>

</body>
</html>

//EmployeeRegistration.html

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<pre>
  <form action="EmployeeServlet">
    Enter eid    :<input type="text" name="eid">
    Enter ename  :<input type="text" name="ename">
    Enter salary :<input type="text" name="salary">
    
            <input type="submit" value="store to employee">
  </form>
</pre>
</body>
</html>


---------------------------------------------------------------

2.web.xml file

  <?xml version="1.0" encoding="UTF-8"?>
<web-app id="WebApp_ID" version="2.4" xmlns="http://java.sun.com/xml/ns/j2ee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd">
	<servlet>
		<description>
		</description>
		<display-name>StudentServlet</display-name>
		<servlet-name>StudentServlet</servlet-name>
		<servlet-class>StudentServlet</servlet-class>
		<init-param>
 		            <param-name>username</param-name>
 		            <param-value>system</param-value>
 		</init-param>
 		<init-param>
 		            <param-name>password</param-name>
 		            <param-value>lit</param-value>
 		</init-param>
		
	</servlet>
	<servlet>
		<description>
		</description>
		<display-name>EmployeeServlet</display-name>
		<servlet-name>EmployeeServlet</servlet-name>
		<servlet-class>EmployeeServlet</servlet-class>
		
		<init-param>
 		            <param-name>username</param-name>
 		            <param-value>bbsr</param-value>
 		</init-param>
 		<init-param>
 		            <param-name>password</param-name>
 		            <param-value>smartcity</param-value>
 		</init-param>
	</servlet>
	<servlet-mapping>
		<servlet-name>StudentServlet</servlet-name>
		<url-pattern>/StudentServlet</url-pattern>
	</servlet-mapping>
	<servlet-mapping>
		<servlet-name>EmployeeServlet</servlet-name>
		<url-pattern>/EmployeeServlet</url-pattern>
	</servlet-mapping>
	
	<context-param>
 	      <param-name>driver</param-name>
          <param-value>oracle.jdbc.OracleDriver</param-value>
    </context-param>
    <context-param>
 	      <param-name>url</param-name>
          <param-value>jdbc:oracle:thin:@localhost:1521:xe</param-value>
 	</context-param>
	
	
</web-app>


-------------------------------
3.StudentServlet.java


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

       
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		int roll = Integer.parseInt(request.getParameter("roll"));
		String name = request.getParameter("name");
		double cgpa = Double.parseDouble(request.getParameter("cgpa"));
		
		try
		{
			
			ServletContext context = getServletContext();
			ServletConfig config = getServletConfig();
			
			String driver = context.getInitParameter("driver");
			String url = context.getInitParameter("url");
			
			String username = config.getInitParameter("username");
			String password = config.getInitParameter("password");
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,username,password);
			
			String sql = "insert into student values(?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, roll);
			pst.setString(2, name);
			pst.setDouble(3, cgpa);
			
			int status = pst.executeUpdate();
			if(status>0)
			{
				out.print("<font color='green'>");
				out.print("Stored to student database under system account");
				out.print("</font>");
			}
			
		}
		catch (Exception e)
		{
			// TODO: handle exception
			System.out.println(e);
		}
	}

}
-----------------------------------------
4.EmployeeServlet.java


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeServlet
 */
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		int eid = Integer.parseInt(request.getParameter("eid"));
		String ename = request.getParameter("ename");
		int salary = Integer.parseInt(request.getParameter("salary"));
		
		try
		{
			
			ServletContext context = getServletContext();
			ServletConfig config = getServletConfig();
			
			String driver = context.getInitParameter("driver");
			String url = context.getInitParameter("url");
		
			String username = config.getInitParameter("username");
			String password = config.getInitParameter("password");
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,username,password);
			
			String sql = "insert into employee values(?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setInt(1, eid);
			pst.setString(2, ename);
			pst.setDouble(3, salary);
			
			int status = pst.executeUpdate();
			if(status>0)
			{
				out.print("<font color='green'>");
				out.print("Stored to employee database under bbsr account");
				out.print("</font>");
			}
			
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e);
		}
	}

	

}


------------------------------
5.add the ojdc14.jar

before running the web app add the .jar file for oracle database connection 

Steps
------
    -> copy ojdbc14.jar to lib folder under WEB-INF
    -> right click ojdc14.jar in that lib folder -> build -> add to build path

----------------------------------------------------------------------------------------------------------------
CONFIG AND CONTEXT PROGRMAS USING ANNOTATION
----------------------------------------------

  -> using annotation we can only declare config parameters , which are local to Servlet
  -> but to declare the context parameter , any how web.xml is required.
  -> syntax to declare config paramters using annotation

      @WebServlet(
		urlPatterns = { "/x" }, 
		initParams = { 
				@WebInitParam(name = "var_name", value = "var_value"), 
				@WebInitParam(name = "var_name", value = "var_value")
		})

  -> syntax to declare context params in web.xml(same as above)

     <context-param>
       <param-name>param_name</param-name>
       <param-value>param_value</param-value>
     </context-param>



     Example Program
     ---------------

     //index.html

     <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>


</head>
<body>
<pre>
 <a href="x">Click here for XServlet</a>
 <a href="y">Click here for YServlet</a>
</pre>

</body>
</html>

//web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" id="WebApp_ID" version="2.5">

   
<context-param>
<param-name>state</param-name>
<param-value>Odisha</param-value>
</context-param>

<context-param>
<param-name>country</param-name>
<param-value>India</param-value>
</context-param>



</web-app>


//XServlet.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class XServlet
 */
@WebServlet(
		urlPatterns = { "/x" }, 
		initParams = { 
				@WebInitParam(name = "city", value = "BBSR"), 
				@WebInitParam(name = "landmark", value = "Lingaraj Temple")
		})
public class XServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		ServletConfig config = getServletConfig();
		ServletContext context = getServletContext();
		
		out.print("<br> city"+config.getInitParameter("city"));
		out.print("<br> landmark"+config.getInitParameter("landmark"));
		
		out.print("<br> State"+context.getInitParameter("state"));
		out.print("<br> Country"+context.getInitParameter("country"));
	}

}


//YServlet.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class YServlet
 */
@WebServlet(
		urlPatterns = { "/y" }, 
		initParams = { 
				@WebInitParam(name = "city", value = "PURI"), 
				@WebInitParam(name = "landmark", value = "jagannath temple")
		})
public class YServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		ServletConfig config = getServletConfig();
		ServletContext context = getServletContext();
		
		out.print("<br> city"+config.getInitParameter("city"));
		out.print("<br> landmark"+config.getInitParameter("landmark"));
		
		out.print("<br> State"+context.getInitParameter("state"));
		out.print("<br> Country"+context.getInitParameter("country"));
	}

}

--------------------------------------------------------------------------------------------------------------




