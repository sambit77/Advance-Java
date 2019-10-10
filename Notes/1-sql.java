-------------------------------------
To connect to oracle database
-open run SQL commandline
- command-> conn system/lit
 (Here Default  Username-system , Password-lit for oracle database that we have given during installation)
-command->cl scr; //clear screen

Default username for mysql is root;
-------------------------------------
SQL(Structured Query Language)
-------------------------------
SQL supports five sub languages

1.DQL(Data Query Language)
2.DDl(Data Definition Language)
3.DML(Data Manipulation Language)
4.TCL(Transaction Control Language)
5.DCL (Data Control Language)

--------------------------------------
1.DQL(Data Query Language) /DRL(Data Retrieval Language)

To fetch/search/display/show record from database table.DQL  provides select statement

syntax:
  - select * from table_name;
  - select col1,col2,.......colN from table_name1 ,table_name2 .....table_nameN
    from condition;

Note -- * means all columns

---------------------------------------
2.DDL(Data Definition Language)

To create and manipulate database object ,DDl provides 5 statements
 create,alter,drop,truncate,rename

 a)create
    it is used to create an object like table/view/sequence/procedure/function etc.

    syntacx to create a table 

    create table student
    (roll number(10),
     name varchar2(10),
     cgpa number(3,1)	
    );

    Note- desc table_name;(to check table existence and get description)

    Datatypes in SQL

    1.number(p)  //p=precison  (p=totaldigits - scaledigits)
    2.number(p,s) //p=precison s=scale (p=totaldigits - scaledigits) s=no of digits after decimal
    3.char
    4.varchar
    5.varchar2
    6.date 
    7.blob (binary large object)
    8.clob (character large object)

CONSTRAINTS
------------
 it is restrictions impose on a column to avoid invalid operations

 Types of constraints:-
 primary key,not null,check ,unique,foreign key


 1.primary key
  -only one primary key is allowed on a single double
  -it is combination of not null and unique i.e null value and duplicate value arent allowed

 2.check
    -logical condition >,<,<=,>=
    -if a column is protected with check constraint ,then it accepts values based on condition

 3.not null
   -if a column is protected with not null constraint it can not accept null values
   -duplicates area allowed

 4.unique
   -the constarint restrict a column to accept duplicate values
   -multiple null values are allowed
 5.foreign key
   -it is used to create a relation between two tables

   Example:-

   create table teacher
   (
   	Tid number(4) primary key,
   	Tname varchar2(10) not null,
   	Dept_name varchar2(20) check(Dept_name in ('cs','it')),
   	phone number(10) not null unique,
   	Dob date
   	);


  b)alter
    it is used to modify the Structure of the table

    1.add a new column
    2.delete an existing column
    3.rename the column
    4.change the datatype/size

    1.add a new column
      alter table table_name add(col1 datatype(size),col2 datatype(size),......,colN datatype(size));

    2.delete an existing column
      alter table table_name drop(col1,col2,...........,colN);

    3.rename an existing column
      alter table table_name rename column old_colname to new_colname;

    4.change the datatype/size
      alter table table_name modify(old_colname new_datatype(size));

  c) rename
     it is used to change the object name.
     syntax: rename table old_tablename to new_tablename;

  d)drop
    syntax:drop table student;

  e)truncate
   -it is used to delete all the record without any condition
   -truncate is made for physical deletion

   syntax: trubcate table table_name;

------------------------------------------   
3.DML(Data Manipulation Language) / RML(Record Manipulation Language)

 it is used to mnaipulate the record. to do so DML provides 3 statements

 insert,update,delete

 1.insert

   //to insert/store/save the record
   syntax:
     - insert into table_name values(val1,val2,.........,valN);
     -insert into table_name(col1,col2,....,colN) values(val1,val2,..........,valN);

 2.delete
    -delete from table_name;
    -delete table_name;
    -delete from table_name where condition;

 3.update
    it is used to modify the record
    syntax :
     update table_name set col1=val1,col2=val2,...........,colN=valN where condition;

Note:-
  -DDl operation are auto-commit.
  -DML operation are not auto-commit we have to write commit manually in case of abnormal termination.

    commands-> commit; //changes are stored to database
               exit; //nornal termination changes are saved automatically no need to write commit after DML;

   -Abnormal termination of server (by clicking the close button in top right corner) //changes are not stored 
                                                                                     // permanently

-------------------------------------------------------
4.TCL(Transaction Control Language)

-All the DML oerations are known as transaction.
-to save or discard the transaction , SQL provides 3 statements
   1.commit// to save the transaction permanently
   2.Rollback //to discard the transaction
   3.savepoint //rollback up to a certain point

-when any DML statements(insert/update/delete) issued on a oracle server ,server automatically create a
 temporary memory i.e known as rollback segment
-The tables on which DML going to be performed are copied to the rollback segment i.e technicallly known as
 snapshots.
-Actually DML opertaions are performed on sanpshots within rollback segment always.
-commit and rollback both are responsible to delete snapshots and entire rollback segment

-difference between commit and rollback is ,before delete the snapshots ,entire snapshots will be replaced in to
 original table where as rollback delete the snapshot and rollback without reflecting original table
-data once commited can not be rollbacked and vice-versa.

auto-commit
------------

Oracle Server automatically issue commit statement
 1.when an user perform DDL operation.
 2.Exited from SQL command line normally by using exit or disconnect.

Oracle Server automatically issue rollback statement 
  1.when user exited from SQL commandline abnormaly by using close button.

SAVEPOINT: it works on the basis of LIFO.

   Example:-
     insert into emp1 values(1,'a');
     insert into emp1 values(2,'b');
     savepoint a1;
     insert into emp1 values(3,'c');
     savepoint a2;
     insert into emp1 values(4,'d');
     insert into emp1 values(5,'e');
     savepoint a3;
     insert into emp1 values(6,'f');
     rollback to a3;
     select * from emp1;

--------------------------------------------------------------
5.DCL(Data Control Language)

it is used to control an user by assign permission,to do so SQL provides 2 statement
 1.grant //to assign permission to user
 2.revoke //to remove permission from user

 syntax : grant permission_name to user_name;
          revoke permission from user;

 Example:

   Connect to OracleServer
    ->conn system/lit; //logged in as database adminstrator

   Creating an user (sambit)with password(tanya)  
    ->create user sambit identified by tanya;
    ->grant dba to sambit; //grant create session privillege to an user 
    ->conn sambit/tanya; //logged in as user sambit
    ->show user;//show the current logged in user
   To revoke a permission from user first we have to logged in as adminstrator(system)
    ->conn system/lit
    ->revoke dba from sambit;



