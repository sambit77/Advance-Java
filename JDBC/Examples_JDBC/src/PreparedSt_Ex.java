
/*SQL> create table student
  2  (
  3  name varchar2(10),
  4  roll number(4),
  5  cgpa number(3,1)
  6  );*/
import java.sql.*;
public class PreparedSt_Ex {
	
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		
		String sql = "insert into student(cgpa,roll,name) values(?,?,?)";
		
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setDouble(1, 8.8);
		pst.setInt(2, 100);
		pst.setString(3, "rani");
		
		int status = pst.executeUpdate();
		System.out.println(status);
		if(status>0)
		{
			System.out.println("inserted ");
		}
		else
		{
			System.out.println("failed");
		}
	}

}
