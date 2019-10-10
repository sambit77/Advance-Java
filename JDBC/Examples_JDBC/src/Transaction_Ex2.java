import java.sql.*;
public class Transaction_Ex2 {
	public static void main(String[] args) throws Exception {
		Connection con =Provider.getOracleConnection();
		con.setAutoCommit(false);
		
		String sql1 = "insert into student values ('sharma',100,10.0)";
		String sql2 = "insert into student values ('sharmili',101,3.0)";
		String sql3 = "insert into student values ('yoyo',102,7.0)";
		String sql4 = "insert into student values ('mika',103,8.0)";
		String sql5 = "insert into student values ('shirley',104,9.0)";
		
		
		Statement st = con.createStatement();
		 int status1 = st.executeUpdate(sql1);	
		 int status2 = st.executeUpdate(sql2);	
		 Savepoint sp1 = con.setSavepoint();
		 int status3 = st.executeUpdate(sql3);
		 Savepoint sp2 = con.setSavepoint();
		 int status4 = st.executeUpdate(sql4);
		 int status5 = st.executeUpdate(sql5);
		 System.out.println(status1);
		 System.out.println(status2);
		 System.out.println(status3);
		 System.out.println(status4);
		 System.out.println(status5);
		 
		 con.rollback(sp2);
		 System.out.println("Roll back up to sp2...........");
		 
		 con.rollback(sp1);
		 System.out.println("ROll back to sp1");
		 
		 con.commit();
		 System.out.println("committedd");
		 
		 con.close();//it automatically commits................
		 
	
		 }
		 
	}
	
	
	


