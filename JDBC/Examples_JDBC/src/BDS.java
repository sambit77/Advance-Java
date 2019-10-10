import java.sql.*;

import org.apache.commons.dbcp.BasicDataSource;
public class BDS {
	
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
		
		Connection con = bds.getConnection();
		if(con != null)
		{
			System.out.println("connected with ORACLE using BasicDataSource");
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