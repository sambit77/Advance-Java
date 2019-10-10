//leftout cst1
/*SQL> create table student
  2  (
  3  name varchar2(10),
  4  roll number(4),
  5  cgpa number(3,1)
  6  );*/
import java.sql.*;
import java.util.*;

public class callable_Ex {
	
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		
		
		
		CallableStatement cst = con.prepareCall("(call proc_insert(?,?,?))");//create a procedure to 
		//insert record in database first with the name proc_insert 
		for(int i = 0 ; i < 3 ;i++)
		{
			System.out.println("enter cgpa");
			double cgpa = sc.nextDouble();
			System.out.println("enter roll");
			int roll = sc.nextInt();
			System.out.println("enter name");
			String name = sc.next();
			
			cst.setDouble(3, cgpa);
			cst.setInt(1, roll);
			cst.setString(2, name);
			
			cst.execute();
			System.out.println("record inserted successfully");
			
			
			
		}
		
		
		
	
	}

}

//same program using statement interface

