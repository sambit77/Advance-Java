//ResultSet MetaData
import java.sql.*;
public class RSMD_Ex 
{
	
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		
		Statement st = con.createStatement();
		String sql = "select * from student";
		ResultSet rs = st.executeQuery(sql);
		System.out.println("NAME"+"\t"+"ROLL"+"\t"+"CGPA" );
		
		while(rs.next())
		{
			System.out.println(rs.getString("name")+"\t"+rs.getInt("Roll")+"\t"+rs.getDouble("cgpa"));
		}
		
		System.out.println("_____________________________________");
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int no_cols =rsmd.getColumnCount();
		
		System.out.println("Number of columns in student table is  "+no_cols);
		
		for(int  i=1 ; i <= no_cols ; i++)
		{
			System.out.println(rsmd.getColumnLabel(i)+"\t"+rsmd.getColumnTypeName(i)+"\t"+rsmd.getColumnClassName(i));
		}
		
		
	}
	

}
