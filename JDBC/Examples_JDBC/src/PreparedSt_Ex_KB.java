
/*SQL> create table student
  2  (
  3  name varchar2(10),
  4  roll number(4),
  5  cgpa number(3,1)
  6  );*/
import java.sql.*;
import java.util.*;

public class PreparedSt_Ex_KB {
	
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		
		String sql = "insert into student(cgpa,roll,name) values(?,?,?)";
		
		PreparedStatement pst = con.prepareStatement(sql);
		for(int i = 0 ; i < 3 ;i++)
		{
			System.out.println("enter cgpa");
			double cgpa = sc.nextDouble();
			System.out.println("enter roll");
			int roll = sc.nextInt();
			System.out.println("enter name");
			String name = sc.next();
			
			pst.setDouble(1, cgpa);
			pst.setInt(2, roll);
			pst.setString(3, name);
			int status = pst.executeUpdate();
			
			
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
//All the DDL operation using PrpearedStatement
//using PReparedStatement do update and delete opertion with and without keyboard input

