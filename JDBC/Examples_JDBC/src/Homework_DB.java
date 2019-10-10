import java.sql.*;
import java.util.*;

public class Homework_DB {
	static Scanner sc = new Scanner(System.in);
	static Connection con;
	static Statement st;
	static String tablename;
	static String finalsql;
	static String columnname;
	static String columndatatype;
	static String datatypesize;
	static boolean result = true;
	static boolean cont = true;
	
	public static void main(String[] args) 
	{
	 while(cont)
	 {
    System.out.println("Enter your choice");		
	System.out.println(" 1.create a table\n 2.add new columns\n 3.modify a column\n 4.rename a column\n 5.delete a column \n 6.Truncate a table\n 7.drop a table");
	int ch = sc.nextInt();
	switch (ch) 
	{
	case 1:
		System.out.println("enter the table name you want to create");
	    tablename = sc.next();
		System.out.println("enter no of columns");
		int size = sc.nextInt();
		String sql = "";
		for(int i = 0 ; i < size ; i++)
		{
			System.out.println("enter name for column "+i);
			columnname = sc.next();
			System.out.println("enter datatype for column "+columnname);
			columndatatype = sc.next();
			System.out.println("enter the size for column "+columnname);
			datatypesize = sc.next();
					/*
					 * System.out.println("enter constraint for column "+columnname); String cconst
					 * = sc.nextLine();
					 */
			if(i == size-1)
			{
				sql = sql.concat(columnname+" "+columndatatype+"("+datatypesize+")");
			} 
			else
			{
			sql = sql.concat(columnname+" "+columndatatype+"("+datatypesize+"),");
			}
		}
		finalsql= "create table "+tablename+"("+sql+")";
		break;
	case 2:
		System.out.println("enter the table name");
		tablename = sc.next();
		System.out.println("enter number of columns you want to add");
		int size2 = sc.nextInt();
		String sql2 = "";
		for(int k = 0 ; k < size2 ; k++)
		{
			System.out.println("enter the column name"+k);
			columnname = sc.next();
			System.out.println("enter the column datatype"+k);
			columndatatype = sc.next();
			System.out.println("enter the column size"+k);
			datatypesize = sc.next();
			if(k == size2-1)
			{
			sql2= sql2.concat(columnname+" "+columndatatype+"("+datatypesize+")");
			}
			else
			{
				sql2= sql2.concat(columnname+" "+columndatatype+"("+datatypesize+"),");
			}
		}
		finalsql ="alter table "+tablename+" add"+"("+sql2+")";
		break;
	case 3:
		System.out.println("enter the table name");
		tablename = sc.next();
		System.out.println("enter the name of the column you wanna modify");
		columnname = sc.next();   
		System.out.println("enter the new datatype for "+columnname);
		columndatatype = sc.next();
		System.out.println("enter new size for "+columnname);
		datatypesize = sc.next();
		finalsql = "alter table "+tablename+" modify("+columnname+" "+columndatatype+"("+datatypesize+"))";
		break;
	case 4:
		System.out.println("enter the table name");
		tablename = sc.next(); 
		System.out.println("enter the column name you wanna rename");
		String oldname = sc.next();
		System.out.println("enter new column name");
		String newname = sc.next();
		finalsql = "alter table "+tablename+" rename column "+oldname+" to "+newname;
		break;
	case 5:
		System.out.println("enter the tablename");
		tablename = sc.next();
		System.out.println("enter the column name you wanna delete ");
		columnname =sc.next();
		finalsql = "alter table "+tablename+" drop"+"("+columnname+")";
		break;
	case 6:
		System.out.println("enter the table name you wannna truncate");
		tablename  = sc.next();
		finalsql= "truncate table "+tablename;
		break;
	case 7:
		System.out.println("enter the table name yoou wanna drop ");
		tablename= sc.next();
		finalsql = "drop table "+tablename;
		break;
    default:
		System.out.println("Choice number does not exist");
		}
	    System.out.println(finalsql);
	    if(ch<=7)
	    {
	    try
	    {
	    con = Provider.getOracleConnection();
		st = con.createStatement();
	    result  = st.execute(finalsql);
	    if(!result)
	    {
	    	System.out.println("Operation successfull");
	    }
	    else
	    {
	    	System.out.println("Operation failed");
	    }
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Operation Failed");
	    	System.out.println(e);
//	    	e.printStackTrace();
	    }
	    }
	    System.out.println("do you want to continue ? yes or no");
	    String ans = sc.next();
	    if(ans.equalsIgnoreCase("no"))
	    {
	    	cont = false;
	    	System.out.println("program terminated");
	    }
	 }
	}
}


//to-do
//delete , update and insert from keyboard
