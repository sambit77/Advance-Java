Listener
--------

1.it is a predefined interface present in javax.serclet package.
2.it executes internally when some event occurs

------------------------------------------------------------------------------------------------------------------
Example:
---------
  (program to count no of users visited your website and  no of active users currently on your website)
  -----------------------------------------------------------------------------------------------------

 1.  create a new project -> Listener_Ex -> create index.html
     create a package(lit) steps  Listener_Ex -> java resources -> src -> right click -> new -> package (lit)

 2.   create 3 servlets :-
      ------------------
        right clcik lit -> new -> Servlet -> Login
        right clcik lit -> new -> Servlet -> Welcome
        right clcik lit -> new -> Servlet -> Logout

 3.   create a Listener :-
      ------------------
      right click lit -> new -> listener -> give name (UserCount) -> check lifecycle of 
      HttpSession events to override -> finish

  4. add any image to webcontent folder and rename it to user.jpg (optional)


   codes
   ------

   //index.html

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="lit.Login">
   Enter Username : <input type="text" name="username">
   Enter Password : <input type="text" name="password">
   
         <input type="submit" value="login">
</form>

</body>
</html>

//Login.java (Servlet)

package lit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/lit.Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
		String username  = request.getParameter("username");
		String password  = request.getParameter("password");
		
		if(username.equalsIgnoreCase(password))
		{
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			response.sendRedirect("lit.Welcome");
			
		}
		else
		{
			out.print("Login Failed");
		}
		
	}

}


//Welcome.java (Servlet)

package lit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Welcome
 */
@WebServlet("/lit.Welcome")
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Welcome() {
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
		String username = (String) session.getAttribute("username");
		
		out.print("<center><img src='user.jpg' width=300 height=300></img></center>");
		
		out.print("<hr><hr> Username: "+username +"</center>");
		out.print("<a href='lit.Logout'>Logout</a>");
	}

}


//Logout.java (Servlet)

package lit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/lit.Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("index.html");
	}



}


//UserCount.java

package lit;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class UserCount
 *
 */
@WebListener
public class UserCount implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public UserCount() {
        // TODO Auto-generated constructor stub
    }
   
	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    
    int currentuser = 0;
    int totaluser=0;
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	currentuser++;
    	totaluser++;
    	System.out.println("Cuurent logged in users "+currentuser);
    	System.out.println("Total users visited "+totaluser);
    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	currentuser--;
    
    	System.out.println("Cuurent logged in users "+currentuser);
    	System.out.println("Total users visited "+totaluser);
    }
	
}


------------------------------------------------------------------------------------------------------------