Inter Servlet Communication
---------------------------

-> Servlet to Servlet Communication is known as inter Servlet Communication.
-> it is possible by following ways
		1.forward() method
		2.include() method
		3.sendRedirect() method


		1.forward() method
		------------------

			-> it is a method of RequestDispatcher() interface which is used to forward the current servlet 
			   request and response to any Servlet.

			   syntax:
			      RequestDispatcher rd = request.getRequestDispatcher("servlet_calss_name/jsp_file_name");
			      rd.forward(request,response);

			-> if a Servlet does not fit to process entire request of client then it passes to any other
			   Servlet using forward() method.


			   enduser--> client side apps -> s1 Servlet --forward--> s2 Servlet
			   						|            |                       |
			   						|            |          			 |
			   						|            |						 |
			   						|         (response)              (response)
			   						|                                    |
			   						|                                    |
			   						|-------------------------------------

--------------------
Example of forward() method
----------------------------

//index.html
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<pre>
   <form action="LoginServlet">
     Enter Username : <input type="text" name="uname">
     Enter Password : <input type="password" name="pass">
     
                      <input type="submit" value="Login">
   </form>
</pre>

</body>
</html>

//LoginServlet.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
           
           String uname = request.getParameter("uname");
           String pass = request.getParameter("pass");
            
           out.println("Present in Login Servlet");
           out.println("username"+uname);
           out.println("password"+pass);
           
           RequestDispatcher rd = request.getRequestDispatcher("VallidateServlet");
           rd.forward(request,response);
           out.print("hello");
           
        
           
	}

}


//VallidateServlet.java



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VallidateServlet
 */
@WebServlet("/VallidateServlet")
public class VallidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VallidateServlet() {
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
         
         String uname = request.getParameter("uname");
         String pass = request.getParameter("pass");
          
         out.println("<h1>Present in Vallidate Servlet</h1>");
         out.println("<hr> username"+uname);
         out.println("<hr>password"+pass);
         if(uname.equalsIgnoreCase(pass))
         {
        	 out.print("<font color='green'>");
        	 out.print("Login successful");
        	 out.print("</font>");
         }
         else
         {
        	 out.print("<font color='red'>");
        	 out.print("Login Failed");
        	 out.print("</font>");
         }
	}

}


----------------------------------------------------------------------------------------------------------------
2.include() method
-------------------

   -> it is used to include another Servlet response into current Servlet

      syntax: 

      RequestDispatcher rd = request.getRequestDispatcher("servlet_calss_name/jsp_file_name");
      rd.include(request,response);

      diagram
      -------

      end user -----> client side app --request--> s1 Servlet <----include response of s2---- s2 Servlet
      					(response)						|											|
      						 |							|											|
      						 |							|											|
      						 |						(response 1)	         			     (response 2)
      						 |						    +
      						 |						(response 2)
      						 |						    +
      				         |______________________(response 3)


    Example of include() method
    ----------------------------

     //index.html
     <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<pre>
   <form action="FindBill">
     Current bill(units)  : <input type="text" name="cbill">
     Previous bill(units) : <input type="text" name="pbill">
     
                      <input type="submit" value="Calculate">
   </form>
</pre>

</body>
</html>

//FindBill.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FindBill
 */
@WebServlet("/FindBill")
public class FindBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindBill() {
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
         
        int cbill= Integer.parseInt(request.getParameter("cbill"));
        int pbill = Integer.parseInt(request.getParameter("pbill"));
          
         out.println("<h1>Bill Particulars</h1> <hr>");
         out.println("current month bill in units <br>"+cbill);
         out.println("previous month bill in units <br>"+pbill);
         
         request.setAttribute("unit_price", 3.5);
         
         RequestDispatcher rd = request.getRequestDispatcher("CalcBill");
         rd.include(request,response);
         out.print("<hr><h4>please pay bill by the end of the month</h4>");
	}

}


//CalcBill.java


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcBill
 */
@WebServlet("/CalcBill")
public class CalcBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcBill() {
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
         
        int cbill= Integer.parseInt(request.getParameter("cbill"));
        int pbill = Integer.parseInt(request.getParameter("pbill"));
        
        double unit_price = (double) request.getAttribute("unit_price");
        
        double camount = cbill*unit_price;
        double pamount = pbill*unit_price;
        double balance = camount - pamount;
        
        out.print("<h1>Amount particulars</h1>");
        out.println("current month bill amount <br>"+camount);
        out.println("previous month bill amount <br>"+pamount);
        out.print("<font color='green'>");
        out.print("total amount u have to pay "+balance);
        
        
	}

	
}

-------------------------------------------------------------------------------------------------------------
3.sendRedirect() method
-----------------------

  -> it is a method of HttpServletResponse , which is used to forward the control into any web component
     within/outside of web server.


     forward()  vs sendRedirect()
     ----------------------------
     forward()
     ---------
     	-> it forward the request & response to another web component.
     	-> it depends on user i.e if forward() wriiten within doGet() then in next Servlet
     	   it should be doGet().

     sendRedirect()
     --------------
     	-> it jumps to another webcomponent withinn same server or different
     	-> it always support doGet().


     	-----------------------------------------------
     	Example of sendRedirect()
     	------------------------------

     	//index.html
     	<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<pre>
            <h1>enter any kewyword to search</h1> <br> <hr>
<form action="Search">
     <input type="text" name="word">
     
     <input type="submit" value="search">
</form>
</pre>

</body>
</html>

//Search.java



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String word = request.getParameter("word");
		out.print("hello");
		response.sendRedirect("http://www.google.co.in/#q="+word);
		out.print("hello");
	}

}

-------------------------------------------------------------------------------------------------------------				         