import java.sql.*;
import java.util.Scanner;

public class Statement_Ex_KB {
	public static void main(String[] args) throws SQLException {
	
	  Connection con = Provider.getOracleConnection();
	  Statement st = con.createStatement();
	  String sql=null;
	  boolean result = false;
	  
	  Scanner sc = new Scanner(System.in);
	  
	  System.out.println("enter the table name you wanna drop");
	  
	  String table_name = sc.next();
	  
	  sql = "drop table "+table_name;
	  try
	  {
		  result = st.execute(sql);
		  System.out.println("table dropped successfully");
	  }
	  catch(Exception e)
	  {
//		  e.printStackTrace();
		  System.out.println("table does not exist");
	  }
	  finally
	  {
	  con.close();
	  }
	  
	 
	  
}
}

//Homework
//1.create a table 
//    a.name from keyboard
//    b.no of column & column name from keyboard
//    c. datatype from keyboard
//    d.size from keyboard
//    e.constraint from keyboard
//2.add new columns
//    a.no of columns from kb
//    b.column name from keyboard
//    c.datatype from kb
//    d.size from kb
//3.to modify the column
//    a.change the datatype
//        1.name of the column from kb
//        2.new datatype and new size
//4.to rename the column
//    a.old column name
//    b.new column name
//5.Truncate the table
//    a.table from kb
//6.drop the table
//    a.table name from kb


