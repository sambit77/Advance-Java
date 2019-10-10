import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;



public class Multi_Fetch {
public static void main(String[] args) throws Exception {
		
		Connection con = Provider.getOracleConnection();
		
		
		String sql = "select imgsrc from multi_table where id=?";
		java.sql.PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, 102);
		
		
		
		
		
		ResultSet rs  = pst.executeQuery();
		
		if(rs.next())
		{
			FileOutputStream fos = new FileOutputStream("sam.jpg");
			InputStream iis = rs.getBinaryStream(1);
			int ch;
			while((ch = iis.read() )!= -1)
			{
				fos.write(ch);
				System.out.println("Fetched Succesfully");
				fos.close();
				iis.close();
			}
		}
	}

}
