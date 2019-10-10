import java.sql.*;

public class Provider {
	public static Connection getOracleConnection()
	{
		Connection con = null;
		try
		{ 
			Class.forName("oracle.jdbc.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","lit");
					
		}
		catch (Exception e) {
			
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static Connection getMysqlConnection()
	{
		Connection con = null;
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","litbbsr");
					
		}
		catch (Exception e) {
			
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return con;
	}
	public static void main(String[] args) {
		
		Connection con_ora = Provider.getOracleConnection();
		Connection con_mysql =Provider.getMysqlConnection();
		
		System.out.println(con_ora);
		System.out.println(con_mysql);
	}


}
/*
 How to set the .jar class path in eclipse
 ---------------------------------------------
 
 JRE System Library (in project) -> Right click -> build path -> configure build path -> libraries ->
 select class path ->  Add external jar file -> select the jar file(browse) -> apply -> apply and close
 
 Note : check in referenced library
 
  */
