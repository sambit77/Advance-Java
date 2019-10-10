import java.sql.*;

public class Statement_Example {
	public static void main(String[] args) throws SQLException {

		Connection con = Provider.getOracleConnection();
		Statement st = con.createStatement();
		String sql = null;
		boolean result = false;

//	 
		/*
		 * sql="drop table course"; result=st.execute(sql);
		 * 
		 * System.out.println(result);
		 */
		int i = 1;
		int
		status = 0 ;
				
				if(i ==0)
				{

		 String sql1 =
		 "Create table Course(cid number(4) primary key, cname varchar2(20),cfees number(6,1))";
	     result = st.execute(sql1); System.out.println(result);
	

		// add a new column i.e email id and phone no

		String sqladd = ("alter table course add (EmailID varchar2(20),phoneno number(10))");
		result = st.execute(sqladd);
		System.out.println(result);

		// rename the table from course to java_course

		String sqlrename = "rename  course to java_course";
		result = st.execute(sqlrename);
		System.out.println(result);
			
		
		//change the datatype of   phone number from number to varchar2
		
		String sqlmodify = "alter table java_course  modify (phoneno varchar2(10))";
		result = st.execute(sqlmodify);
		System.out.println(result);
		
		//change the column name from email id to email
		
		String sqlcol = " alter table java_course rename column EmailID to email";
		result = st.execute(sqlcol);
		System.out.println(result);
		
		
		//delete phone no column 
		
		String sqldel = "alter table java_course drop(phoneno)";
		status = st.executeUpdate(sqldel);
		System.out.println(status);
			
				//truncate statement execution
				String sqlt = "truncate table java_course";
				result = st.execute(sqlt);
				System.out.println(result);
				}
		
		
		

	}
}

/*
 * homework 1. rename the table from course to jsvs_course. 2. change the
 * datatype of phoneno from number to varchar2. 3. change the column name from
 * emailid to email. 4. delete phone no column 5. execute the truncate statement
 * 
 */