import java.sql.*;

import org.apache.commons.dbcp.BasicDataSource;
public class BDS_Faster {
	
	public static void main(String[] args) throws Exception{
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.OracleDriver";
		String username = "system";
		String password= "lit";
		
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(driver);
		bds.setUrl(url);
		bds.setUsername(username);
		bds.setPassword(password);
		
		
		for(int i = 0 ; i <= 50 ; i++)
		{
			Connection con = bds.getConnection();
			System.out.println(con.hashCode());
			con.close();
		}
		System.out.println("__________________________________");
		for(int i = 0 ; i <= 50 ; i++)
		{
			Connection con = DriverManager.getConnection(url,username,password);
			System.out.println(con.hashCode());
			con.close();
		}
		

}
}
  /*
   Connection Pooling
   ------------------
   
    BasicDataSource is used to reuse the connection Object.
    
    In case of BDS ,for the first time user request , connection object will be created and stored in cache 
    memory i.e connection pool and second request onwards , the existing connection object will be 
    available
    
    In case of DriverManager.getConnection(), for each user request , a new connection object will be created
    
    Compare to DriverManager , BasicDataSource is faster
   
   */