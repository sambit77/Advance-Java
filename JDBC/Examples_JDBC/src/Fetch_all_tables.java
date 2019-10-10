import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class Fetch_all_tables {
 public static void main(String[] args) throws Exception {
	 Scanner sc = new Scanner(System.in);
	 Connection con = Provider.getOracleConnection();
	 String sql = "select table_name from user_tables";
	 
	 java.sql.Statement st  = con.createStatement();
	 
	 ResultSet rs = st.executeQuery(sql);
	  while(rs.next())
	  {
		  System.out.println(rs.getString(1));
	  }
	 
	 
	
}
}
