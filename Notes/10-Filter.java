FILTER
-------
  -> Filter is a pre-defined interface in javax.servlet package
  -> Filter is used to do Filtering task like input vallidation,output rendering etc
  -> Filter is just like Servlet, it follows a lifecycle , the following are the lifecycle methods of Filter

       1.init()
       2.doFilter()  (analogous to service() of Servlet)
       3.destroy()


       Basic Skeleton of Filter
       ------------------------


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class Vallidate
 */
@WebFilter("/Vallidate")
public class Vallidate implements Filter {

    /**
     * Default constructor. 
     */
    public Vallidate() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

---------------------------------------------------------------------------------------------------------------
Example of Filter(Program)
--------------------------
create a new project -> Filter_Ex -> create index.html
create a package(lit) steps  Filter_Ex -> java resources -> src -> right click -> new -> package (lit)

then right click lit -> new -> filter -> Vallidate
then right clcik lit -> new -> Servlet -> Test


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
  <form action="lit.Test">
    Enter roll : <input type="text" name="roll">
    Enter age  : <input type="text" name="age">
    
    <input type="submit" value="go">
  </form>
</body>
</html>

//Vallidate.java (Filter)

package lit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class Vallidate
 */
@WebFilter("/lit.Test")
public class Vallidate implements Filter {

    /**
     * Default constructor. 
     */
    public Vallidate() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String roll = request.getParameter("roll");
		String age = request.getParameter("age");
		if(roll.isEmpty())
		{
			out.print("Roll cant be empty");
		}
		else if(age.isEmpty() ||  Integer.parseInt(age) < 0)
		{
			out.print("age can not be blank or negative");
		}
		else
		{
            out.print("<h3>");
			// pass the request along the filter chain
			chain.doFilter(request, response);
			out.print("</h3>");
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}


//Test.java (Servlet)

package lit;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test
 */
@WebServlet("/lit.Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
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
		String age = request.getParameter("age");
		
		out.print(roll);
		out.print(age);
		
	}

	

}
------------------------------------------------------------------------------------------------------------------------