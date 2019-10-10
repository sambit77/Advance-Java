PL/SQL
------------------------------------
-it is a programming approach of sql
-collection of sql statement written in programming format i.e PL/SQL

Advantages - (Faster,secure,Shareable)

->PL/SQL program can be developed by two ways
  1.procedure/stored procedure
  2.Function/stored Function

   Stored procedure :- it is PL/SQL program which may or may not take argument but does not return any value.
   Stored function :- it is PL/SQL program which may or may not accept argument but always return a value.

   1.syntax  of PL/SQL procedure

     create or replace procedure procedure_name(arg1 datatype,arg2 datatype,......,argN datatype)
     is
     variable declaration
     begin
     logic of procedure
     end procedure_name;
     /  

     Note :- command to call a procedure
          ->execute procedure_name;
          ->show error;  //it prints the error to console
   2.syntax of PL/SQL function

     create or replace function function_name(arg1 datatype,arg2 datatype,.................,argN datatype)
     return   datatype
     is
     variable declaration
     begin
     logic of function
     return value/expression
     end function_name;
     /

     Note:- A function can only be called from a procedure 

     syntax:

     make a procedure to call the function

     create or replace procedure procedure_name
     is
     result number(2);
     begin
     result :=  function_name;
     end procedure_name;
     /


 ----------------------------------------------------------------------------------------------------------
 Examples
 1.procedure to insert update delete from student table

   create or replace procedure proc_DML(r number,n varchar2,a number)
   is
   begin
   insert into student values(r,n,a);
   end proc_DML;
   /

   execute proc_DML(100,'ram',9.1);

 2.procedure to find the maximum cgpa from student table

   create or replace procedure proc_max
   is
   a number(3,1);
   begin
   select max(cgpa) into a from student;
   dbms_output.put_line('maximum cgpa is ' || a); //prints to console ,|| String concatenation
   end proc_max;
   /

   Note:-
      to use debms_output.put_line();
      we need to set the serveroutput on using command ->set serveroutput on;

   set serveroutput on;
   execute procedure proc_max;

 3.PL/SQL function to find maximum cgpa from student table

   create or replace function fun_max
   return  number
   is
   a number(3,1);
   begin
   select max(cgpa) into a from student;
   return a;
   end fun_max;
   /

   //procedure to call the function

   create or replace procedure proc_call
   is
   cgpa number(3,1);
   begin
   cgpa := fun_max;
   dbms_output.put_line('maximum cgpa is ' || cgpa);
   end proc_call;
   /

 4.PL/SQL procedure to display details of students based on roll number

   create or replace procedure proc_dtl(r number)
   is
   n varchar2(20);
   c number(3,1);
   begin
   select name,cgpa into n,c from student where roll=r;
   dbms_output.put_line('ROLL ' || r);
   dbms_output.put_line('NAME ' || n);
   dbms_output.put_line('CGPA ' || c);
   end proc_dtl;
   /   

 5.PL/SQL function to display max cgpa and current date 

   create or replace function my_fun
   return date
   is
   c number(3,1);
   begin
   select max(cgpa) into c from student;
   dbms_output.put_line('Maximum cgpa is ' || c);
   return sysdate;
   end my_fun;
   /

   create or replace procedure proc_call
   is
   today date;
   begin
   today := my_fun;
   dbms_output.put_line('today is ' || today);
   end proc_call;
   /

  6.PL/SQL procedure to add two numbers 

    create or replace procedure proc_add
    is
    no1 number(4) := &no1;
    no2  number(4) := &no2;
    result number(10);
    begin
    result := no1+no2;
    dbms_output.put_line('addition is' || result);
    end proc_add;
    /

    execute proc_add;

 --------------------------------------------------------------------------------
 Notes:-

 max(colname) -> returns the maximum value for that column.
 count(*) -> returns the number of records in the table.
 sysdate ->returns date 
 systimestamp -> Time systimestamp
 current_date  -> returns date
 