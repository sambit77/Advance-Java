import java.sql.*;
public class Transaction_Ex {
	public static void main(String[] args) throws Exception {
		Connection con =Provider.getOracleConnection();
		con.setAutoCommit(false);
		
		String sql1 = "insert into student values ('sharma',100,10.0)";
		String sql2 = "insert into student values ('sharmili',101,1.0)";
		String sql3 = "update student set name='xyz' where roll=100";
		
		Statement st = con.createStatement();
		 int status1 = st.executeUpdate(sql1);	
		 int status2 = st.executeUpdate(sql2);	
		 int status3 = st.executeUpdate(sql3);	
		 System.out.println(status1);
		 System.out.println(status2);
		 System.out.println(status3);
		 
		 if(status1 > 0 && status2 > 0 && status3 > 0)
		 {
			 con.commit();
			 System.out.println("Yes records committed................");
		 }
		 else
		 {
			 con.rollback();
			 System.out.println("Records are rollbacked");
		 }
		 con.close();
	}
	
	
	

}
