
/*SQL> create table student
  2  (
  3  name varchar2(10),
  4  roll number(4),
  5  cgpa number(3,1)
  6  );*/
import java.sql.*;
import java.util.*;

public class St_Ex_KB2_vspst {
	
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		
		
		
		Statement st = con.createStatement();
		for(int i = 0 ; i < 3 ;i++)
		{
			System.out.println("enter cgpa");
			double cgpa = sc.nextDouble();
			System.out.println("enter roll");
			int roll = sc.nextInt();
			System.out.println("enter name");
			String name = sc.next();
			
			String sql = "insert into student(cgpa,roll,name) values("+cgpa+","+roll+",'"+name+"')";
			
			
			int status = st.executeUpdate(sql);
			
			
			if(status>0)
			{
				System.out.println("inserted by pst ");
			}
			else
			{
				System.out.println("failed in inserting");
			}
		}
		
		
		
	
	}

}

//same program using statement interface

