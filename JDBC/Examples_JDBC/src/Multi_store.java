import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

public class Multi_store {
public static void main(String[] args) throws Exception {
		
		Connection con = Provider.getOracleConnection();
		
		
		String sql = "insert into multi_table values (?,?)";
		java.sql.PreparedStatement pst = con.prepareStatement(sql);
		
		FileInputStream fis = new FileInputStream("g.jpg");
		
		pst.setInt(1, 102);
		pst.setBinaryStream(2,fis,fis.available());
		
		int status = pst.executeUpdate();
		
		if(status > 0)
		{
			System.out.println("Multi media inserted successfully");
		}
		else
		{
			System.out.println("Error in insertion");
		}
		
	}
	

}
