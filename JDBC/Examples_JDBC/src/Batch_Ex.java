import java.sql.*;
public class Batch_Ex {
	public static void main(String[] args) throws Exception {
		Connection con =Provider.getOracleConnection();
		
		String sql = "insert into student values ('sharma',100,10.0)";
		String sql2 = "insert into student values ('sharmili',101,1.0)";
		String sql3 = "update student set name='xyz' where roll=900";
		
		Statement st = con.createStatement();
		st.addBatch(sql);
		st.addBatch(sql2);
		st.addBatch(sql3);
		
		int[] status = st.executeBatch();
		for(int s : status)
		{
			System.out.println(s);
		}
		
	}
	
	
	

}
