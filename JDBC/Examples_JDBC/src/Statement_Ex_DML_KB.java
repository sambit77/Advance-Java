import java.sql.*;
import java.util.Scanner;

public class Statement_Ex_DML_KB {
	public static void main(String[] args) throws SQLException {
	
	  Connection con = Provider.getOracleConnection();
	  Statement st = con.createStatement();
	  String sql=null;
	int status =0;
	  
	  Scanner sc = new Scanner(System.in);
	  
	  sql = "insert into course values(100,'raja',5000,'R@GMAIL.COM',909090)";
	  status = st.executeUpdate(sql);
	  if(status>0)
	  {
		  System.out.println("Record inserted successfully");
	  }
	  else
	  {
		  System.out.println("Error in insertion ");
	  }
	  System.out.println("enter roll ");
	  int roll = sc.nextInt();
	  System.out.println("enter name");
	  String name = sc.next();
	  
	  
	  sql = "insert into course (roll,name) values("+roll+",'"+name+"')";
	  status = st.executeUpdate(sql);
	  if(status>0)
	  {
		  System.out.println("Record inserted successfully");
	  }
	  else
	  {
		  System.out.println("Error in insertion ");
	  }
	 
	  
	  
	  
	  
	  
	 
	  
}
}

//delete and update na insert from keyboard
