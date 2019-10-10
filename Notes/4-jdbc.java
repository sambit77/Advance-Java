JDBC-
---------------------------
-> it is a technique which is used to communicate to database server from java application.
-> c/c++ uses ODBC (open database connectivity) for DB communication.
-> .net uses ADO(activex data object) for database communication.
->  .php uses PDO(php data object) for database communication.

---------------------------------------------------------------

java language -> JDBC |

c/C++ language -> ODBC  |
                              }->>> DATABASE SERVER  --(oracle,mysql,db2,sybase)
.net -> ADO             |

.php ->  PDO          |

--------------------------------------------------------------------

why not odbc in java?

- product of microsoft
- purely developed in c
- java does not support the concept of pointer
- internal of odbc is pointer

-------------------------------------------------

ARCHITECTURE OF JDBC
----------------------
   |application layer|                           |physical layer|

java application -> JDBC API  ->  {oracle driver/mysql driver/db2 driver} -> {oracle database,mysql database,db2 database}

----------------------------------
Questions ->

1.how to create user-defined api(javadoc tool)?
2.how to create a .jar file?what is jar? Advanatages of jar.
3.how to create a .exe file?
4.how to create a setup file?
----------------------------------

API
----
it is a collection of classes and interfaces.

JDBC-API
--------
it contains all the classes and interfaces related to database operations.

sql package
------------
  -> it is a sub package of java or javax , there are no difference between java.sql and javax.sql
  -> classes and interfaces related to JDBC-API present in java.sql or javax.sql package.

JDBC driver
-------------

->Driver is a predefined class
  Example-
     OracleDriver - Driver class for oracle database.
     Driver - Driver class for Mysql database.
     Db2JCCDriver - Driver class for DB2 database.

 ->Driver class is used as a middle-ware between JDBC-API and database server.
 -> in otherwards , the driver class is responsible to convert java code into database understandable format
    and vice versa.

    TYPES OF DRIVER 
    ----------------
    1.Type-1(Jdbc-Odbc Bridge Driver)
    2.Type-2(Native API partly java driver)
    3.Type-3(Network protocol pure java driver)
    4.Type-4(Thin Driver)
    5.Type-5(Data Direct Driver)

-------------------------------------------------------------------------

1.Type-1(Jdbc-Odbc Bridge Driver)

  -> it is purely developed in c language.
  -> it is not secure.
  -> it is removed from latest jdk(i.e after jdk 1.6).
  -> here JDBC-API interact with odbc software for database communication.

  ARCHITECTURE
  ------------

  java application -> JDBC-API -> ODBC software -> DATABASE
                                   ->c language
                                   ->microsoft product
                                   ->require a DSN  for connection.

  ->by type-1 driver it is possible to communicate with all the database server using a single driver class.
  ->Driver class Name
      sun.jdbc.odbc.JdbcOdbcDriver  (present only in jdk 1.6)

      (rt.jar) in jre contains all the standard packages
---------------------------------------------------------------------------------------------------------------

steps to create a jdbc application
-----------------------------------
1.load the driver
2.establish the connection
3.create a request object
4.Process the request.
5.close the connection


1.load the driver
-------------------

syntax: Class.forName("driver_class_name");

forName() -> it is used to load a class into JVM.

  various ways to create an object in java

  1.new keyword
     Employee obj = new Employee();
  2.newInstance() method
     ->will study in next section.
  3.clone() method
     Student s1 = new Student();
     Student s2 =(Student) s1.clone();
  4.factory method
      refer core java notes.

----------------------
creating object by newinstance method 

Example:-
class A
{
	A()
	{
		System.out.println("A class constructor");
	}
}
class B
{
	B()
	{
		System.out.println("B class constructor");
	}
}
class fname
{
	public static void main(String[] args) throws Exception{
		Class c1 = Class.forName("A");
		Class c2 = Class.forName("B");

		c1.newInstance();
		c2.newInstance();
	}
}

output:-
javac fname.java
java fname
A class constructor
B class constructor

Note:-
-> CLass: it is predefined class present in java.lang package ,it provides design information
  
  Q.find out all the variables ,methods,constructors present in a class.

  Ans -  refre core java copy.

-> newInstance() : create the object of that class ,which is loaded into JVM.

-------------------------------------------------------------
Driver can be loaded by 3 different ways

->forName() method
-> registerDriver() method
-> system properties

set path v/s  set classpath
---------------------------
set path is used to set the path of .exe file
set classpath is used to set the path of .jar file

Program
//example to load type-1 driver by forname()   method 
import java.sql.*;
import java.util.*;
class A
{
	public static void main(String[] args)  throws Exception
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Enumeration e = DriverManager.getDrivers();
		while(e.hasMoreElements())
			System.out.println(e.nextElement());
		System.out.println("Type 1 driver loaded by forName() method ");
	}
}

output:
E:\Storage\AdvanceJava\Notes@Autumn>set path=;C:\Program Files\Java\jdk1.6.0_45\bin;

E:\Storage\AdvanceJava\Notes@Autumn>javac type1load.java

E:\Storage\AdvanceJava\Notes@Autumn>java A
sun.jdbc.odbc.JdbcOdbcDriver@1ee7b241
Type 1 driver loaded by forName() method

-----------------------------------------------------------------------
Note:

forName() it is used to load the class  
getDrivers() it is a static method of DriverManager class which is used to return drivers list.
Enumeration it is an intreface which is used to traverse the collection
hasMoreElements() it checks next element existence and returns true or false.
nextElement() it returns the list element & move the reference to next element .

-------------
Program
//example to load driver by register driver method
import java.sql.*;
import java.util.*;
class A
{
	public static void main(String[] args)  throws Exception
	{
		sun.jdbc.odbc.JdbcOdbcDriver obj = new sun.jdbc.odbc.JdbcOdbcDriver();
		DriverManager.registerDriver(obj);
		Enumeration e = DriverManager.getDrivers();
		System.out.println(e.nextElement());
		System.out.println("Type 1 Driver loaded by registerDriver()");
	}
}

output
------

E:\Storage\AdvanceJava\Notes@Autumn>set path=;C:\Program Files\Java\jdk1.6.0_45\bin;

E:\Storage\AdvanceJava\Notes@Autumn>java A
sun.jdbc.odbc.JdbcOdbcDriver@7d67d940
Type 1 Driver loaded by registerDriver()

-------------------------------------------------------------------------------

//loading the driver by system properties 
import java.sql.*;
import java.util.*;
class A
{
	public static void main(String[] args)  throws Exception
	{
		
		Enumeration e = DriverManager.getDrivers();
		System.out.println(e.nextElement());
		System.out.println("Type 1 Driver loaded by system properties");
	}
}

output:
E:\Storage\AdvanceJava\Notes@Autumn>set path=;C:\Program Files\Java\jdk1.6.0_45\bin;

E:\Storage\AdvanceJava\Notes@Autumn>java A -Djdbc.Driver=sun.jdbc.odnc.JdbcOdbcDriver
sun.jdbc.odbc.JdbcOdbcDriver@5e743399
Type 1 Driver loaded by system properties

Note:
-D<name>=<value> : set a system property.

---------------------------------------------------------------------------------------------------------------
2.establish the connection
--------------------------

 it is possible by 3 ways
 --------------------------
   1.getConnection(String DB_URL)
   2.getConnection(String DB_URL,String username,String password)
   3.getConnection(String DB_URL,Properties object )


   synatx:

   Connection con = DriverManager.getConnection(String DB_URL);
   String DB_URL  = jdbc:odbc:dsn_name
   getConnection() : it is a static method of DriverManager class which is used to establish the connection 
                     with Db and return connection object.


---------------------------------------------------------------------
Connecting to MS-ACCESS(  1.getConnection(String DB_URL) example)

1.how to create DSN for MS-ACCESS

control panel -> Adminstrative  tools -> select odbc data source (32/64) -> system DSN -> Add a new Driver
-> select the database driver(.mdb) -> finish -> create a new database -> select the drive -> select the folder
-> give the database name -> ok(2) -> give data source name-> (dsn_access_home) -> ok(2)

//program to connect with MS-ACCESS using type-1 driver

import java.sql.*;
class  A
{
	public static void main(String[] args)  throws Exception
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		String url = "jdbc:odbc:dsn_access_home";
		Connection con = DriverManager.getConnection("url");
		System.out.println(con);
	}
}
---------------------------------------------------------------------
connecting  to oracle(2.getConnection(String DB_URL,String username,String password) example)
------=-
how to create DSN for oracle

control panel -> Adminstrative tools-> select odbc data source(32/64) -> system DSN -> Add a new Driver ->
select the db driver -> Oracle XE -> finish -> give a DSN name (dsn_oracle_home) -> give TNS service name(XE)
-> give user id (system)  -> test connection ->give password(lit)  -> ok(4)


//program to connect to oracle database using type1 driver
import java.sql.*;

class Demo
{
	public static void main(String[] args)  throws Exception
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con = DriverManager.getConnection("jdbc:odbc:dsn_oracle_home","system","lit");
		if(con!= null)
		{
			System.out.println(con);
			System.out.println("connected o oracle using type 1 driver");
		}
	}
}

output:
-------
E:\Storage\AdvanceJava\Notes@Autumn>set path=;C:\Program Files\Java\jdk1.6.0_45\bin;

E:\Storage\AdvanceJava\Notes@Autumn>javac demo.java

E:\Storage\AdvanceJava\Notes@Autumn>java Demo
sun.jdbc.odbc.JdbcOdbcConnection@e05d173
connected o oracle using type 1 driver

------------------------------------------------------------
connecting  to oracle(3.getConnection(String DB_URL,Properties object ) example)
->this approach is suitable to store more than one database details
---------------------
-> Properties
   -> create a property file db.properties  (in the same location as that of .java file)
       //db.properties
       url=jdbc:odbc:dsn_oracle_home
       driver=sun.jdbc.odbc.JdbcOdbcDriver
       user=system
       password=lit

 ->java program

 //example to connect to oracle database using property class and type1 driver
import java.sql.*;
import java.util.*;
import java.io.*;
class Demo
{
	public static void main(String[] args)  throws Exception
	{
		Properties p = new Properties();
		p.load(new FileInputStream("db.properties"));
		Class.forName(p.getProperty("driver"));
		Connection con = DriverManager.getConnection(p.getProperty("url"),p);
		if(con != null)
		{
			System.out.println(con);
			System.out.println("connected ");
		}
	}
}

output:
E:\Storage\AdvanceJava\Notes@Autumn>set path=;C:\Program Files\Java\jdk1.6.0_45\bin;

E:\Storage\AdvanceJava\Notes@Autumn>javac demo.java

E:\Storage\AdvanceJava\Notes@Autumn>java Demo
sun.jdbc.odbc.JdbcOdbcConnection@1ff9dc36
connected

-----------------------------------------------------------------------------------------------------------------------
2.Type-2 Driver(Native API partially java driver)

-> Native Application : if the application is responsible to communicate java language with other environment like
   c/c++ , i.e native application.

-> this driver is partially written in java.
->to communicate with database server here native libraries are required.

ARCHITECTURE
------------
java application -> JDBC-API -> Native libraries  -> database

-> here .jar file is required to communicate with database server.
-> Driver class to communicate with oracle
    oracle.jdbc.pool.OracleDataSource
-> oracle.jdbc.pool package is present in ojdbc6.jar/ojdnc6_g.jar/ojdbc5.jar...etc 
->this .jar file is present in following location
   C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib

-> java program
  //example to connect with oracle database using type-2 driver
import java.sql.*;
class Demo
{
	public static void main(String[] args)  throws Exception
	{
		Class.forName("oracle.jdbc.pool.OracleDataSource");
		Connection con = DriverManager.getConnection("jdbc:oracle:oci:@localhost:1521:xe","system","lit");
		if(con != null)
		{
			System.out.println("connected to oracle using type2");
			System.out.println(con);
		}
	}
}
--do not forget to run cmd in adminstrator mode
/*output:*/
E:\Storage\AdvanceJava\Notes@Autumn>set classpath=;C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6_g.jar;

E:\Storage\AdvanceJava\Notes@Autumn>javac Demo.java

E:\Storage\AdvanceJava\Notes@Autumn>java Demo
connected to oracle using type2
oracle.jdbc.driver.T2CConnection@3dd4520b

Note:-
"jdbc:oracle:oci:@localhost:1521:xe","system","lit"
  
  lit : password of oracle
  system : username of oracle
  xe : database name
  1521 : port no of oracle  // in some systems the port no may be 1522
  localhost : Database server is running in local machine
  jdbc:oracle:oci  database url for orcale in type-2

-----------------------------------------------------------------------------------------------------------------------
Type-3(Network Protocol Pure Java Driver)

->here application server is required to communicate with database server
-> Name of the application server is IDS
-> Application server is responsible to communicate with database server with the help of database 
   specific jar file
-> Java application communicate with ids (application server) with the help of application specific .jar file.
    i.e jdk14drv.jar
-> this .jar file present in c:\IDSServer\classes location
-> an application server play the role of middle-ware, this driver is also popularly known as 
   middle-ware / third party jdbc driver

   ARCHITECTURE
   ------------
   java application -> JDBC-API -> Application Serrver -> DATABASE server

-> it is purely written in java , known as pure java driver.

-> java program
 //example to connect with access using  type 3 driver
import java.sql.*;
class Demo
{
	public static void main(String[] args)  throws Exception
	{
	  Class.forName("ids.sql.IDSDriver");
	  Connection con = DriverManager.getConncetion("jdbc:ids://localhost:12/conn?dsn=dsn_access_home");
	  if(con != null)
	  {
	  	System.out.println("connected to access db using type 3");
	  	System.out.println(con);
	  }	
	}
}
//install IDS server applicatiob first and also create a DSN if does not exists
output:
set classpath=;C:\IDSServer\classes\jdk14drv.jar;
javac Demo.java
java Demo
connected to access db using type 3
ids.sql.IDSConnection@3f91bc4

Note:

jdbc:ids://localhost:12/conn?dsn=dsn_access_home

jdbc:ids url to connect with ids server
localhost ids server is running in localmachine
conn? specify url
dsn_access_home system dsn fro MS-ACCESS
----------------------------------------------------------------------------------------------------
Type-4 Driver(thin driver)
--------------------------
-> this driver is most popular jdbc driver.
-> here no application server is required as well as no odbc software is required.
-> here java application directly communicate with database Server without any extra software
   so it is faster
-> only .jar file is required like type 2
-> this driver is purely written in java so secure.
-> all most all databases can be connected by type 4 driver.

ARCHITECTURE
-------------
java application -> JDBC-API -> DATABASE server
    
    for oracle we need ojdbc14.jar
    	for mysql we need mysql-connector.jar
-> java program
//examp[le to connect with oracle database using type4 driver
import java.sql.*;
class Demo
{
	public static void main(String[] args)  throws Exception
	{
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection con = DriverManager.getConnection(url,"system","lit");
		if(con != null)
		{
			System.out.println(con);
			System.out.println("connected to oracle using type 4");
		}


	}
}
output:
E:\Storage\AdvanceJava\Notes@Autumn>set classpath=;E:\Storage\AdvanceJava\J2EE\JAR\ojdbc14.jar;

E:\Storage\AdvanceJava\Notes@Autumn>java Demo
oracle.jdbc.driver.T4CConnection@33e5ccce
connected to oracle using type 4

-> java program
//examp[le to connect with mysql database using type4 driver
import java.sql.*;
class Demo
{
	public static void main(String[] args)  throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/sam";
		Connection con = DriverManager.getConnection(url,"litbbsr","litbbsr");
		if(con != null)
		{
			System.out.println(con);
			System.out.println("connected to mysql using type 4");
		}


	}
}
//do not forget to create a database sam
   win -> client
   select MYSQL client -> enter the password (litbbsr)
   query -> create database sam;
         -> show databases; //lists all databases under that user
output:
Doubt
Error

E:\Storage\AdvanceJava\Notes@Autumn>set classpath=;E:\Storage\AdvanceJava\J2EE\JAR\mysql-connector-java-5.1.22-bin.jar;

E:\Storage\AdvanceJava\Notes@Autumn>javac demo.java

E:\Storage\AdvanceJava\Notes@Autumn>java Demo
Exception in thread "main" java.sql.SQLException: Access denied for user 'litbbsr'@'localhost' (using password: YES)
        at com.mysql.jdbc.SQLError.createSQLException(SQLError.java:1074)
        at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:4096)
        at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:4028)
        at com.mysql.jdbc.MysqlIO.checkErrorPacket(MysqlIO.java:919)
        at com.mysql.jdbc.MysqlIO.proceedHandshakeWithPluggableAuthentication(MysqlIO.java:1694)
        at com.mysql.jdbc.MysqlIO.doHandshake(MysqlIO.java:1244)
        at com.mysql.jdbc.ConnectionImpl.coreConnect(ConnectionImpl.java:2412)
        at com.mysql.jdbc.ConnectionImpl.connectOneTryOnly(ConnectionImpl.java:2445)
        at com.mysql.jdbc.ConnectionImpl.createNewIO(ConnectionImpl.java:2230)
        at com.mysql.jdbc.ConnectionImpl.<init>(ConnectionImpl.java:813)
        at com.mysql.jdbc.JDBC4Connection.<init>(JDBC4Connection.java:47)
        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
        at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500)
        at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481)
        at com.mysql.jdbc.Util.handleNewInstance(Util.java:411)
        at com.mysql.jdbc.ConnectionImpl.getInstance(ConnectionImpl.java:399)
        at com.mysql.jdbc.NonRegisteringDriver.connect(NonRegisteringDriver.java:334)
        at java.sql/java.sql.DriverManager.getConnection(DriverManager.java:677)
        at java.sql/java.sql.DriverManager.getConnection(DriverManager.java:228)
        at Demo.main(demo.java:9)

E:\Storage\AdvanceJava\Notes@Autumn>
------------------------------------------------------------------------------------------------------
Type-5 Driver

-> this is the latest JDBC driver.
-> here .jar file related to all the database is included.
->this driver is not free and open source.
->it is provide by progress company.

Note:
  -> all the driver class names ,database urls are pre written in  a file config.txt
     which is presnet in following directory.

     C:\Program Files\Progress\DataDirect\Connect_for_JDBC_51 -> search for config.txt file

     Example:
     --------
     //connecting to oracle database using type 5 driver
import java.sql.*;
class Demo
{
	public static void main(String[] args) throws Exception
	{
		Class.forName("com.ddtek.jdbc.oracle.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:datadirect:oracle://localhost:1521;SID=XE","system","lit");
		if(con != null)
		{
			System.out.println(con);
			System.out.println("connected to oracle using type5 driver");
		}
	}
}
Note :
to execute the .java file we need to set classpath for oracle.jar after istalling the software progress
output
------
\Notes@Autumn>set classpath=;C:\Program Files\Progress\DataDirect\Connect_for_JDBC_51\lib\oracle.jar;

\Notes@Autumn>javac demo.java

\Notes@Autumn>java Demo

----------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------
=======================SWITCHING TO ECLIPSE IDE==============================================

how to create/switch worksapce in ECLIPSE

eclipse -> file -> switch workspace -> other -> *your workspace name*

creating a new java project in eclipse

*your workspace name* -> create new project -> java -> give name (JDBC_EXMAPLES)
->src -> right click -> new class -> *your class name*
----------------------------------------------------------------------------------------------
ECLIPSE PROGRAMS
----------------
1.Provider.java (it retruns connection object for Oracle and MYsql Connection)
---------------------------------------------------------------------------------
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
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
		}
		return con;
	}
	public static Connection getMysqlConnection()
	{
		Connection con = null;
		try
	    {
		Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","litbbsr","litbbsr");
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
		}
		return con;
	}
  public static void main(String[] args) throws Exception 
  {
	Connection con = Provider.getOracleConnection();
	System.out.println(con);
	Connection con_mysql = Provider.getMysqlConnection();
	System.out.println(con_mysql);
  }
}
->for executing provider.java we need two jar files i.e ojdbc14.jar and mysql-connector.jar
  HOW TO SET THE .JAR CLASSPATH IN ECLIPSE
  ----------------------------------------

  JRE system library (in project)-> right click -> build path -> configure build path -> libraries
  -> classpath(present at the bottom) ->add external .jar file -> apply -> apply and close

  Note :- to cross check if .jar file is added or not check it in referenced library in project folder.

output:
oracle.jdbc.driver.T4CConnection@5f150435
java.sql.SQLException: Access denied for user 'litbbsr'@'localhost' (using password: YES)
null //doubt ? not getting a connection object for mysql database?

------------------------------------------------------------------------------------------------------
CONNECTION POOLLING
-------------------
 ->BasicDataSource is used to reuse the connection object.
 ->in case of BDS,for the first time user request , connection object will be created and stored in
   cache memory i.e connection pool and second request onwards , the existing connection object
   will be available.
 ->in case of DriverManager.getConnection(),for each user request a new connection
   object will be created.
 ->compare to DriverManager , BasicDataSource is faster.

 2.BDS.java (BasicDataSource)
 ----------------------------
 import java.sql.*;

import org.apache.commons.dbcp.BasicDataSource;

public class BDS {
public static void main(String[] args) throws  Exception 
{
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "system";
	String password = "lit";
	String driver = "oracle.jdbc.OracleDriver";
	
	BasicDataSource bds = new BasicDataSource();
	bds.setDriverClassName(driver);
	bds.setUrl(url);
	bds.setUsername(username);
	bds.setPassword(password);
	
	Connection con = bds.getConnection();
	if(con != null)
	{
		System.out.println("connected to oracle using basic data source");
	}
	
}
}

Note:
->to use BasicDataSource following .jar file is required
     1 commons-pool-1.5.4.jar  & 2 org.apache.commomns.dbcp.jar

 output
 ------
 connected to oracle using basic data source
-----------------------------------------------------------------------------------------------
3/program to show the difference between creating a connection object using BasicDataSource 
  and DriverManager s getConnection() method.

import java.sql.*;

import org.apache.commons.dbcp.BasicDataSource;

public class BDSFaster {
public static void main(String[] args) throws  Exception 
{
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "system";
	String password = "lit";
	String driver = "oracle.jdbc.OracleDriver";
	
	BasicDataSource bds = new BasicDataSource();
	bds.setDriverClassName(driver);
	bds.setUrl(url);
	bds.setUsername(username);
	bds.setPassword(password);
	for(int i = 0 ; i<4 ;i++)
	{
	    Connection con= bds.getConnection();
		System.out.println(con.hashCode());
		con.close();

	}
	System.out.println("_______________________________");
	for(int i = 0 ; i<4 ;i++)
	{
        Connection  con =Provider.getOracleConnection();
        System.out.println(con.hashCode());
		con.close();

	}
	
}
}

output:
-------
240650537
240650537
240650537
240650537
_______________________________
2088051243
1277181601
41903949
488970385

Note:
the above program output is 
-> the first for loop prints a single hashcode 5 times , hence in BDS method Connection
   object is reused ,while the 2nd for loop prints 5 different hashcodes.
->  there is  a limit of creating connection to database at a time i.e if we run for loop for 
   100 times ,it creates connection only for fixed number of times and on further request for
   connection it throws Error.
-------------------------------------------------------------------------------------------------------
3.create a request object.
   Request object can be creeated by 3 ways:-
     1.Using Statement interface
     2.Using PreparedStatement interface.
     3.Using CallableStatement interface.

    1.Using Statement interface
    ---------------------------

    ->this interface is used to process the static request.
        Statement st = con.createStatement(); 

     2.Using PreparedStatement interface
    ---------------------------

    ->this interface is used to process the dynamic request.
        PreparedStatement pst = con.prepareStatement(String query);

     1.Using CallableStatement interface
    ---------------------------

    ->this interface is used to process the PL/SQL program
        CallableStatement cst = con.prepareCall(String expression)
-----------------------------------------------------------------------------------------------------
4.process the request
   
   ->JDBC provides 3 pre-defined methods to execute the request.
       1. execute() : to execute DDL Statement (create,alter,drop,rename,truncate)
       2. executeUpdate() : to execute DML Statement (insert,update,delete)
       3. executeQuery() : to execute DQL Statement (select)

   ->SYNTAX to process the request generated by Statement interface
        boolean result = con.execute(String DDL_Statement);
        int status = con.executeUpdate(String DML_Statement);
        ResultSet rs = con.executeQuery(String select_statement);


   ->SYNTAX to process the request generated by PreparedStatement interface
        boolean result = con.execute();
        int status = con.executeUpdate();
        ResultSet rs = con.executeQuery();

   ->SYNTAX to process the request generated by CallableStatement interface
        cst.execute();


2.PreparedStatement
   
   -> it is used to process dynamic request.
       Ex:GUI application with database.

   ->PreparedStatement always process pre-compilled query.
   -> pre-compilled : for tthe first user request , SQL query will be compilled but next
                      request onwards only value will be submitted.


    Statement v/s PreparedStatement
    -------------------------------

    -> Statement is slower as compared to PreparedStatement.
    -> Statement is less secure as comnpared to PreparedStatement.
    -> Statement does not support multimedia information where as PreparedStatement directly supoorts.
    -> Statement does not support parameter binding  , where as PreparedStatement directly supports.

Note: 
   ->in case of statement interface  , for each and every request query will be submitted into
     database server then execute , which is slower and less secure approach.

     PARAMETER BINDING
     -----------------

     -> it is a  technique which is used to assign value to query parameter.
     ->query parameter is always denoted by ? symbol.
     ->to assign value PreparedStatement provides setter method.
     -> all the setter method always accept 2 argument.
          1.index value.
          2.parameter value.
     ->index value begins from 1 unlike array index value.

     Example:

     insert into student(roll,name,cgpa) values(?,?,?);

     pst.setInt(1,100);
     pst.setString(2,"Ram");
     pst.setDouble(3,10.0);

3.CallableStatement

 -> it is used to execute PL/SQL  procedure and Function.


 Example  PROGRAMS
 ------------------
 first create a table student in Oracle.
 Run SQL command line -> conn  system/lit ->
    create table student(roll number(10),name varchar2(10),cgpa number(3,1)); 
    -----------------------------------------------------------------------------
 //Statement_Example DDL Statement
    import java.sql.Connection;
import java.sql.Statement;

public class Statement_Example {
	public static void main(String[] args) 
	{
		try 
		{
			Connection con = Provider.getOracleConnection();
			Statement st = con.createStatement();
			String sql = "drop table student";
			boolean status = st.execute(sql);
			if(!status)
			{
				System.out.println("table dropped");
			}
			else
			{
				System.out.println("Table not dropped");
			}
			String sql2 ="create table student(roll number(10),name varchar2(10),cgpa number(3,1))";
			boolean status2 = st.execute(sql2);
			if(!status2)
			{
				System.out.println("Table created ");
			}
			else
			{
				System.out.println("table not created ");
			}
			String sql3 ="alter table student add phone number(10)";
			boolean status3 = st.execute(sql3);
			if(!status3)
			{
				System.out.println("Table ALtered ");
			}
			else
			{
				System.out.println("table not created ");
			}
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			System.out.println(e);
		}
	}

}

output:
table dropped
Table created 
Table ALtered 

----------------------------------------------------------------------------------------

//Statement Example from Keyboard
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Statement_EX_KB {
	public static void main(String[] args) 
	{
		Scanner sc= new Scanner(System.in);
	
		try 
		{
			Connection con = Provider.getOracleConnection();
			Statement st = con.createStatement();
			System.out.println("entre the table name you wanna drop");
		 	String tname = sc.next();
			String sql = "drop table "+tname;
			boolean status = st.execute(sql);
			if(!status)
			{
				System.out.println("table dropped");
			}
			else
			{
				System.out.println("Table not dropped/does not exists");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
	
Output:
-------
entre the table name you wanna drop
student
table dropped

---------------------------------------------------------------------------------------
//Statement Example DML dirct and from keyboard both.

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Statement_EX_KB_DML {
	public static void main(String[] args) 
	{
		Scanner sc= new Scanner(System.in);
	
		try 
		{
			Connection con = Provider.getOracleConnection();
			Statement st = con.createStatement();
			
			String sql = "insert into student values(100,'sambit',9.9,9999)";
			
			int status  = st.executeUpdate(sql);
			if(status > 0)
			{
				System.out.println("inserted");
			}
			
			//from keyboard 
			System.out.println("enter roll no");
			int roll = sc.nextInt();
			System.out.println("enter name");
			String name = sc.next();
			System.out.println("enter cgpa");
			Double cgpa = sc.nextDouble();
			System.out.println("enter phone");
			int phone = sc.nextInt();
			
			String sql2 = "insert into student values("+roll+",'"+name+"',"+cgpa+","+phone+")";
			int status2 = st.executeUpdate(sql2);
			if(status2 > 0)
			{
				System.out.println("inserted");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
	
output:
inserted
enter roll no
101
enter name
fo
enter cgpa
2.1
enter phone
90898
inserted

----------------------------------------------------------------------------------------------------
//statement example inserting multiple records submikts the query to database each and every time
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Statement_EX_KB_DML_Multiple {
	public static void main(String[] args) 
	{
		Scanner sc= new Scanner(System.in);
	
		try 
		{
			Connection con = Provider.getOracleConnection();
			Statement st = con.createStatement();
			
			
			
			//from keyboard inserting 6 records
			for(int i = 0 ; i < 5 ;i++)
			{
			System.out.println("enter roll no");
			int roll = sc.nextInt();
			System.out.println("enter name");
			String name = sc.next();
			System.out.println("enter cgpa");
			Double cgpa = sc.nextDouble();
			System.out.println("enter phone");
			int phone = sc.nextInt();
			
			String sql2 = "insert into student values("+roll+",'"+name+"',"+cgpa+","+phone+")";
			int status2 = st.executeUpdate(sql2);
			if(status2 > 0)
			{
				System.out.println("inserted");
			}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
	
output:
-------
enter roll no
1
enter name
tanya
enter cgpa
2.1
enter phone
0909
inserted
enter roll no
2
enter name
shanaya
enter cgpa
3.1
enter phone
0909
inserted
enter roll no
3
enter name
shradha
enter cgpa
3.2
enter phone
9898
inserted
enter roll no
4
enter name
simran
enter cgpa
9.1
enter phone
9876
inserted
enter roll no
5
enter name
swoatiks
enter cgpa
3.2
enter phone
9090
inserted

------------------------------------------------------------------------------------------------------
//PrepareStatement Exampleimport java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class PST_EX 
{
   public static void main(String[] args) 
   {
	   Scanner sc = new Scanner(System.in);
	  try 
	  {
		Connection con = Provider.getOracleConnection();
		String sql = "insert into student values(?,?,?,?)";
		PreparedStatement  pst = con.prepareStatement(sql);
		pst.setInt(1, 1000);
		pst.setString(2, "shirley");
		pst.setDouble(3, 5.5);
		pst.setInt(4, 5463);
		
		int status = pst.executeUpdate();
		if(status > 0)
		{
			System.out.println("Record inserted");
		}
		else
		{
			System.out.println("Failed to insert");
		}
		System.out.println("_____________________________________");
		//Multiple DML Operations using PreparedStatement  processes the sql
		// query only once and rest times
		//only value is passed
		
		for(int i = 0 ; i<=3 ; i++)
		{
			System.out.println("enter roll");
			
		pst.setInt(1,sc.nextInt());
		System.out.println("enter name");
		pst.setString(2, sc.next());
		System.out.println("enter cgpa");
		pst.setDouble(3,sc.nextDouble());
		System.out.println("enter phone");
		pst.setInt(4, sc.nextInt());
		
		int status2 = pst.executeUpdate();
		if(status2 > 0)
		{
			System.out.println("Record inserted");
		}
		else
		{
			System.out.println("Failed to insert");
		}
		}
	  } 
	  catch (Exception e) 
	  {
		// TODO: handle exception
		  System.out.println(e);
	  }
   }
}

output:
-------
Record inserted
_____________________________________
enter roll
5800
enter name
a
enter cgpa
2.1
enter phone
5
Record inserted
enter roll
5801
enter name
hola
enter cgpa
2.2
enter phone
565
Record inserted
enter roll
5521
enter name
snayak
enter cgpa
6.6

enter phone
55
Record inserted
enter roll
5329
enter name
atatseee
enter cgpa
2.3
enter phone
56

Record inserted

-----------------------------------------------------------------------------------------------------
//CallableStatement Example(Callin a PL/SQL procedure)-
1.first create a PL/SQL procedure to insert record in student table (run SQLCommandline)
   
   create or replace procedure proc_insert(r number,n varchar2,c number,p number)
   is
   begin
   insert into student values(r,n,c,p);
   end proc_insert;
   /

2.java program to execute this procedure by CallableStatement

 
  import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Scanner;

public class CST_EX_PROC 
{
	public static void main(String[] args) 
	{
	  try 
	  {
		Connection con = Provider.getOracleConnection();
		CallableStatement cst = con.prepareCall("call proc_insert(?,?,?,?)");
		Scanner  sc = new Scanner(System.in);
		for(int i = 0 ; i <=2 ;i++)
		{
			System.out.println("enter roll");
			
			cst.setInt(1,sc.nextInt());
			System.out.println("enter name");
			cst.setString(2, sc.next());
			System.out.println("enter cgpa");
			cst.setDouble(3,sc.nextDouble());
			System.out.println("enter phone");
			cst.setInt(4, sc.nextInt());
			cst.execute();
			
			System.out.println("Record inserted successfully");
			
		}
	  } 
	  catch (Exception e) 
	  {
		// TODO: handle exception
		  System.out.println(e);
	  }	
	}

}

Output:
-------
enter roll
33
enter name
s
enter cgpa
3.2
enter phone
21
Record inserted successfully
enter roll
34

enter name
sa
enter cgpa
2.1

enter phone
33
Record inserted successfully
enter roll
35
enter name
sa
enter cgpa
2.1
enter phone
56
Record inserted successfully
-------------------------------------------------------------------------------------------------
//Callable Statement Example(Calling PL/SQL function from java program)
  1.create a PL/SQL function to insert data into student table and return number of records
     (Run /mysql command-line)

     create or replace function fun_insert(r number,n varchar2,c number,p number)
     return number
     is
     f number;
     begin
     insert into student values(r,n,c,p);
     select count(*) into f from student;
     return f;
     end fun_insert;
     /

     2.java program to execute a function from java using CallableStatement

       import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Scanner;



public class CST_FUNCT 
{
	public static void main(String[] args) throws Exception
	{
	  try 
	  {
		Connection con = Provider.getOracleConnection();
		CallableStatement cst = con.prepareCall("{? = call fun_insert(?,?,?,?)}");
		Scanner  sc = new Scanner(System.in);
		for(int i = 0 ; i <=2 ;i++)
		{
			System.out.println("enter roll");
			
			cst.setInt(2,sc.nextInt());
			System.out.println("enter name");
			cst.setString(3, sc.next());
			System.out.println("enter cgpa");
			cst.setDouble(4,sc.nextDouble());
			System.out.println("enter phone");
			cst.setInt(5, sc.nextInt());
			cst.registerOutParameter(1, java.sql.Types.INTEGER);
			cst.execute();
			
			System.out.println("Record inserted successfully");
			
		}
		System.out.println("number of records in table "+cst.getInt(1));
	  } 
	  catch (Exception e) 
	  {
		// TODO: handle exception
		  System.out.println(e);
	  }	
	}

}

output
------
enter roll
54
enter name
s
enter cgpa
3.3

enter phone
66

Record inserted successfully
enter roll
65
enter name
cc
enter cgpa
5.5
enter phone
63
Record inserted successfully
enter roll
6
enter name
ww
enter cgpa
6.66

enter phone
23
Record inserted successfully
number of records in table 22

----------------------------------------------------------------------------------------------------

 ResultSet
 ---------

 -> the output of select statement is virtual table.
 -> virtual table can be stored in ResultSet in JDBC.
 ->ResultSet store data on the basis of rows and columns.
 ->cursor of ResultSet always point to top of ResultSet i.e 0th row.

 next() : it is responsible to check the record existence and moves the cursor to next record 
          of resultset.

          Types of ResultSet:
          -------------------
          1.Non-Scrollable ResultSet

              ->if the ResultSet can only be traversed in single direction.
                i.e top to bottom then it is Non-Scrollable ResultSet.
              ->here to fetch record which is present in 100th row , it  is required to traverse 
                all the 99 records which is unnecssary.

          2.Scrollable ResultSet.

              ->The ResultSet can be traversed in any direction
                 1.top to  bottom
                 2.bottom to top
                 3.Random order 
              -> Scrollable ResultSet is faster than non Scrollable ResultSet.


         -------------------------------
         -> ResultSet interface provide some getter methods to fetch data.
         -> all the getter method always accept either column no.(according to select statement)
            or column name as a argument.

              eg. getString("name"); or getString(2);

         --------------------------------
         -> Statement with type and mode attribute is Scrollable ResultSet.

            syntax:
               Statement st = con.createStatement(Type,Mode);

               Type attribute
               --------------
               TYPE_FORWARD_ONLY : support forward traversing only loke Non-Scrollable RS.
               TYPE_SCROLL_SENSITIVE : support traversing in any direction.

               Mode attribute
               --------------
               CONCUR_READ_ONLY : create a read-only ResultSet 
               CONCUR_UPDATETABLE : DML operations are allowed

   --Non-Scrollable ResultSet  Example --------

   import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class NSRS 
{ 
	public static void main(String[] args) throws Exception 
	{
		try 
		{
			Connection con = Provider.getOracleConnection();
			Statement st = con.createStatement();
			String sql = "select roll,name,cgpa,phone from student";
			
			ResultSet rs = st.executeQuery(sql);
			System.out.println("roll "+" name "+" cgpa "+" phone");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
			}
			
			System.out.println("--other way---");
			ResultSet rs2 = st.executeQuery(sql);
			System.out.println("roll "+" name "+" cgpa "+" phone");
			while(rs2.next())
			{
				System.out.println(rs2.getInt("roll")+"\t"+rs2.getString("name")+"\t"+rs2.getDouble("cgpa")+"\t"+rs2.getInt("phone"));
			}
			
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
		}
	}

}

output:
roll  name  cgpa  phone
100	sambit	9.9	9999
100	sambit	9.9	9999
101	fo	2.1	90898
1	tanya	2.1	909
2	shanaya	3.1	909
3	shradha	3.2	9898
4	simran	9.1	9876
5	swoatiks	3.2	9090
1000	shirley	5.5	5463
1000	shirley	5.5	5463
5800	a	2.1	5
5801	hola	2.2	565
5521	snayak	6.6	55
5329	atatseee	2.3	56
200	d	2.3	54
201	f	3.6	54
33	s	3.2	21
34	sa	2.1	33
35	sa	2.1	56
54	s	3.3	66
65	cc	5.5	63
6	ww	6.7	23
--other way---
roll  name  cgpa  phone
100	sambit	9.9	9999
100	sambit	9.9	9999
101	fo	2.1	90898
1	tanya	2.1	909
2	shanaya	3.1	909
3	shradha	3.2	9898
4	simran	9.1	9876
5	swoatiks	3.2	9090
1000	shirley	5.5	5463
1000	shirley	5.5	5463
5800	a	2.1	5
5801	hola	2.2	565
5521	snayak	6.6	55
5329	atatseee	2.3	56
200	d	2.3	54
201	f	3.6	54
33	s	3.2	21
34	sa	2.1	33
35	sa	2.1	56
54	s	3.3	66
65	cc	5.5	63
6	ww	6.7	23
---------------------------------------------------------------------------------------------
----Scrollable ResultSet Example (read-only)------------------
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SRSRO
{
	public static void main(String[] args) 
	{
		try 
		{
			Connection con = Provider.getOracleConnection();
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			String sql = "select roll,name,cgpa,phone from student";
			ResultSet rs = st.executeQuery(sql);
			System.out.println("Foreard traversing...");
			System.out.println("roll "+" name "+" cgpa "+" phone");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
			}
			
			System.out.println("Backward Traversing...");
			System.out.println("roll "+" name "+" cgpa "+" phone");
			while(rs.previous())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
			}
			
			System.out.println("Record present at 3rd position");
			rs.absolute(3);
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
			
			System.out.println(rs.getRow());
			System.out.println(rs.isFirst());
			System.out.println(rs.isLast());
			System.out.println(rs.isBeforeFirst());
			System.out.println(rs.isAfterLast());
			rs.first();
			System.out.println(rs.getRow());
			rs.last();
			System.out.println(rs.getRow());
			rs.beforeFirst();
			System.out.println(rs.getRow());
			rs.afterLast();
			System.out.println(rs.getRow());
			rs.absolute(4);
			System.out.println(rs.getRow());
			//  rs.deleteRow(); invalid operation for Read only ResultSet
			
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
		}
		
	}

}

Output:
-------
Foreard traversing...
roll  name  cgpa  phone
100	sambit	9.9	9999
100	sambit	9.9	9999
101	fo	2.1	90898
1	tanya	2.1	909
2	shanaya	3.1	909
3	shradha	3.2	9898
4	simran	9.1	9876
5	swoatiks	3.2	9090
1000	shirley	5.5	5463
1000	shirley	5.5	5463
5800	a	2.1	5
5801	hola	2.2	565
5521	snayak	6.6	55
5329	atatseee	2.3	56
200	d	2.3	54
201	f	3.6	54
33	s	3.2	21
34	sa	2.1	33
35	sa	2.1	56
54	s	3.3	66
65	cc	5.5	63
6	ww	6.7	23
Backward Traversing...
roll  name  cgpa  phone
6	ww	6.7	23
65	cc	5.5	63
54	s	3.3	66
35	sa	2.1	56
34	sa	2.1	33
33	s	3.2	21
201	f	3.6	54
200	d	2.3	54
5329	atatseee	2.3	56
5521	snayak	6.6	55
5801	hola	2.2	565
5800	a	2.1	5
1000	shirley	5.5	5463
1000	shirley	5.5	5463
5	swoatiks	3.2	9090
4	simran	9.1	9876
3	shradha	3.2	9898
2	shanaya	3.1	909
1	tanya	2.1	909
101	fo	2.1	90898
100	sambit	9.9	9999
100	sambit	9.9	9999
Record present at 3rd position
101	fo	2.1	90898
3
false
false
false
false
1
22
0
0
4
--------------------------------------------------------------------------------------------------
-----Scrollable ResultSet with DML Operations  -------------------------------------
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SRSRODML
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		try 
		{
			Connection con = Provider.getOracleConnection();
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			String sql = "select roll,name,cgpa,phone from student";
			ResultSet rs = st.executeQuery(sql);
			System.out.println("Foreard traversing...");
			System.out.println("roll "+" name "+" cgpa "+" phone");
            
			rs.next();
			
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
			
			//updating the data
			rs.updateString(2, "Deba");
			rs.updateDouble(3, 5.9);
			rs.updateRow();
			System.out.println("Yes updated");
			
			//insertion operation
			
			rs.moveToInsertRow();
			rs.updateInt(1, 999);
			rs.updateString(2, "Tanya");
			rs.updateDouble(3, 4.4);
			rs.updateInt(4,5454 );
			rs.insertRow();
			
			System.out.println("Record inserted");
			
			//delete opertaion
			
			rs.absolute(3);
			rs.deleteRow();
			
			System.out.println("roll "+" name "+" cgpa "+" phone");
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
			}
			
			
			System.out.println("enter the row u wanna delete");
			int rowno = sc.nextInt();
			rs.absolute(rowno);
			rs.deleteRow();
			System.out.println("Record deleted successfully");
			
			}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
		}
		
	}

}

Output:
Foreard traversing...
roll  name  cgpa  phone
100	sambit	9.9	9999
Yes updated
Record inserted
roll  name  cgpa  phone
1	tanya	2.1	909
2	shanaya	3.1	909
3	shradha	3.2	9898
4	simran	9.1	9876
5	swoatiks	3.2	9090
1000	shirley	5.5	5463
1000	shirley	5.5	5463
5800	a	2.1	5
5801	hola	2.2	565
5521	snayak	6.6	55
5329	atatseee	2.3	56
200	d	2.3	54
201	f	3.6	54
33	s	3.2	21
34	sa	2.1	33
35	sa	2.1	56
54	s	3.3	66
65	cc	5.5	63
6	ww	6.7	23
enter the row u wanna delete

5
Record deleted successfully
-----------------------------------------------------------------------------------------------------
--Program to fetch all the tables present in database server-----------------
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Fetch_ALl
{
	public static void main(String[] args) 
	{
		try 
		{
			Connection con = Provider.getOracleConnection();
			Statement st = con.createStatement();
			String sql = "select table_name from user_tables";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				System.out.println(rs.getString(1));
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
		}
		
	}

}

Output:
-------

HELP
STD
TANYA
F
COLLEGE
BIT
JAVACOURSE
MULTI_TABLE
BANK
USERS
EMPLOYEE
ADV_LAB
PRO_LAB
TEACHER
STUDENT
JAVA_COURSE
PET
CADET



-----------------------------------------------------------------------------------------------------

BATCH PROCESSING
-----------------

-> related sql statement group together and add into batch.
-> process batch in db server by calling executeBatch() method .
    
    syntax:  int[] status = st.executeBatch();

-> it does not nothing or everything principle.

-> addBatch() : it is used to add a query into batch.

 Example Program:
 import java.sql.Connection;
import java.sql.Statement;

public class Batch_Ex 
{

	public static void main(String[] args) 
	{
		try 
		{
			Connection con = Provider.getOracleConnection();
			Statement st = con.createStatement();
			String sql = "insert into student values(800,'shanaya',8.8,911)";
			String sql2 = "insert into student values(801,'Ramya',9.8,921)";
			String sql3 = "update student set name='sam' where roll=100";
			
			st.addBatch(sql2);
			st.addBatch(sql3);
			st.addBatch(sql);
			
			int[] status = st.executeBatch();
			for(int a : status)
			{
				System.out.println(a);
			}
			
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
}

Output:
-------
1
2
1

-----------------------------------------------------------------------------------------------------
TRANSACTION PROCESSING
----------------------

 -> the related sql statement group together and execute on the basis of nothing or everything
    prinnciple known as TRANSACTION MANAGEMENT.

 -> by default JDBC supports auto-commit.
 ->to manage the transaction it is required ti disable auto-commit.

    syntax: con.setAutoCommit(false);

    Example Programs:
    -----------------

    1.

    import java.sql.Connection;
import java.sql.Statement;

public class Transaction_Ex 
{
	public static void main(String[] args) 
	{
		try 
		{
			Connection con = Provider.getOracleConnection();
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			String sql = "insert into student values(800,'shanaya',8.8,911)";
			String sql2 = "insert into student values(801,'Ramya',9.8,921)";
			String sql3 = "update student set name='sam' where roll=100";
			
			int status = st.executeUpdate(sql);
			int status2 = st.executeUpdate(sql2);
			int status3 = st.executeUpdate(sql3);
			
			if(status > 0 && status2 > 0 && status3 >0)
			{
				con.commit();
				System.out.println("Records are committed");
			}
			else
			{
				System.out.println("Records are not commited");
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
		}
		
		
	}

}
output:
-------
Records are committed

    -------------------------------------
    2.

    import java.sql.Connection;
import java.sql.Savepoint;
import java.sql.Statement;

public class Transaction_Ex2 
{
	public static void main(String[] args) 
	{
		try 
		{
			Connection con = Provider.getOracleConnection();
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			String sql = "insert into student values(800,'shanaya',8.8,911)";
			String sql2 = "insert into student values(801,'Ramya',9.8,921)";
			String sql3 = "insert into student values(802,'jojo',8.8,922)";
			String sql4 = "insert into student values(803,'kanika',9.8,923)";
			
			
			int status = st.executeUpdate(sql);
			int status2 = st.executeUpdate(sql2);
			Savepoint sp1 = con.setSavepoint();
			int status3 = st.executeUpdate(sql3);
			Savepoint sp2 = con.setSavepoint();
			int status4 = st.executeUpdate(sql4);
			System.out.println(status);
			System.out.println(status2);
			System.out.println(status3);
			System.out.println(status4);
			
			con.rollback(sp2);
			System.out.println("Roll back upto sp2");
			con.rollback(sp1);
			System.out.println("Roll back up to sp1");
			
			con.commit();
			System.out.println("Commited");
			con.close(); //it automatically commits all
			


		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
		}
		
		
	}

}

output:
-------
1
1
1
1
Roll back upto sp2
Roll back up to sp1
Commited
--------------------------------------------------------------------------------------------------
MULTIMEDIA DATA PROCESSING
--------------------------
//stroing multimedia into database
  blob : binary large object
  clob : character large object
create a table in oracle to store multimedia (run SQL command-line)

syntax:

  create table mymultimedia
  (
  	id number(10),
  	src blob
  );

  ------------------------------------
  //java program to store multimedia into that database

  import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Multi_Store 
{
	public static void main(String[] args) 
	{
		try 
		{
			Connection con = Provider.getOracleConnection();
			String sql = "insert into mymultimedia values(?,?)";
			
			PreparedStatement pst = con.prepareStatement(sql);
			
			FileInputStream fis = new FileInputStream("j.jpg");
			pst.setInt(1, 101);
			pst.setBinaryStream(2, fis , fis.available());
			
			int status = pst.executeUpdate();
			if(status >0)
			{
				System.out.println("multimedia stored");
			}
			else
			{
				System.out.println("Failed to store");
			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
		}
		
	}

}

output:
multimedia stored

Note: the image j.jpg must be present in the program location
 i.eE:\Storage\AdvanceJava\Eclipse Programs\JDBC_HOME\JDBC_PROGRAMS

 --to view the multimedia(image stored in database) ORACLE ---

   software required :- SqlDeveloper (J2EE)

   Run SqlDeveloper.exe -> connections -> +(add a connection) -> give connection  name ->
   userid , password -> test connection -> connect -> connected to database

   then

   expand connection -> connection name -> tables -> mymultimedia(table name) 
   -> double-click -> data -> double-click on(blob) record -> set view as image

  --------------------------------------------------

  //java program to fetch the multimedia
  import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Fetch
{
	public static void main(String[] args) 
	{
		try 
		{
			Connection con = Provider.getOracleConnection();
			String sql = "select src from mymultimedia where id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, 101);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				FileOutputStream fos = new FileOutputStream("res.jpg");
				InputStream iis = rs.getBinaryStream(1);
				int ch;
				while((ch = iis.read())!= -1)
				{
					fos.write(ch);
					
				
				}
				System.out.println("fetched succesfully");
					iis.close();
					fos.close();
			}
			
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
		}
		
	}

}

Output:
fetched succesfully

--an image file will be created res.jpg.
-----------------------------------------------------------------------------------------------------
DATABASE METADATA
-----------------
 ->  it is an interface present in java.sql package (DatabseMetaData)
 -> this interface is used to get details about database server and JDBC driver.

    syntax to create the object :

     DatabaseMetaData dbnd = con.getMetaData();

     Example
     --------
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

output:
-------
11
1
Oracle
Oracle Database 11g Release 11.1.0.0.0 - Production
30
0
0
0
0
32
30
________________________________________
5
7
MySQL
5.7.13-enterprise-commercial-advanced-log
64
64
64
256
256
16
64


-----------------------------------------------------------------------------------------------------
RESULTSETMETADTA
----------------
 ->it is an interface present in java.util package.
 ->this information is used to get information about ResultSet.
 Example
 -------
 //ResultSet MetaData
import java.sql.*;
public class RSMD_Ex 
{
	
	public static void main(String[] args) throws Exception
	{
		Connection con = Provider.getOracleConnection();
		
		Statement st = con.createStatement();
		String sql = "select * from student";
		ResultSet rs = st.executeQuery(sql);
		System.out.println("NAME"+"\t"+"ROLL"+"\t"+"CGPA" );
		
		while(rs.next())
		{
			System.out.println(rs.getString("name")+"\t"+rs.getInt("Roll")+"\t"+rs.getDouble("cgpa"));
		}
		
		System.out.println("_____________________________________");
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int no_cols =rsmd.getColumnCount();
		
		System.out.println("Number of columns in student table is  "+no_cols);
		
		for(int  i=1 ; i <= no_cols ; i++)
		{
			System.out.println(rsmd.getColumnLabel(i)+"\t"+rsmd.getColumnTypeName(i)+"\t"+rsmd.getColumnClassName(i));
		}
		
		
	}
	

}

Output:
-------
NAME	ROLL	CGPA
sam	100	5.9
sam	100	9.9
shanaya	800	8.8
tanya	1	2.1
shanaya	2	3.1
shanaya	800	8.8
simran	4	9.1
swoatiks	5	3.2
shirley	1000	5.5
shirley	1000	5.5
a	5800	2.1
hola	5801	2.2
snayak	5521	6.6
atatseee	5329	2.3
d	200	2.3
f	201	3.6
s	33	3.2
sa	34	2.1
sa	35	2.1
s	54	3.3
cc	65	5.5
ww	6	6.7
Tanya	999	4.4
Ramya	801	9.8
Ramya	801	9.8
shanaya	800	8.8
Ramya	801	9.8
_____________________________________
Number of columns in student table is  4
ROLL	NUMBER	java.math.BigDecimal
NAME	VARCHAR2	java.lang.String
CGPA	NUMBER	java.math.BigDecimal
PHONE	NUMBER	java.math.BigDecimal

----------------------------------------------------------------------------------------------------------------
//connecting to .cd=sv file(comma separated variables)

///first create a  comma separated file and name it as myfile.csv
  
  //myfile.csv
  roll,name,cgpa,phone
  1,tanya,8.9,9999
  2,shanaya,9.0,8765
  3,shradha,6.5,6545

  -> then create a data source name dsn for .csv file 
     name-> (dsn_csv_home)

     steps :-

      control panel -> Adminstrative tools -> ODBC Data Sources (32-bit) -> System DSN -> ADD ->
      microsoft Text Driver (*.txt,*.csv) -> finish -> give a dsn name (dsn_csv_home) -> uncheck
      currennt directory -> select directory -> select myfile.csv ->  ok

 ->java program

 //java program to connect to .csv file
import java.sql.*;
class Demo
{
	public static void main(String[] args)  throws Exception
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con = DriverManager.getConnection("jdbc:odbc:dsn_csv_home");
		if(con != null)
		{
			System.out.println("connected to .csv file");
			String sql = "select * from myfile.csv";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while(rs.next())
			{
				int roll = rs.getInt("roll");
				String name = rs.getString("name");
				Double cgpa = rs.getDouble("cgpa");
				int phone = rs.getInt("phone");

				System.out.println(roll+"\t"+name+"\t"+cgpa+"\t"+phone);
			}
		}
	}
}

output:
-------
E:\Storage\AdvanceJava\Notes@Autumn>set path=;C:\Program Files (x86)\Java\jdk1.6.0\bin;

E:\Storage\AdvanceJava\Notes@Autumn>javac demo.java

E:\Storage\AdvanceJava\Notes@Autumn>java Demo
connected to .csv file
1       tanya   8.9     9999
2       shanaya 9.0     8765
3       shradha 6.5     654
----------------------------------------------------------------------------------------------------------------
//connecting to excel worksheet , fetrching data from excel worksheet and storing it in Database

 1. create a excel worksheet and storedata
     //mysheet.xls

    roll name cgpa phone
    54    sam  7.5  5463
    55    Ram  8.5  5521
    56    Dam  9.5  5801

    Save the above as :- save as type :- Excel 97-2003 Workbook (*.xls) -> FileName :- mysheet.xls


 2.create a DSN for Excel

      control panel -> Adminstrative tools -> ODBC Data Sources (32-bit) -> System DSN -> ADD ->
      Driver Do microsoft excel (*.xls) ->Finish -> give a dsn name (dsn_xls_home) -> select Workbook
      -> (select the directory of  .xls file) (mysheet.xls) -> ok

  3.Java program

  //java program
import java.sql.*;
class Demo
{
	public static void main(String[] args) throws Exception
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con = DriverManager.getConnection("jdbc:odbc:dsn_xls_home");

		Class.forName("oracle.jdbc.OracleDriver");
		Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","lit");

		String sql = "insert into student values(?,?,?,?)";
		PreparedStatement pst = con2.prepareStatement(sql);
		if(con != null)
		{
			System.out.println("connnected to cls database");
			Statement st = con.createStatement();
			String sql2 = "select * from [sheet1$]";
			ResultSet rs = st.executeQuery(sql2);

			while(rs.next())
			{
				int roll = rs.getInt("roll");
				String name = rs.getString("name");
				Double cgpa = rs.getDouble("cgpa");
				int phone = rs.getInt("phone");

				System.out.println(name+"\t"+roll+"\t"+cgpa+"\t"+phone);

				pst.setInt(1,roll);
				pst.setString(2,name);
				pst.setDouble(3,cgpa);
				pst.setInt(4,phone);

				int status = pst.executeUpdate();
				if(status > 0)
				{
					System.out.println("data stored to oracle from excel sheet");
				}

			}
		}
	}
}

output:
-------

E:\Storage\AdvanceJava\Notes@Autumn>set path=;C:\Program Files (x86)\Java\jdk1.6.0\bin;

E:\Storage\AdvanceJava\Notes@Autumn>set classpath=;E:\Storage\AdvanceJava\J2EE\JAR\ojdbc14.jar;

E:\Storage\AdvanceJava\Notes@Autumn>javac demo.java

E:\Storage\AdvanceJava\Notes@Autumn>java Demo
connnected to cls database
mona    1001    5.5     894
data stored to oracle from excel sheet
sona    1002    5.6     56135
data stored to oracle from excel sheet
rona    1003    2.3     651
data stored to oracle from excel sheet
dona    1004    2.2     51
data stored to oracle from excel sheet

----------------------------------------------------------------------------------------------------------------




