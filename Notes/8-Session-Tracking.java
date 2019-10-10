SESSION TRACKING
----------------

   -> Session means it is a time interval.
   -> Http is a stateless protocol i.e it can not retain state after page submission.
   -> each request treated as new reuqest.
   -> to identify an user as an existing user , it is require to store the state of session.


   Session Tracking is known as Session Management
   it is possible by 4 ways in java 

       1.Hidden Form Field
       2. url-rewriting
       3.Cookie
       4.HttpSession


 1.Hidden Form Field
 ---------------------

       <input type="text">
       <input type="hidden">


       -> it is a server side session management technique , which work with any type of browser.
       -> in this technique , one hidden  textfield is used to store the value which is required in the next page.
       -> drawback is extra form submission is required


       Example
       --------

//index.html
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="FirstServlet">
   Roll : <input type="text" name="roll">
   Name : <input type="text" name="name">
   Age  : <input type="text" name="age">
  
   <input type="submit" value="Go">
</form>

</body>
</html>

//FirstServlet.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
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
		
		out.print(roll);
		out.print(name);
		out.print(age);
		out.print("<hr>");
		out.print("<form action='SecondServlet'>");
		out.print("<input type='hidden' name='roll' value="+roll+">");
		out.print("<input type='hidden' name='name' value="+name+">");
		out.print("<input type='hidden' name='age' value="+age+">");
		out.print("Enter branch : <input type='text' name='branch'>");
		out.print("Enter section : <input type='text' name='section'>");
		out.print("<input type='submit' value='go to second'>");
		out.print("</form>");
	
		
						
	}

}


//SecondServlet.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SecondServlet
 */
@WebServlet("/SecondServlet")
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecondServlet() {
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
		String branch = request.getParameter("branch");
		String section = request.getParameter("section");
		
		
		out.print(roll);
		out.print(name);
		out.print(age);
		out.print(branch);
		out.print(section);
		out.print("<hr>");
		out.print("<form action='FinalServlet'>");
		out.print("<input type='hidden' name='roll' value="+roll+">");
		out.print("<input type='hidden' name='name' value="+name+">");
		out.print("<input type='hidden' name='age' value="+age+">");
		out.print("<input type='hidden' name='branch' value="+branch+">");
		out.print("<input type='hidden' name='section' value="+section+">");
		out.print("Enter college : <input type='text' name='college'>");
		out.print("Enter city : <input type='text' name='city'>");
		out.print("<input type='submit' value='go to final'>");
		out.print("</form>");
	
		
	}


}


//FinalServlet.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SecondServlet
 */
@WebServlet("/FinalServlet")
public class FinalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalServlet() {
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
		String branch = request.getParameter("branch");
		String section = request.getParameter("section");
		String college = request.getParameter("college");
		String city = request.getParameter("city");
		
		
		out.print(roll);
		out.print(name);
		out.print(age);
		out.print(branch);
		out.print(section);
		out.print(college);
		out.print(city);		
		out.print("<hr>");
	
	
		
	}


}
-----------------------------------------------------------------------------------------------------------------
2.url-rewriting
---------------
  
  -> it is  a server side session management technique which will work in any browser
  -> here a token (paramete) is added at the end of the url
  -> the token is the combination of name/value pair i.e name-value

     syntax:
         URL?name1=value1&name2=value2....

  -> url-rewriting only work with get

    Example:
    --------      

 //index.html
    <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <form action="FirstServlet">
   Roll : <input type="text" name="roll">
   Name : <input type="text" name="name">
   Age  : <input type="text" name="age">
  
   <input type="submit" value="Go">
</form>
  
</body>
</html>

//FirstServlet.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
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
		
		out.print("Roll"+roll);
		out.print("NAme"+name);
		out.print("age"+age);
		out.print("<hr>");
		out.print("<a href='FinalServlet?roll="+roll+"&name="+name+"&age="+age+"'>Click me for final Servlet</a>");
	}

}


//FinalServlet.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FinalServlet
 */
@WebServlet("/FinalServlet")
public class FinalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalServlet() {
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
		
		out.print("Roll"+roll);
		out.print("NAme"+name);
		out.print("age"+age);
	}

}

----------------------------------------------------------------------------------------------------------------------
3.Cookies
---------

  -> it is a client side session management technique which works if user enable cookie in browser.
  -> here cookie will be created and added with response object i.e
       response.addCookie(ck1);

       syntax: Cookie ck1 = new Cookie("name","value");

  -> once cookie object added with response , it will be available in entire web application
  -> to get information available in cookies getCookies() method we have to call.

       Cookie[] ck = request.getCookies();


       Note: syntax for cookie value with spaces

        Cookie sk1 = new Cookie("name",URLEncoder.encode("value","UTF-8"));

        out.print("<br> Name:"+URLDecoder.decode(name,"UTF-8"));


        Example of Cookie
        -----------------

        //index.html
        <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <form action="FirstServlet">
   Roll : <input type="text" name="roll">
   Name : <input type="text" name="name">
   Age  : <input type="text" name="age">
  
   <input type="submit" value="Go">

</body>
</html>

//FirstServlet.java



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
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
		
		out.print("Roll"+roll);
		out.print("Name"+name);
		out.print("age"+age);
		out.print("<hr>");
	
		Cookie ck1 = new Cookie("roll", roll);
		Cookie ck2 = new Cookie("name", name);
		Cookie ck3 = new Cookie("age", age);
		response.addCookie(ck1);
		response.addCookie(ck2);
		response.addCookie(ck3);
		out.print("<a href='FinalServlet'>Click me for final Servlet</a>");
	}

}

//FinalServlet.java



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FinalServlet
 */
@WebServlet("/FinalServlet")
public class FinalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalServlet() {
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
		
		Cookie[] ck = request.getCookies();
		
		String roll = ck[0].getValue();
		String name =  ck[1].getValue();
		String age =  ck[2].getValue();
		
		out.print("Roll"+roll);
		out.print("NAme"+name);
		out.print("age"+age);
		out.print("<hr>");
	}

}


-------------------------------------------------------------------------------------------------------------------
4.HttpSession
-------------
   

   -> it is a server side session management technique , which will work in any browser
   -> once attribute added with HttpSession , it will be available for entire web application.
   ->  getSession() : it is a pre defined method of HttpSession , it is used to create the object of 
                      HttpSession interface

   -> setAttribute() : it is used to add attribute into session
   -> getAttribute() : it is used to get value from session attribute
   -> removeAttribute() : it is used to remove attribute from session
   -> invallidate() : it is ued to remove all attributes from session

   Example:
   --------
//index.html
   <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="First">
 <form action="FirstServlet">
   Roll : <input type="text" name="roll">
   Name : <input type="text" name="name">
   Age  : <input type="text" name="age">
  
   <input type="submit" value="Go to First">
</form>
</body>
</html>

//First.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class First
 */
@WebServlet("/First")
public class First extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public First() {
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
		
		out.print("Roll"+roll);
		out.print("Name"+name);
		out.print("age"+age);
		out.print("<hr>");
		
		HttpSession session = request.getSession();
		session.setAttribute("roll", roll);
		session.setAttribute("name", name);
		session.setAttribute("age", age);
		
		out.print("<a href='Second'>Go to Second</a>");
	}

}


//Second.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Second
 */
@WebServlet("/Second")
public class Second extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Second() {
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
		
		HttpSession session = request.getSession();
		
		String roll = (String)session.getAttribute("roll");
		String name =(String)session.getAttribute("name");
		String age =(String)session.getAttribute("age");
		
		out.print("Roll"+roll);
		out.print("Name"+name);
		out.print("age"+age);
		out.print("<hr>");
		
	session.removeAttribute("roll");
		
		out.print("<a href='Third'>Go to Third</a>");
	}

	


}


//Third.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Third
 */
@WebServlet("/Third")
public class Third extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Third() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				
				HttpSession session = request.getSession();
				
				String roll = (String)session.getAttribute("roll");
				String name =(String)session.getAttribute("name");
				String age =(String)session.getAttribute("age");
				
				out.print("Roll"+roll);
				out.print("Name"+name);
				out.print("age"+age);
				out.print("<hr>");
				
			session.invalidate();
				
				out.print("<a href='Final'>Go to Final</a>");
	}



}


//Final.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Final
 */
@WebServlet("/Final")
public class Final extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Final() {
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
		
		HttpSession session = request.getSession();
		
		String roll = (String)session.getAttribute("roll");
		String name =(String)session.getAttribute("name");
		String age =(String)session.getAttribute("age");
		
		out.print("Roll"+roll);
		out.print("Name"+name);
		out.print("age"+age);
		out.print("<hr>");
	}


}

--------------------------------------------------------------------------------------------------------------------
