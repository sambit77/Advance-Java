import java.sql.*;

//Database Metadata example
public class DBMD_Ex 
{
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		DatabaseMetaData dbmd = con.getMetaData();
		
		System.out.println(dbmd.getDatabaseMajorVersion());
		System.out.println(dbmd.getDatabaseMinorVersion());
		System.out.println(dbmd.getDatabaseProductName());
		System.out.println(dbmd.getDatabaseProductVersion());
		
		System.out.println(dbmd.getMaxColumnNameLength());
		System.out.println(dbmd.getMaxColumnsInGroupBy());
		System.out.println(dbmd.getMaxColumnsInOrderBy());
		System.out.println(dbmd.getMaxColumnsInSelect());
		System.out.println(dbmd.getMaxTablesInSelect());
		System.out.println(dbmd.getMaxColumnsInIndex());
		System.out.println(dbmd.getMaxTableNameLength());
		
		
		System.out.println("________________________________________");
		
		Connection con1 = Provider.getMysqlConnection();
		DatabaseMetaData dbmd2 = con1.getMetaData();
		
		System.out.println(dbmd2.getDatabaseMajorVersion());
		System.out.println(dbmd2.getDatabaseMinorVersion());
		System.out.println(dbmd2.getDatabaseProductName());
		System.out.println(dbmd2.getDatabaseProductVersion());
		
		System.out.println(dbmd2.getMaxColumnNameLength());
		System.out.println(dbmd2.getMaxColumnsInGroupBy());
		System.out.println(dbmd2.getMaxColumnsInOrderBy());
		System.out.println(dbmd2.getMaxColumnsInSelect());
		System.out.println(dbmd2.getMaxTablesInSelect());
		System.out.println(dbmd2.getMaxColumnsInIndex());
		System.out.println(dbmd2.getMaxTableNameLength());
		
	}

}
