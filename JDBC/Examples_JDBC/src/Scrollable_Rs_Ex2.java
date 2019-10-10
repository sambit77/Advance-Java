import java.sql.*;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;
//left out cst2
public class Scrollable_Rs_Ex2 {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		String sql = "select 	roll,name, cgpa from student";
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		System.out.println(rs.getInt(1)+"\t"+ rs.getString(2)+"\t"+rs.getDouble(3));
//		
//		rs.updateString(2, "Deba");
//		rs.updateDouble(3, 9.9);
//		rs.updateRow();
		
		System.out.println("yes updated");
		
		rs.moveToInsertRow();
		rs.updateInt(1, 222);
		rs.updateString(2,"haha");
		rs.updateDouble(3, 10.0);
		rs.insertRow();
		
		System.out.println("Record Inserted ...............");
		
//		rs.absolute(3);
//		rs.deleteRow();
//		System.out.println("Record deleted");
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"\t"+ rs.getString(2)+"\t"+rs.getDouble(3));
		}
		System.out.println("enter row u want to delete");
		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		rs.absolute(row);
		rs.deleteRow();
		System.out.println("Record Deleted.......................");
		
		
		
		
		
		
	}
	
}

//display all the table name present in oracle server
//check a particular table existence from RS
//Query to fetch all the tables "select table_name from user_tables"

