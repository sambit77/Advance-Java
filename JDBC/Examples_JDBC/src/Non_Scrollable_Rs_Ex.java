import java.sql.*;
import java.sql.Connection;
import java.sql.Types;
import java.util.Scanner;
//left out cst2
public class Non_Scrollable_Rs_Ex {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		String sql = "select name , roll, cgpa from student";
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next())
		{
		System.out.println(rs.getString(1)+"\t"+rs.getDouble("cgpa")+"\t"+rs.getInt(2));
		}
		
		
		
		
	}
	
}

//display all the table name present in oracle server
//check a particular table existence from RS
//Query to fetch all the tables "select table_name from user_tables"

