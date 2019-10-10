import java.sql.*;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;
//left out cst2
public class Scrollable_Rs_Ex {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		String sql = "select 	roll,name, cgpa from student";
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		
		ResultSet rs = st.executeQuery(sql);
		
		System.out.println("Forrward traversing.............");
		
		while(rs.next())
		{
		System.out.println(rs.getInt(1)+"\t"+ rs.getString(2)+"\t"+rs.getDouble("cgpa"));
		}
		
		System.out.println("Backward traversing........");
		
		while(rs.previous())
		{
			System.out.println(rs.getInt(1)+"\t"+ rs.getString(2)+"\t"+rs.getDouble("cgpa"));
		}
		
		System.out.println("--------------------");
		rs.absolute(2);
		System.out.println("Record present in second row");
		System.out.println(rs.getInt(1)+"\t"+ rs.getString(2)+"\t"+rs.getDouble("cgpa"));
		
		System.out.println(rs.getRow());
		
		System.out.println(rs.isFirst());
		System.out.println(rs.isLast());
		System.out.println(rs.isBeforeFirst());
		System.out.println(rs.isAfterLast());
		
		System.out.println("_________________________________");
		
		rs.first();
		System.out.println(rs.getRow());
		rs.last();
		System.out.println(rs.getRow());
		rs.beforeFirst();
		System.out.println(rs.getRow());
		rs.afterLast();
		System.out.println(rs.getRow());
		
		rs.absolute(3);
	//	rs.deleteRow(); not possible as the result set is read only type
		
		
		
		
		
		
	}
	
}

//display all the table name present in oracle server
//check a particular table existence from RS
//Query to fetch all the tables "select table_name from user_tables"

