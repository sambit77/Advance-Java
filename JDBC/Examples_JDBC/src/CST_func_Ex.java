import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;
//left out cst2
public class CST_func_Ex {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		
		
		
		CallableStatement cst = con.prepareCall("{?=call Func_insert(?,?,?)}");//create a procedure to 
		//insert record in database first with the name Func_insert 
			System.out.println("enter cgpa");
			double cgpa = sc.nextDouble();
			System.out.println("enter roll");
			int roll = sc.nextInt();
			System.out.println("enter name");
			String name = sc.next();
			
			cst.setDouble(4, cgpa);
			cst.setInt(2, roll);
			cst.setString(3, name);
			cst.registerOutParameter(1, Types.INTEGER);
			
			cst.execute();
			System.out.println("record inserted successfully");
			System.out.println("No of recors in table is"+cst.getInt(1));
			
			}
	
}

//code for function in pl/sql in sql commandline
/*
 * Create or replace Function Func_insert (r number, n varchar2,c number) return
 * number is cnt number(4); begin insert into student values(n,r,c); select
 * count(*) into cnt from student; return cnt; end Func_insert; /
 */

//work at home
//1. create a PL/SQL procedure which will delete the record based on roll
//2. create a PL/SQL procedure which will update record based on roll
//3. create a PL/SQL procedure which will return the details of student based on roll no
//4  create a PL/SQL function which will return the maximum & minimum cgpa of student


